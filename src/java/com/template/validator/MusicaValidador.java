package com.template.validator;

import com.template.util.DialogUtil;

import java.util.ArrayList;
import java.util.List;

public class MusicaValidador implements IMusicaValidador {

    @Override
    public boolean validarMusica(String nome, String artista, String genero, String ano) {
        // Lista genérica contendo os validadores dos campos
        List<Validador<String>> validadores = new ArrayList<>();

        // Adicionando validadores de campos obrigatórios
        validadores.add(new CamposObrigatoriosValidador("Nome", nome));
        validadores.add(new CamposObrigatoriosValidador("Artista", artista));
        validadores.add(new CamposObrigatoriosValidador("Gênero", genero));
        validadores.add(new CamposObrigatoriosValidador("Ano", ano));

        // Adicionando validador específico de regra de negócio
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
