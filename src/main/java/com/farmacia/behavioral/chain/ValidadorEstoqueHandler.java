package com.farmacia.behavioral.chain;

import com.farmacia.creational.singleton.Estoque;
import com.farmacia.model.ItemPedido;
import com.farmacia.model.Pedido;
import com.farmacia.model.PedidoInvalidoException;

public class ValidadorEstoqueHandler extends ValidadorPedido {

    @Override
    protected void verificar(Pedido pedido) {
        Estoque estoque = Estoque.getInstance();
        for (ItemPedido item : pedido.getItens()) {
            if (!estoque.possuiQuantidadeSuficiente(item.getMedicamento(), item.getQuantidade())) {
                throw new PedidoInvalidoException(
                        "Estoque insuficiente para: " + item.getMedicamento().getNome());
            }
        }
    }
}
