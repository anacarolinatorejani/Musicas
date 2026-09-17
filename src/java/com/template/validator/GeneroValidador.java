package com.template.validator;

public class GeneroValidador implements Validador<String> {

    private final String genero;

    public GeneroValidador(String genero) {
        this.genero = genero;
    }

    @Override
    public boolean validar(String valorAtual) {
        return this.genero != null && !this.genero.trim().isEmpty();
    }

    @Override
    public String getMensagemErro() {
        return "Digite um gênero válido.";
    }

    @Override
    public String getValor() {
        return genero;
    }
}