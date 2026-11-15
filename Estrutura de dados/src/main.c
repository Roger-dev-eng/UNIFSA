#include "d1_busca.h"
#include "d2_listagem.h"
#include "d3_despacho.h"
#include "d4_historico.h"
#include "d5_rotas.h"

#include <ctype.h>
#include <errno.h>
#include <limits.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    TabelaHash codigos;
    VetorPedidos pedidos;
    HeapDespacho pendentes;
    HistoricoDespachos historico;
    GrafoBairros mapa;
} Sistema;

static const char *const bairros[] = {
    "Centro", "Norte", "Sul", "Leste", "Oeste", "Distrito Industrial"
};
enum { TOTAL_BAIRROS = sizeof bairros / sizeof bairros[0] };

static bool ler_linha(const char *mensagem, char linha[128])
{
    fputs(mensagem, stdout);
    fflush(stdout);
    if (fgets(linha, 128, stdin) == NULL) {
        return false;
    }

    char *quebra = strchr(linha, '\n');
    if (quebra != NULL) {
        *quebra = '\0';
    } else if (!feof(stdin)) {
        int c;
        do {
            c = getchar();
        } while (c != '\n' && c != EOF);
        linha[0] = '\0';
    }
    return true;
}

static bool ler_inteiro(const char *mensagem, int minimo, int maximo, int *valor)
{
    char linha[128];
    while (ler_linha(mensagem, linha)) {
        char *fim;
        errno = 0;
        long numero = strtol(linha, &fim, 10);
        if (fim != linha && errno == 0 && numero >= minimo && numero <= maximo) {
            while (isspace((unsigned char)*fim)) {
                ++fim;
            }
            if (*fim == '\0') {
                *valor = (int)numero;
                return true;
            }
        }
        printf("Informe um inteiro de %d a %d.\n", minimo, maximo);
    }
    return false;
}

static bool interpretar_data(const char *linha, int *data)
{
    if (strlen(linha) != 10 || linha[4] != '-' || linha[7] != '-') {
        return false;
    }
    for (size_t i = 0; i < 10; ++i) {
        if (i != 4 && i != 7 && !isdigit((unsigned char)linha[i])) {
            return false;
        }
    }

    int ano = atoi(linha);
    int mes = atoi(linha + 5);
    int dia = atoi(linha + 8);
    if (ano < 1 || mes < 1 || mes > 12 || dia < 1) {
        return false;
    }
    const int dias_por_mes[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    int limite = dias_por_mes[mes - 1];
    if (mes == 2 && ano % 4 == 0 && (ano % 100 != 0 || ano % 400 == 0)) {
        ++limite;
    }
    if (dia > limite) {
        return false;
    }
    *data = ano * 10000 + mes * 100 + dia;
    return true;
}

static bool ler_data(int *data)
{
    char linha[128];
    while (ler_linha("Data (AAAA-MM-DD): ", linha)) {
        if (interpretar_data(linha, data)) {
            return true;
        }
        puts("Data invalida.");
    }
    return false;
}

static void mostrar_bairros(void)
{
    for (int i = 0; i < TOTAL_BAIRROS; ++i) {
        printf("  %d - %s\n", i + 1, bairros[i]);
    }
}

static void mostrar_pedido(const Pedido *pedido)
{
    int data = pedido->data;
    printf("Pedido %d | %04d-%02d-%02d | %s | %s | %s\n", pedido->codigo,
           data / 10000, data / 100 % 100, data % 100, bairros[pedido->bairro],
           pedido->urgente ? "urgente" : "normal", pedido->despachado ? "despachado" : "pendente");
}

static void cadastrar(Sistema *sistema)
{
    int codigo;
    if (!ler_inteiro("Codigo: ", 1, INT_MAX, &codigo)) {
        return;
    }
    if (hash_buscar(&sistema->codigos, codigo) != NULL) {
        puts("Ja existe um pedido com esse codigo.");
        return;
    }

    int data;
    int urgente;
    if (!ler_data(&data) || !ler_inteiro("Urgente? (0 = nao, 1 = sim): ", 0, 1, &urgente)) {
        return;
    }
    mostrar_bairros();
    int bairro;
    if (!ler_inteiro("Bairro de destino: ", 1, TOTAL_BAIRROS, &bairro)) {
        return;
    }

    Pedido *pedido = malloc(sizeof *pedido);
    if (pedido == NULL) {
        fputs("Memoria insuficiente para um novo pedido.\n", stderr);
        return;
    }
    *pedido = (Pedido){
        .codigo = codigo,
        .data = data,
        .bairro = bairro - 1,
        .chegada = sistema->pedidos.total,
        .urgente = urgente == 1
    };
    /* A tabela, o vetor e o heap guardam referências ao mesmo pedido. */
    hash_inserir(&sistema->codigos, pedido);
    vetor_adicionar(&sistema->pedidos, pedido);
    heap_inserir(&sistema->pendentes, pedido);
    puts("Pedido cadastrado.");
}

static void buscar(Sistema *sistema)
{
    int codigo;
    if (!ler_inteiro("Codigo: ", 1, INT_MAX, &codigo)) {
        return;
    }
    const Pedido *pedido = hash_buscar(&sistema->codigos, codigo);
    if (pedido == NULL) {
        puts("Pedido nao encontrado.");
    } else {
        mostrar_pedido(pedido);
    }
}

static void listar(Sistema *sistema)
{
    if (sistema->pedidos.total == 0) {
        puts("Nenhum pedido cadastrado.");
        return;
    }

    VetorPedidos lista = vetor_listar_por_data(&sistema->pedidos);
    for (size_t i = 0; i < lista.total; ++i) {
        mostrar_pedido(lista.itens[i]);
    }
    vetor_liberar(&lista);
}

static void despachar(Sistema *sistema)
{
    Pedido *pedido = heap_despachar(&sistema->pendentes);
    if (pedido == NULL) {
        puts("Nenhum pedido pendente.");
        return;
    }
    pedido->despachado = true;
    historico_registrar(&sistema->historico, pedido);
    printf("Pedido %d despachado.\n", pedido->codigo);
}

static void desfazer(Sistema *sistema)
{
    Pedido *pedido = historico_desfazer(&sistema->historico);
    if (pedido == NULL) {
        puts("Nenhum despacho para desfazer.");
        return;
    }
    pedido->despachado = false;
    heap_inserir(&sistema->pendentes, pedido);
    printf("Despacho do pedido %d desfeito.\n", pedido->codigo);
}

static void refazer(Sistema *sistema)
{
    Pedido *pedido = historico_refazer(&sistema->historico);
    if (pedido == NULL) {
        puts("Nenhum despacho para refazer.");
        return;
    }
    /* Remove pelo código porque o pedido do histórico pode estar fora da raiz do heap. */
    heap_retirar_por_codigo(&sistema->pendentes, pedido->codigo);
    pedido->despachado = true;
    printf("Despacho do pedido %d refeito.\n", pedido->codigo);
}

static void consultar_rota(Sistema *sistema)
{
    mostrar_bairros();
    int origem;
    int destino;
    if (!ler_inteiro("Bairro de origem: ", 1, TOTAL_BAIRROS, &origem) ||
        !ler_inteiro("Bairro de destino: ", 1, TOTAL_BAIRROS, &destino)) {
        return;
    }
    --origem;
    --destino;
    int ruas = grafo_menor_distancia(&sistema->mapa, origem, destino);
    if (ruas < 0) {
        puts("Nao existe caminho entre esses bairros.");
    } else {
        printf("Menor numero de ruas entre %s e %s: %d.\n", bairros[origem], bairros[destino], ruas);
    }
}

int main(void)
{
    Sistema sistema = {0};
    sistema.mapa = grafo_criar(TOTAL_BAIRROS);
    grafo_adicionar_rua(&sistema.mapa, 0, 1);
    grafo_adicionar_rua(&sistema.mapa, 0, 2);
    grafo_adicionar_rua(&sistema.mapa, 1, 3);
    grafo_adicionar_rua(&sistema.mapa, 2, 4);

    /* Cada opção do menu associa um texto à função que executa a operação. */
    const struct {
        const char *nome;
        void (*executar)(Sistema *sistema);
    } opcoes[] = {
        {"Cadastrar pedido", cadastrar},
        {"Buscar pedido por codigo", buscar},
        {"Listar pedidos por data", listar},
        {"Despachar proximo pedido", despachar},
        {"Desfazer despacho", desfazer},
        {"Refazer despacho", refazer},
        {"Consultar menor numero de ruas", consultar_rota}
    };
    enum { TOTAL_OPCOES = sizeof opcoes / sizeof opcoes[0] };

    for (;;) {
        puts("\nEntregas");
        for (int i = 0; i < TOTAL_OPCOES; ++i) {
            printf("%d - %s\n", i + 1, opcoes[i].nome);
        }
        puts("0 - Sair");

        int escolha;
        if (!ler_inteiro("Opcao: ", 0, TOTAL_OPCOES, &escolha) || escolha == 0) {
            break;
        }
        opcoes[escolha - 1].executar(&sistema);
    }

    heap_liberar(&sistema.pendentes);
    historico_liberar(&sistema.historico);
    grafo_liberar(&sistema.mapa);
    hash_liberar(&sistema.codigos);
    for (size_t i = 0; i < sistema.pedidos.total; ++i) {
        free(sistema.pedidos.itens[i]);
    }
    vetor_liberar(&sistema.pedidos);
    return EXIT_SUCCESS;
}
