#include "d4_historico.h"

void historico_registrar(HistoricoDespachos *historico, Pedido *pedido)
{
    vetor_adicionar(&historico->feitos, pedido);
    /* Um novo despacho descarta os despachos disponíveis para refazer. */
    historico->desfeitos.total = 0;
}

/* Desfazer move o topo de feitos para desfeitos. Refazer faz o caminho inverso. */
static Pedido *mover_topo(VetorPedidos *origem, VetorPedidos *destino)
{
    Pedido *pedido = vetor_retirar_ultimo(origem);
    if (pedido != NULL) {
        vetor_adicionar(destino, pedido);
    }
    return pedido;
}

Pedido *historico_desfazer(HistoricoDespachos *historico)
{
    return mover_topo(&historico->feitos, &historico->desfeitos);
}

Pedido *historico_refazer(HistoricoDespachos *historico)
{
    return mover_topo(&historico->desfeitos, &historico->feitos);
}

void historico_liberar(HistoricoDespachos *historico)
{
    vetor_liberar(&historico->feitos);
    vetor_liberar(&historico->desfeitos);
}
