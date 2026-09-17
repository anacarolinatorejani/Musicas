package com.template.validator;

public class NomeValidador implements Validador<String> {

    private final String nome;

    public NomeValidador(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean validar(String valorAtual) {
        return this.nome != null && !this.nome.trim().isEmpty() && this.nome.trim().length() >= 2;
    }

    @Override
    public String getMensagemErro() {
        return "Digite um nome válido (mínimo de 2 caracteres).";
    }

    @Override
    public String getValor() {
        return nome;
    }
}