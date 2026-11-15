#include "d2_listagem.h"

#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

static Pedido **redimensionar(Pedido **itens, size_t total)
{
    if (total > SIZE_MAX / sizeof *itens) {
        fputs("Vetor excedeu o tamanho maximo.\n", stderr);
        exit(EXIT_FAILURE);
    }
    Pedido **novo = realloc(itens, total * sizeof *itens);
    if (novo == NULL) {
        fputs("Memoria insuficiente para o vetor.\n", stderr);
        exit(EXIT_FAILURE);
    }
    return novo;
}

void vetor_adicionar(VetorPedidos *vetor, Pedido *pedido)
{
    if (vetor->total == vetor->capacidade) {
        size_t capacidade = vetor->capacidade == 0 ? 16 : 2 * vetor->capacidade;
        vetor->itens = redimensionar(vetor->itens, capacidade);
        vetor->capacidade = capacidade;
    }
    vetor->itens[vetor->total++] = pedido;
}

Pedido *vetor_retirar_ultimo(VetorPedidos *vetor)
{
    return vetor->total == 0 ? NULL : vetor->itens[--vetor->total];
}

void vetor_liberar(VetorPedidos *vetor)
{
    free(vetor->itens);
    *vetor = (VetorPedidos){0};
}

static bool vem_antes(const Pedido *a, const Pedido *b)
{
    return a->data < b->data || (a->data == b->data && a->codigo < b->codigo);
}

/* Ordena as duas metades e intercala os pedidos por data, com desempate por código. */
static void mergesort(Pedido **itens, Pedido **auxiliar, size_t inicio, size_t fim)
{
    if (fim - inicio < 2) {
        return;
    }

    size_t meio = inicio + (fim - inicio) / 2;
    mergesort(itens, auxiliar, inicio, meio);
    mergesort(itens, auxiliar, meio, fim);

    size_t esquerda = inicio;
    size_t direita = meio;
    for (size_t i = inicio; i < fim; ++i) {
        if (direita == fim || (esquerda < meio && !vem_antes(itens[direita], itens[esquerda]))) {
            auxiliar[i] = itens[esquerda++];
        } else {
            auxiliar[i] = itens[direita++];
        }
    }
    memcpy(itens + inicio, auxiliar + inicio, (fim - inicio) * sizeof *itens);
}

VetorPedidos vetor_listar_por_data(const VetorPedidos *vetor)
{
    if (vetor->total == 0) {
        return (VetorPedidos){0};
    }

    size_t total = vetor->total;
    /* A ordenação da cópia preserva a ordem de cadastro no vetor original. */
    VetorPedidos copia = {redimensionar(NULL, total), total, total};
    memcpy(copia.itens, vetor->itens, total * sizeof *copia.itens);
    if (total > 1) {
        Pedido **auxiliar = redimensionar(NULL, total);
        mergesort(copia.itens, auxiliar, 0, total);
        free(auxiliar);
    }
    return copia;
}
