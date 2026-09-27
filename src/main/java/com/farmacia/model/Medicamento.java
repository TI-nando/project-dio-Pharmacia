package com.farmacia.model;

public class Medicamento {

    private final String nome;
    private final String principioAtivo;
    private final double preco;
    private final TipoMedicamento tipo;
    private final boolean exigeReceita;

    public Medicamento(String nome, String principioAtivo, double preco,
                        TipoMedicamento tipo, boolean exigeReceita) {
        this.nome = nome;
        this.principioAtivo = principioAtivo;
        this.preco = preco;
        this.tipo = tipo;
        this.exigeReceita = exigeReceita;
    }

    public String getNome() {
        return nome;
    }

    public String getPrincipioAtivo() {
        return principioAtivo;
    }

    public double getPreco() {
        return preco;
    }

    public TipoMedicamento getTipo() {
        return tipo;
    }

    public boolean isExigeReceita() {
        return exigeReceita;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - R$ %.2f [%s]%s",
                nome, principioAtivo, preco, tipo,
                exigeReceita ? " [RECEITA OBRIGATÓRIA]" : "");
    }
}
