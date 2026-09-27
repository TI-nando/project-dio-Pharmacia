package com.farmacia.behavioral.observer;

import com.farmacia.model.Medicamento;

public class NotificadorFarmaceutico implements EstoqueObserver {
    @Override
    public void alertaEstoqueBaixo(Medicamento medicamento, int quantidadeAtual) {
        System.out.printf("[FARMACÊUTICO] Atenção: estoque de '%s' está baixo (%d unidades).%n",
                medicamento.getNome(), quantidadeAtual);
    }
}
