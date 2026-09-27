package com.farmacia.model;

public class Cliente {

    private final String nome;
    private final int idade;
    private final boolean possuiConvenio;
    private final boolean clienteFidelidade;

    public Cliente(String nome, int idade, boolean possuiConvenio, boolean clienteFidelidade) {
        this.nome = nome;
        this.idade = idade;
        this.possuiConvenio = possuiConvenio;
        this.clienteFidelidade = clienteFidelidade;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public boolean isPossuiConvenio() {
        return possuiConvenio;
    }

    public boolean isClienteFidelidade() {
        return clienteFidelidade;
    }
}
