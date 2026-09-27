package com.farmacia.creational.factory;

import com.farmacia.model.Medicamento;
import com.farmacia.model.TipoMedicamento;

/**
 * Padrão Factory Method.
 *
 * Centraliza a regra de negócio de criação: quem decide se um medicamento
 * exige receita é a fábrica, não quem está montando o pedido. Isso evita
 * duplicar essa regra pelo sistema todo.
 */
public class MedicamentoFactory {

    private MedicamentoFactory() {
    }

    public static Medicamento criar(String nome, String principioAtivo, double preco, TipoMedicamento tipo) {
        boolean exigeReceita = (tipo == TipoMedicamento.CONTROLADO);
        return new Medicamento(nome, principioAtivo, preco, tipo, exigeReceita);
    }
}
