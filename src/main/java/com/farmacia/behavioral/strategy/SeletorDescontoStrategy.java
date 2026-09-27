package com.farmacia.behavioral.strategy;

import com.farmacia.model.Cliente;

/**
 * Decide qual DescontoStrategy usar conforme o perfil do cliente.
 * Prioridade: convênio > idoso > fidelidade > sem desconto.
 * (A escolha em si não é o padrão; o padrão está nas estratégias plugáveis.)
 */
public class SeletorDescontoStrategy {

    private static final int IDADE_IDOSO = 60;

    public DescontoStrategy selecionar(Cliente cliente) {
        if (cliente.isPossuiConvenio()) {
            return new DescontoConvenio();
        }
        if (cliente.getIdade() >= IDADE_IDOSO) {
            return new DescontoIdoso();
        }
        if (cliente.isClienteFidelidade()) {
            return new DescontoFidelidade();
        }
        return new SemDesconto();
    }
}
