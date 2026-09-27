package com.farmacia.creational.singleton;

import com.farmacia.behavioral.observer.EstoqueObserver;
import com.farmacia.model.Medicamento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Padrão Singleton.
 *
 * Existe um único ponto de controle do estoque da farmácia em todo o
 * sistema. Também atua como "Subject" do padrão Observer: quando a
 * quantidade de um medicamento fica abaixo do limite mínimo, avisa
 * automaticamente todos os interessados (farmacêutico, setor de compras...).
 */
public final class Estoque {

    private static final int LIMITE_MINIMO = 5;
    private static Estoque instancia;

    private final Map<String, Integer> quantidades = new HashMap<>();
    private final List<EstoqueObserver> observers = new ArrayList<>();

    private Estoque() {
    }

    public static synchronized Estoque getInstance() {
        if (instancia == null) {
            instancia = new Estoque();
        }
        return instancia;
    }

    public void registrarObserver(EstoqueObserver observer) {
        observers.add(observer);
    }

    public void repor(Medicamento medicamento, int quantidade) {
        quantidades.merge(medicamento.getNome(), quantidade, Integer::sum);
    }

    public int consultarQuantidade(Medicamento medicamento) {
        return quantidades.getOrDefault(medicamento.getNome(), 0);
    }

    public boolean possuiQuantidadeSuficiente(Medicamento medicamento, int quantidadeDesejada) {
        return consultarQuantidade(medicamento) >= quantidadeDesejada;
    }

    public void baixarEstoque(Medicamento medicamento, int quantidade) {
        int atual = consultarQuantidade(medicamento);
        int novoValor = Math.max(0, atual - quantidade);
        quantidades.put(medicamento.getNome(), novoValor);

        if (novoValor < LIMITE_MINIMO) {
            notificarObservers(medicamento, novoValor);
        }
    }

    private void notificarObservers(Medicamento medicamento, int quantidadeAtual) {
        for (EstoqueObserver observer : observers) {
            observer.alertaEstoqueBaixo(medicamento, quantidadeAtual);
        }
    }
}
