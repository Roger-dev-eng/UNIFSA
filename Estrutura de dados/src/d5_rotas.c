#include "d5_rotas.h"

#include <stdio.h>
#include <stdlib.h>

struct Vizinho {
    int bairro;
    Vizinho *proximo;
};

static void *alocar(size_t quantidade, size_t tamanho)
{
    void *bloco = calloc(quantidade, tamanho);
    if (bloco == NULL) {
        fputs("Memoria insuficiente para o grafo.\n", stderr);
        exit(EXIT_FAILURE);
    }
    return bloco;
}

GrafoBairros grafo_criar(int total_bairros)
{
    if (total_bairros <= 0) {
        return (GrafoBairros){0};
    }
    Vizinho **adjacencias = alocar((size_t)total_bairros, sizeof *adjacencias);
    return (GrafoBairros){adjacencias, total_bairros};
}

static bool bairro_valido(const GrafoBairros *grafo, int bairro)
{
    return bairro >= 0 && bairro < grafo->total_bairros;
}

static void ligar(GrafoBairros *grafo, int origem, int destino)
{
    Vizinho *vizinho = alocar(1, sizeof *vizinho);
    *vizinho = (Vizinho){destino, grafo->adjacencias[origem]};
    grafo->adjacencias[origem] = vizinho;
}

bool grafo_adicionar_rua(GrafoBairros *grafo, int origem, int destino)
{
    if (!bairro_valido(grafo, origem) || !bairro_valido(grafo, destino) || origem == destino) {
        return false;
    }
    for (Vizinho *vizinho = grafo->adjacencias[origem]; vizinho != NULL; vizinho = vizinho->proximo) {
        if (vizinho->bairro == destino) {
            return false;
        }
    }
    /* Cada rua de mão dupla aparece nas listas de adjacência dos dois bairros. */
    ligar(grafo, origem, destino);
    ligar(grafo, destino, origem);
    return true;
}

int grafo_menor_distancia(const GrafoBairros *grafo, int origem, int destino)
{
    if (!bairro_valido(grafo, origem) || !bairro_valido(grafo, destino)) {
        return -1;
    }

    size_t total = (size_t)grafo->total_bairros;
    int *fila = alocar(total, sizeof *fila);
    int *distancia = alocar(total, sizeof *distancia);
    /* Zero indica não visitado. Os demais valores contam ruas mais um. */
    size_t fim = 0;
    fila[fim++] = origem;
    distancia[origem] = 1;

    /* A busca em largura visita os bairros por distância crescente da origem. */
    for (size_t inicio = 0; inicio < fim; ++inicio) {
        int bairro = fila[inicio];
        if (bairro == destino) {
            break;
        }
        for (Vizinho *vizinho = grafo->adjacencias[bairro]; vizinho != NULL; vizinho = vizinho->proximo) {
            int proximo = vizinho->bairro;
            if (distancia[proximo] == 0) {
                distancia[proximo] = distancia[bairro] + 1;
                fila[fim++] = proximo;
            }
        }
    }

    int ruas = distancia[destino] - 1;
    free(distancia);
    free(fila);
    return ruas;
}

void grafo_liberar(GrafoBairros *grafo)
{
    for (int bairro = 0; bairro < grafo->total_bairros; ++bairro) {
        Vizinho *vizinho = grafo->adjacencias[bairro];
        while (vizinho != NULL) {
            Vizinho *proximo = vizinho->proximo;
            free(vizinho);
            vizinho = proximo;
        }
    }
    free(grafo->adjacencias);
    *grafo = (GrafoBairros){0};
}
