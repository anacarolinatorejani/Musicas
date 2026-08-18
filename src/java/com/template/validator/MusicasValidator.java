package com.template.validator;

public class MusicasValidator {

    public static boolean validarMusica(
            String nome,
            String artista,
            String genero,
            String ano) {

        if (nome == null || nome.trim().isEmpty() ||
                artista == null || artista.trim().isEmpty() ||
                genero == null || genero.trim().isEmpty() ||
                ano == null || ano.trim().isEmpty()) {

            return false;
        }

        return true;
    }
}