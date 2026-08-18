package com.template.validator;

import static com.template.util.DialogUtil.showError;

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

    public static boolean validarCampos(String nome, String artista, String genero, String ano) {

        boolean valido = MusicasValidator.validarMusica(
                nome,
                artista,
                genero,
                ano
        );

        if (!valido) {
            showError(
                    "Por favor, preencha todos os campos obrigatórios."
            );
        }

        return valido;
    }

}