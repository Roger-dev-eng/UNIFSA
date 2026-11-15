#include "d3_despacho.h"

/* Urgentes saem primeiro. Na mesma prioridade, vale a ordem de chegada. */
static bool tem_prioridade(const Pedido *a, const Pedido *b)
{
    if (a->urgente != b->urgente) {
        return a->urgente;
    }
    return a->chegada < b->chegada;
}

static void subir(VetorPedidos *vetor, size_t posicao, Pedido *pedido)
{
    while (posicao > 0) {
        size_t pai = (posicao - 1) / 2;
        if (!tem_prioridade(pedido, vetor->itens[pai])) {
            break;
        }
        vetor->itens[posicao] = vetor->itens[pai];
        posicao = pai;
    }
    vetor->itens[posicao] = pedido;
}

static void descer(VetorPedidos *vetor, size_t posicao, Pedido *pedido)
{
    while (posicao < vetor->total / 2) {
        size_t filho = 2 * posicao + 1;
        size_t direita = filho + 1;
        if (direita < vetor->total && tem_prioridade(vetor->itens[direita], vetor->itens[filho])) {
            filho = direita;
        }
        if (!tem_prioridade(vetor->itens[filho], pedido)) {
            break;
        }
        vetor->itens[posicao] = vetor->itens[filho];
        posicao = filho;
    }
    vetor->itens[posicao] = pedido;
}

void heap_inserir(HeapDespacho *heap, Pedido *pedido)
{
    vetor_adicionar(&heap->pedidos, pedido);
    subir(&heap->pedidos, heap->pedidos.total - 1, pedido);
}

static Pedido *retirar(VetorPedidos *vetor, size_t posicao)
{
    Pedido *retirado = vetor->itens[posicao];
    Pedido *ultimo = vetor_retirar_ultimo(vetor);
    /* O último item preenche a vaga e sobe ou desce para restaurar a ordem do heap. */
    if (posicao < vetor->total) {
        if (posicao > 0 && tem_prioridade(ultimo, vetor->itens[(posicao - 1) / 2])) {
            subir(vetor, posicao, ultimo);
        } else {
            descer(vetor, posicao, ultimo);
        }
    }
    return retirado;
}

Pedido *heap_despachar(HeapDespacho *heap)
{
    return heap->pedidos.total == 0 ? NULL : retirar(&heap->pedidos, 0);
}

Pedido *heap_retirar_por_codigo(HeapDespacho *heap, int codigo)
{
    for (size_t i = 0; i < heap->pedidos.total; ++i) {
        if (heap->pedidos.itens[i]->codigo == codigo) {
            return retirar(&heap->pedidos, i);
        }
    }
    return NULL;
}

void heap_liberar(HeapDespacho *heap)
{
    vetor_liberar(&heap->pedidos);
}
