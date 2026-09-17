package com.template.validator;

public class MusicasValidator implements IMusicaValidador {

    private final MusicaValidador validador = new MusicaValidador();

    @Override
    public boolean validarMusica(String nome, String artista, String genero, String ano) {
        return validador.validarMusica(nome, artista, genero, ano);
    }

    public static boolean validarCampos(String nome, String artista, String genero, String ano) {
        return new MusicaValidador().validarMusica(nome, artista, genero, ano);
    }
}