package com.farmacia.behavioral.chain;

import com.farmacia.model.Pedido;

/**
 * Padrão Chain of Responsibility.
 *
 * Cada validador cuida de UMA regra de negócio e repassa o pedido
 * para o próximo elo da corrente. Assim é fácil adicionar, remover ou
 * reordenar validações sem mexer nas demais.
 */
public abstract class ValidadorPedido {

    private ValidadorPedido proximo;

    public ValidadorPedido encadear(ValidadorPedido proximo) {
        this.proximo = proximo;
        return proximo;
    }

    public final void validar(Pedido pedido) {
        verificar(pedido);
        if (proximo != null) {
            proximo.validar(pedido);
        }
    }

    protected abstract void verificar(Pedido pedido);
}
