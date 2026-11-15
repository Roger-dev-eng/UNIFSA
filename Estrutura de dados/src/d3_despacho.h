#ifndef D3_DESPACHO_H
#define D3_DESPACHO_H

#include "d2_listagem.h"

typedef struct {
    VetorPedidos pedidos;
} HeapDespacho;

void heap_inserir(HeapDespacho *heap, Pedido *pedido);
Pedido *heap_despachar(HeapDespacho *heap);
Pedido *heap_retirar_por_codigo(HeapDespacho *heap, int codigo);
void heap_liberar(HeapDespacho *heap);

#endif
