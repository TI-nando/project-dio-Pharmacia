package com.farmacia.behavioral.chain;

import com.farmacia.model.ItemPedido;
import com.farmacia.model.Pedido;
import com.farmacia.model.PedidoInvalidoException;
import com.farmacia.model.TipoMedicamento;

public class ValidadorIdadeHandler extends ValidadorPedido {

    private static final int IDADE_MINIMA_CONTROLADO = 18;

    @Override
    protected void verificar(Pedido pedido) {
        boolean possuiControlado = pedido.getItens().stream()
                .map(ItemPedido::getMedicamento)
                .anyMatch(m -> m.getTipo() == TipoMedicamento.CONTROLADO);

        if (possuiControlado && pedido.getCliente().getIdade() < IDADE_MINIMA_CONTROLADO) {
            throw new PedidoInvalidoException(
                    "Cliente menor de idade não pode comprar medicamento controlado.");
        }
    }
}
