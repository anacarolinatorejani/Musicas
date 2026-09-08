package com.template.validator;

import java.util.ArrayList;
import java.util.List;

public class MusicasValidator implements IMusicasValidator {

    @Override
    public String validarMusica(String nome, String artista, String genero, String ano) {
        List<Validador<String>> validadores = new ArrayList<>();

        validadores.add(new CampoObrigatorioValidador(nome, "Nome"));
        validadores.add(new CampoObrigatorioValidador(artista, "Artista"));
        validadores.add(new CampoObrigatorioValidador(genero, "Gênero"));
        validadores.add(new CampoObrigatorioValidador(ano, "Ano"));

        validadores.add(new NomeValidador(nome));
        validadores.add(new ArtistaValidador(artista));
        validadores.add(new GeneroValidador(genero));
        validadores.add(new AnoValidador(ano));

        for (Validador<String> validador : validadores) {
            if (!validador.validar(validador.getValor())) {
                return validador.getMensagemErro();
            }
        }

        return null;
    }
}