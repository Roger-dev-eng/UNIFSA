#ifndef D1_BUSCA_H
#define D1_BUSCA_H

#include <stdbool.h>
#include <stddef.h>

typedef struct {
    int codigo;
    int data; /* AAAAMMDD */
    int bairro;
    size_t chegada;
    bool urgente;
    bool despachado;
} Pedido;

typedef struct {
    Pedido **entradas;
    size_t total;
    size_t capacidade;
} TabelaHash;

/* A tabela guarda referências aos pedidos. O chamador é dono deles. */
bool hash_inserir(TabelaHash *tabela, Pedido *pedido);
Pedido *hash_buscar(const TabelaHash *tabela, int codigo);
void hash_liberar(TabelaHash *tabela);

#endif
