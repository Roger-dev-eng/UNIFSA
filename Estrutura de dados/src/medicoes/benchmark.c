#include "d1_busca.h"
#include "d2_listagem.h"
#include "d3_despacho.h"
#include "d4_historico.h"
#include "d5_rotas.h"

#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <windows.h>

#define VERIFICAR(condicao) do { if (!(condicao)) { \
    fprintf(stderr, "Falha no benchmark, linha %d.\n", __LINE__); exit(EXIT_FAILURE); \
} } while (0)

enum { REP = 7, LISTAGENS = 8, ROTAS = 8 };

typedef struct {
    int n;
    Pedido **entrada;
    Pedido *base;
    int *consultas;
} Amostra;

typedef struct {
    double preparo;
    double operacao;
    uint64_t assinatura;
} Resultado;

static LARGE_INTEGER frequencia;

static int64_t agora(void) {
    LARGE_INTEGER ticks;
    VERIFICAR(QueryPerformanceCounter(&ticks));
    return ticks.QuadPart;
}

static double us(int64_t inicio, int64_t fim, size_t operacoes) {
    return (double)(fim - inicio) * 1e6 / (double)frequencia.QuadPart / (double)operacoes;
}

static uint32_t sortear(uint32_t *estado) {
    uint32_t x = *estado;
    x ^= x << 13;
    x ^= x >> 17;
    x ^= x << 5;
    return *estado = x;
}

static uint64_t somar(uint64_t estado, int valor) {
    return estado * UINT64_C(1099511628211) + (uint64_t)valor;
}

static Amostra gerar(int n, int perfil) {
    Amostra a = {n, malloc((size_t)n * sizeof *a.entrada),
                 calloc((size_t)n, sizeof *a.base), malloc((size_t)n * sizeof *a.consultas)};
    VERIFICAR(a.entrada && a.base && a.consultas);
    uint32_t prioridades = 20261115;
    for (int i = 0; i < n; ++i) {
        int dia = i / 2;
        int data = (2026 + dia / 336) * 10000 + (1 + dia / 28 % 12) * 100 + 1 + dia % 28;
        a.base[i] = (Pedido){.codigo = i + 1, .data = data, .urgente = sortear(&prioridades) % 4 == 0};
        a.entrada[i] = &a.base[i];
    }

    uint32_t ordem = 1729;
    if (perfil == 0) {
        for (int i = n - 1; i > 0; --i) {
            size_t j = sortear(&ordem) % (uint32_t)(i + 1);
            Pedido *tmp = a.entrada[i];
            a.entrada[i] = a.entrada[j];
            a.entrada[j] = tmp;
        }
    } else if (perfil == 2) {
        for (int i = 0; i < n / 20; ++i) {
            size_t j = sortear(&ordem) % (uint32_t)(n - 1);
            Pedido *tmp = a.entrada[j];
            a.entrada[j] = a.entrada[j + 1];
            a.entrada[j + 1] = tmp;
        }
    }
    uint32_t buscas = 1987;
    for (int i = 0; i < n; ++i) {
        a.entrada[i]->chegada = (size_t)i;
        a.consultas[i] = 1 + (int)(sortear(&buscas) % (uint32_t)n) + (i % 4 == 0 ? n : 0);
    }
    return a;
}

static Pedido *busca_linear(const VetorPedidos *vetor, int codigo) {
    for (size_t i = 0; i < vetor->total; ++i) {
        if (vetor->itens[i]->codigo == codigo) return vetor->itens[i];
    }
    return NULL;
}

static Resultado busca(const Amostra *a, bool atual) {
    TabelaHash tabela = {0};
    VetorPedidos vetor = {0};
    int64_t inicio = agora();
    for (int i = 0; i < a->n; ++i) {
        Pedido *p = a->entrada[i];
        if (atual) {
            VERIFICAR(hash_inserir(&tabela, p));
        } else {
            VERIFICAR(busca_linear(&vetor, p->codigo) == NULL);
        }
        vetor_adicionar(&vetor, p);
    }
    int64_t meio = agora();
    uint64_t assinatura = 0;
    for (int i = 0; i < a->n; ++i) {
        int codigo = a->consultas[i];
        Pedido *p = atual ? hash_buscar(&tabela, codigo) : busca_linear(&vetor, codigo);
        assinatura = somar(assinatura, p ? p->codigo : 0);
    }
    int64_t fim = agora();
    hash_liberar(&tabela);
    vetor_liberar(&vetor);
    return (Resultado){us(inicio, meio, (size_t)a->n), us(meio, fim, (size_t)a->n), assinatura};
}

static void inserir_ordenado(VetorPedidos *vetor, Pedido *p) {
    size_t esquerda = 0;
    size_t direita = vetor->total;
    while (esquerda < direita) {
        size_t meio = esquerda + (direita - esquerda) / 2;
        Pedido *m = vetor->itens[meio];
        if (m->data < p->data || (m->data == p->data && m->codigo < p->codigo)) {
            esquerda = meio + 1;
        } else {
            direita = meio;
        }
    }
    vetor_adicionar(vetor, p);
    memmove(vetor->itens + esquerda + 1, vetor->itens + esquerda,
            (vetor->total - 1 - esquerda) * sizeof *vetor->itens);
    vetor->itens[esquerda] = p;
}

static Resultado listagem(const Amostra *a, bool atual) {
    VetorPedidos vetor = {0};
    int64_t inicio = agora();
    for (int i = 0; i < a->n; ++i) {
        if (atual) vetor_adicionar(&vetor, a->entrada[i]);
        else inserir_ordenado(&vetor, a->entrada[i]);
    }
    int64_t meio = agora();
    uint64_t assinatura = 0;
    for (int i = 0; i < LISTAGENS; ++i) {
        VetorPedidos copia = atual ? vetor_listar_por_data(&vetor) : vetor;
        for (size_t j = 0; j < copia.total; ++j) assinatura = somar(assinatura, copia.itens[j]->codigo);
        if (atual) vetor_liberar(&copia);
    }
    int64_t fim = agora();
    vetor_liberar(&vetor);
    return (Resultado){us(inicio, meio, (size_t)a->n), us(meio, fim, LISTAGENS), assinatura};
}

static bool prioriza(Pedido *a, Pedido *b) {
    return a->urgente == b->urgente ? a->chegada < b->chegada : a->urgente;
}

static Pedido *despachar_vetor(VetorPedidos *vetor) {
    size_t melhor = 0;
    for (size_t i = 1; i < vetor->total; ++i) {
        if (prioriza(vetor->itens[i], vetor->itens[melhor])) melhor = i;
    }
    Pedido *p = vetor->itens[melhor];
    vetor->itens[melhor] = vetor->itens[--vetor->total];
    return p;
}

static Resultado despacho(const Amostra *a, bool atual) {
    HeapDespacho heap = {0};
    VetorPedidos vetor = {0};
    int64_t inicio = agora();
    for (int i = 0; i < a->n; ++i) {
        if (atual) heap_inserir(&heap, a->entrada[i]);
        else vetor_adicionar(&vetor, a->entrada[i]);
    }
    int64_t meio = agora();
    uint64_t assinatura = 0;
    for (int i = 0; i < a->n; ++i) {
        Pedido *p = atual ? heap_despachar(&heap) : despachar_vetor(&vetor);
        VERIFICAR(p);
        assinatura = somar(assinatura, p->codigo);
    }
    int64_t fim = agora();
    heap_liberar(&heap);
    vetor_liberar(&vetor);
    return (Resultado){us(inicio, meio, (size_t)a->n), us(meio, fim, (size_t)a->n), assinatura};
}

static Resultado historico(const Amostra *a, bool atual) {
    HistoricoDespachos pilhas = {0};
    VetorPedidos vetor = {0};
    size_t cursor = 0;
    int64_t inicio = agora();
    for (int i = 0; i < a->n; ++i) {
        if (atual) {
            historico_registrar(&pilhas, a->entrada[i]);
        } else {
            vetor.total = cursor;
            vetor_adicionar(&vetor, a->entrada[i]);
            ++cursor;
        }
    }
    int64_t meio = agora();
    uint64_t assinatura = 0;
    for (int i = 0; i < a->n; ++i) {
        Pedido *p = atual ? historico_desfazer(&pilhas) : vetor.itens[--cursor];
        VERIFICAR(p);
        assinatura = somar(assinatura, p->codigo);
    }
    for (int i = 0; i < a->n; ++i) {
        Pedido *p = atual ? historico_refazer(&pilhas) : vetor.itens[cursor++];
        VERIFICAR(p);
        assinatura = somar(assinatura, p->codigo);
    }
    int64_t fim = agora();
    historico_liberar(&pilhas);
    vetor_liberar(&vetor);
    return (Resultado){us(inicio, meio, (size_t)a->n), us(meio, fim, (size_t)2 * (size_t)a->n), assinatura};
}

static int bfs_matriz(const unsigned char *matriz, size_t n, int origem, int destino) {
    int *dist = calloc(n, sizeof *dist);
    int *fila = calloc(n, sizeof *fila);
    VERIFICAR(dist && fila);
    size_t fim = 0;
    fila[fim++] = origem;
    dist[origem] = 1;
    for (size_t i = 0; i < fim; ++i) {
        int vertice = fila[i];
        if (vertice == destino) break;
        const unsigned char *linha = matriz + (size_t)vertice * n;
        for (size_t vizinho = 0; vizinho < n; ++vizinho) {
            if (linha[vizinho] && dist[vizinho] == 0) {
                dist[vizinho] = dist[vertice] + 1;
                fila[fim++] = (int)vizinho;
            }
        }
    }
    int ruas = dist[destino] - 1;
    free(fila);
    free(dist);
    return ruas;
}

static Resultado rotas(const Amostra *a, bool atual) {
    size_t n = (size_t)a->n;
    GrafoBairros grafo = {0};
    unsigned char *matriz = NULL;
    int64_t inicio = agora();
    if (atual) {
        grafo = grafo_criar(a->n);
    } else {
        matriz = calloc(n, n);
        VERIFICAR(matriz);
    }
    for (int i = 0; i < a->n; ++i) {
        int origem = a->entrada[i]->codigo - 1;
        const int saltos[] = {1, 7};
        for (int j = 0; j < 2; ++j) {
            int destino = (origem + saltos[j]) % a->n;
            if (atual) {
                VERIFICAR(grafo_adicionar_rua(&grafo, origem, destino));
            } else {
                size_t ida = (size_t)origem * n + (size_t)destino;
                size_t volta = (size_t)destino * n + (size_t)origem;
                VERIFICAR(matriz[ida] == 0);
                matriz[ida] = matriz[volta] = 1;
            }
        }
    }
    int64_t meio = agora();
    uint64_t assinatura = 0;
    for (int i = 0; i < ROTAS; ++i) {
        int origem = i * a->n / ROTAS;
        int destino = (origem + a->n / (i + 3)) % a->n;
        int dist = atual ? grafo_menor_distancia(&grafo, origem, destino)
                         : bfs_matriz(matriz, n, origem, destino);
        VERIFICAR(dist >= 0);
        assinatura = somar(assinatura, dist);
    }
    int64_t fim = agora();
    grafo_liberar(&grafo);
    free(matriz);
    return (Resultado){us(inicio, meio, 2 * n), us(meio, fim, ROTAS), assinatura};
}

static double mediana(double vet[REP]) {
    for (int i = 1; i < REP; ++i) {
        double valor = vet[i];
        int j = i;
        while (j > 0 && vet[j - 1] > valor) {
            vet[j] = vet[j - 1];
            --j;
        }
        vet[j] = valor;
    }
    return vet[REP / 2];
}

int main(void) {
    VERIFICAR(QueryPerformanceFrequency(&frequencia));
    Resultado (*medir[])(const Amostra *, bool) = {busca, listagem, despacho, historico, rotas};
    const int tamanhos[] = {100, 1000, 10000};
    const char *perfis[] = {"A", "O", "Q"};
    puts("demanda,n,perfil,preparo_atual,preparo_alternativa,operacao_atual,operacao_alternativa");
    for (int t = 0; t < 3; ++t) {
        for (int p = 0; p < 3; ++p) {
            Amostra a = gerar(tamanhos[t], p);
            for (int d = 0; d < 5; ++d) {
                Resultado aquecer_atual = medir[d](&a, true);
                Resultado aquecer_alternativa = medir[d](&a, false);
                VERIFICAR(aquecer_atual.assinatura == aquecer_alternativa.assinatura);
                double preparo[2][REP], operacao[2][REP];
                for (int r = 0; r < REP; ++r) {
                    for (int vez = 0; vez < 2; ++vez) {
                        int i = (vez + r) % 2;
                        Resultado result = medir[d](&a, i == 0);
                        VERIFICAR(result.assinatura == aquecer_atual.assinatura);
                        preparo[i][r] = result.preparo;
                        operacao[i][r] = result.operacao;
                    }
                }
                printf("D%d,%d,%s,%.6f,%.6f,%.6f,%.6f\n", d + 1, a.n, perfis[p],
                       mediana(preparo[0]), mediana(preparo[1]), mediana(operacao[0]), mediana(operacao[1]));
            }
            free(a.consultas);
            free(a.entrada);
            free(a.base);
        }
    }
    return EXIT_SUCCESS;
}
