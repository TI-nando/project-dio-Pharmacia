package com.farmacia.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedido {

    private final Cliente cliente;
    private final List<ItemPedido> itens;
    private final boolean receitaApresentada;
    private double descontoAplicado;

    public Pedido(Cliente cliente, List<ItemPedido> itens, boolean receitaApresentada) {
        this.cliente = cliente;
        this.itens = new ArrayList<>(itens);
        this.receitaApresentada = receitaApresentada;
        this.descontoAplicado = 0d;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemPedido> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public boolean isReceitaApresentada() {
        return receitaApresentada;
    }

    public double getValorBruto() {
        return itens.stream().mapToDouble(ItemPedido::getSubtotal).sum();
    }

    public double getDescontoAplicado() {
        return descontoAplicado;
    }

    public void setDescontoAplicado(double descontoAplicado) {
        this.descontoAplicado = descontoAplicado;
    }

    public double getValorFinal() {
        return getValorBruto() - descontoAplicado;
    }
}
