package com.farmacia.behavioral.strategy;

public class DescontoConvenio implements DescontoStrategy {

    private static final double PERCENTUAL = 0.20;

    @Override
    public double calcular(double valorBruto) {
        return valorBruto * PERCENTUAL;
    }

    @Override
    public String getDescricao() {
        return "Desconto convênio (20%)";
    }
}
