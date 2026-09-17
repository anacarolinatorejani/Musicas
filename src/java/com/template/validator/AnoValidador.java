package com.template.validator;

public class AnoValidador implements Validador<String> {

    private final String ano;

    public AnoValidador(String ano) {
        this.ano = ano;
    }

    @Override
    public boolean validar(String valorAtual) {
        if (ano == null || ano.trim().isEmpty()) {
            return false;
        }
        try {
            int anoInt = Integer.parseInt(ano.trim());
            return anoInt >= 1500 && anoInt <= 2030;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String getMensagemErro() {
        return "O campo Ano deve conter apenas números válidos (entre 1500 e 2030).";
    }

    @Override
    public String getValor() {
        return ano;
    }
}
