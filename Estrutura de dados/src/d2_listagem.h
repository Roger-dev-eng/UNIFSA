#ifndef D2_LISTAGEM_H
#define D2_LISTAGEM_H

#include "d1_busca.h"

typedef struct {
    Pedido **itens;
    size_t total;
    size_t capacidade;
} VetorPedidos;

/* O vetor guarda referências e não libera os pedidos. */
void vetor_adicionar(VetorPedidos *vetor, Pedido *pedido);
Pedido *vetor_retirar_ultimo(VetorPedidos *vetor);
void vetor_liberar(VetorPedidos *vetor);

/* Retorna uma cópia ordenada do vetor. O chamador deve liberá-la. */
VetorPedidos vetor_listar_por_data(const VetorPedidos *vetor);

#endif
