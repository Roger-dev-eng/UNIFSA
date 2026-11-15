#ifndef D5_ROTAS_H
#define D5_ROTAS_H

#include <stdbool.h>

typedef struct Vizinho Vizinho;

typedef struct {
    Vizinho **adjacencias;
    int total_bairros;
} GrafoBairros;

GrafoBairros grafo_criar(int total_bairros);
bool grafo_adicionar_rua(GrafoBairros *grafo, int origem, int destino);
/* Retorna -1 quando não há caminho entre os bairros. */
int grafo_menor_distancia(const GrafoBairros *grafo, int origem, int destino);
void grafo_liberar(GrafoBairros *grafo);

#endif
