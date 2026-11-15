#include "d1_busca.h"

#include <stdio.h>
#include <stdlib.h>

static size_t localizar(const TabelaHash *tabela, int codigo)
{
    /* Em caso de colisão, a sondagem linear segue até achar o código ou uma vaga. */
    size_t posicao = (size_t)codigo % tabela->capacidade;
    while (tabela->entradas[posicao] != NULL && tabela->entradas[posicao]->codigo != codigo) {
        posicao = (posicao + 1) % tabela->capacidade;
    }
    return posicao;
}

static void ampliar(TabelaHash *tabela)
{
    size_t capacidade = tabela->capacidade == 0 ? 16 : 2 * tabela->capacidade;
    Pedido **entradas = calloc(capacidade, sizeof *entradas);
    if (entradas == NULL) {
        fputs("Memoria insuficiente para a tabela hash.\n", stderr);
        exit(EXIT_FAILURE);
    }

    TabelaHash antiga = *tabela;
    tabela->entradas = entradas;
    tabela->capacidade = capacidade;
    /* A nova capacidade muda o resto da divisão que determina cada posição inicial. */
    for (size_t i = 0; i < antiga.capacidade; ++i) {
        Pedido *pedido = antiga.entradas[i];
        if (pedido != NULL) {
            tabela->entradas[localizar(tabela, pedido->codigo)] = pedido;
        }
    }
    free(antiga.entradas);
}

bool hash_inserir(TabelaHash *tabela, Pedido *pedido)
{
    if (pedido->codigo <= 0 || hash_buscar(tabela, pedido->codigo) != NULL) {
        return false;
    }
    if (tabela->total >= tabela->capacidade - tabela->capacidade / 4) {
        ampliar(tabela);
    }
    tabela->entradas[localizar(tabela, pedido->codigo)] = pedido;
    ++tabela->total;
    return true;
}

Pedido *hash_buscar(const TabelaHash *tabela, int codigo)
{
    if (codigo <= 0 || tabela->capacidade == 0) {
        return NULL;
    }
    return tabela->entradas[localizar(tabela, codigo)];
}

void hash_liberar(TabelaHash *tabela)
{
    free(tabela->entradas);
    *tabela = (TabelaHash){0};
}
