package com.farmacia.model;

public class ItemPedido {

    private final Medicamento medicamento;
    private final int quantidade;

    public ItemPedido(Medicamento medicamento, int quantidade) {
        this.medicamento = medicamento;
        this.quantidade = quantidade;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getSubtotal() {
        return medicamento.getPreco() * quantidade;
    }

    @Override
    public String toString() {
        return String.format("%dx %s = R$ %.2f", quantidade, medicamento.getNome(), getSubtotal());
    }
}
