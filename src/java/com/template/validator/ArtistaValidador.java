package com.template.validator;

public class ArtistaValidador implements Validador<String> {

    private final String artista;

    public ArtistaValidador(String artista) {
        this.artista = artista;
    }

    @Override
    public boolean validar(String valorAtual) {
        return this.artista != null && !this.artista.trim().isEmpty();
    }

    @Override
    public String getMensagemErro() {
        return "Digite um nome de artista válido.";
    }

    @Override
    public String getValor() {
        return artista;
    }
}
