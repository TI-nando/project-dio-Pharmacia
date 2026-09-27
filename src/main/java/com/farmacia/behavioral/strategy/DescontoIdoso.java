package com.farmacia.behavioral.strategy;

public class DescontoIdoso implements DescontoStrategy {

    private static final double PERCENTUAL = 0.15;

    @Override
    public double calcular(double valorBruto) {
        return valorBruto * PERCENTUAL;
    }

    @Override
    public String getDescricao() {
        return "Desconto idoso (15%)";
    }
}
