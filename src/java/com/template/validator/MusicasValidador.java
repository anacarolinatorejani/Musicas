package com.template.validator;

import com.template.util.DialogUtil;

import java.util.ArrayList;
import java.util.List;

public class MusicasValidador implements IMusicaValidador {

    @Override
    public boolean validarMusica(String nome, String artista, String genero, String ano) {
        // Lista genérica contendo os validadores dos campos
        List<Validador<String>> validadores = new ArrayList<>();

        // Validadores de campo obrigatório (verificam se não estão vazios)
        validadores.add(new CamposObrigatoriosValidador("Nome", nome));
        validadores.add(new CamposObrigatoriosValidador("Artista", artista));
        validadores.add(new CamposObrigatoriosValidador("Gênero", genero));
        validadores.add(new CamposObrigatoriosValidador("Ano", ano));

        // Validadores de regra de negócio específica por campo
        validadores.add(new NomeValidador(nome));
        validadores.add(new ArtistaValidador(artista));
        validadores.add(new GeneroValidador(genero));
        validadores.add(new AnoValidador(ano));

        // Itera sobre a lista de validadores obrigatoriamente com foreach
        for (Validador<String> validador : validadores) {
            // Cada validador testa seu valor específico utilizando métodos da interface Validador
            if (!validador.validar(validador.getValor())) {
                DialogUtil.showWarning(validador.getMensagemErro());
                return false; // Interrompe na primeira validação que falhar
            }
        }

        return true; // Todos os validadores foram atendidos com sucesso
    }
}
