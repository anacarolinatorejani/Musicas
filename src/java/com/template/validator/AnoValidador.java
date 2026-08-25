package com.template.validator;

public class AnoValidador implements Validador<String> {

    private final String valor;

    public AnoValidador(String valor) {
        this.valor = valor;
    }

    @Override
    public boolean validar(String valor) {
        try {
            int ano = Integer.parseInt(valor);
            return ano > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String getMensagemErro() {
        return "O ano deve ser um número válido.";
    }

    @Override
    public String getValor() {
        return valor;
    }
}