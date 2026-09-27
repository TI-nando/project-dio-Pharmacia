package com.farmacia.creational.builder;

import com.farmacia.model.Cliente;
import com.farmacia.model.ItemPedido;
import com.farmacia.model.Medicamento;
import com.farmacia.model.Pedido;

import java.util.ArrayList;
import java.util.List;

/**
 * Padrão Builder.
 *
 * Permite montar um Pedido passo a passo (cliente -> itens -> receita)
 * com uma API fluente, sem precisar de um construtor telescópico gigante.
 */
public class PedidoBuilder {

    private Cliente cliente;
    private final List<ItemPedido> itens = new ArrayList<>();
    private boolean receitaApresentada = false;

    public PedidoBuilder paraCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public PedidoBuilder adicionarItem(Medicamento medicamento, int quantidade) {
        this.itens.add(new ItemPedido(medicamento, quantidade));
        return this;
    }

    public PedidoBuilder comReceitaApresentada(boolean apresentada) {
        this.receitaApresentada = apresentada;
        return this;
    }

    public Pedido build() {
        if (cliente == null) {
            throw new IllegalStateException("Não é possível montar um pedido sem cliente.");
        }
        if (itens.isEmpty()) {
            throw new IllegalStateException("Não é possível montar um pedido sem itens.");
        }
        return new Pedido(cliente, itens, receitaApresentada);
    }
}
