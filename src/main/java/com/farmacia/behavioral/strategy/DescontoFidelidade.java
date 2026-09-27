package com.farmacia.behavioral.strategy;

public class DescontoFidelidade implements DescontoStrategy {

    private static final double PERCENTUAL = 0.10;

    @Override
    public double calcular(double valorBruto) {
        return valorBruto * PERCENTUAL;
    }

    @Override
    public String getDescricao() {
        return "Desconto programa fidelidade (10%)";
    }
}
