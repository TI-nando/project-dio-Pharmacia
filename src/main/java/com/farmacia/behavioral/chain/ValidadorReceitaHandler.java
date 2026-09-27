package com.farmacia.behavioral.chain;

import com.farmacia.model.ItemPedido;
import com.farmacia.model.Pedido;
import com.farmacia.model.PedidoInvalidoException;

public class ValidadorReceitaHandler extends ValidadorPedido {

    @Override
    protected void verificar(Pedido pedido) {
        boolean possuiItemControlado = pedido.getItens().stream()
                .map(ItemPedido::getMedicamento)
                .anyMatch(m -> m.isExigeReceita());

        if (possuiItemControlado && !pedido.isReceitaApresentada()) {
            throw new PedidoInvalidoException(
                    "Pedido contém medicamento controlado e nenhuma receita foi apresentada.");
        }
    }
}
