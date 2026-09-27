package com.farmacia.behavioral.strategy;

/**
 * Padrão Strategy.
 *
 * Cada regra de desconto vira uma implementação intercambiável,
 * escolhida em tempo de execução conforme o perfil do cliente.
 */
public interface DescontoStrategy {
    double calcular(double valorBruto);
    String getDescricao();
}
