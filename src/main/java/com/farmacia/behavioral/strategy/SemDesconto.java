package com.farmacia.behavioral.strategy;

public class SemDesconto implements DescontoStrategy {
    @Override
    public double calcular(double valorBruto) {
        return 0d;
    }

    @Override
    public String getDescricao() {
        return "Sem desconto";
    }
}
