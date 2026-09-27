package com.farmacia.behavioral.observer;

import com.farmacia.model.Medicamento;

public class NotificadorCompras implements EstoqueObserver {
    @Override
    public void alertaEstoqueBaixo(Medicamento medicamento, int quantidadeAtual) {
        System.out.printf("[COMPRAS] Solicitar reposição de '%s' (restam %d unidades).%n",
                medicamento.getNome(), quantidadeAtual);
    }
}
