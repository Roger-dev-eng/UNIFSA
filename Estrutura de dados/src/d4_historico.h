#ifndef D4_HISTORICO_H
#define D4_HISTORICO_H

#include "d2_listagem.h"

typedef struct {
    VetorPedidos feitos;
    VetorPedidos desfeitos;
} HistoricoDespachos;

void historico_registrar(HistoricoDespachos *historico, Pedido *pedido);
Pedido *historico_desfazer(HistoricoDespachos *historico);
Pedido *historico_refazer(HistoricoDespachos *historico);
void historico_liberar(HistoricoDespachos *historico);

#endif
