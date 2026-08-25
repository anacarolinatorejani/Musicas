package com.template.validator;

import java.util.ArrayList;
import java.util.List;

import static com.template.util.DialogUtil.showError;

public class MusicasValidator {

    public static boolean validarMusica(
            String nome,
            String artista,
            String genero,
            String ano) {

        List<Validador<String>> validadores = new ArrayList<>();

        validadores.add(
                new CampoObrigatorioValidador("Nome", nome)
        );
        validadores.add(
                new CampoObrigatorioValidador("Artista", artista)
        );
        validadores.add(
                new CampoObrigatorioValidador("Gênero", genero)
        );
        validadores.add(
                new CampoObrigatorioValidador("Ano", ano)
        );
        validadores.add(
                new NomeValidador(nome)
        );
        for (Validador<String> validador : validadores) {
            if (!validador.validar(validador.getValor())) {
                showError(validador.getMensagemErro());
                return false;
            }
        }
        return true;
    }

    public static boolean validarCampos(
            String nome,
            String artista,
            String genero,
            String ano) {

        return validarMusica(
                nome,
                artista,
                genero,
                ano
        );
    }
}