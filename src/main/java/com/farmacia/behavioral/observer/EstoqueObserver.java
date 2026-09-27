package com.farmacia.behavioral.observer;

import com.farmacia.model.Medicamento;

/**
 * Padrão Observer (papel de "Observer").
 */
public interface EstoqueObserver {
    void alertaEstoqueBaixo(Medicamento medicamento, int quantidadeAtual);
}
