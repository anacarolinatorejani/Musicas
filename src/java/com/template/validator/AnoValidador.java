package com.template.validator;

public class AnoValidador implements Validador<String> {

<<<<<<< HEAD
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
=======
    private final String valor;

    public AnoValidador(String valor) {
        this.valor = valor;
    }

    @Override
    public boolean validar(String valor) {
        try {
            int ano = Integer.parseInt(valor);
            return ano > 0;
>>>>>>> d5b9a2b666c04c80a3e4cd50746fcf3d36698097
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String getMensagemErro() {
<<<<<<< HEAD
        return "Digite um ano válido de lançamento (entre 1500 e 2030).";
=======
        return "O ano deve ser um número válido.";
>>>>>>> d5b9a2b666c04c80a3e4cd50746fcf3d36698097
    }

    @Override
    public String getValor() {
<<<<<<< HEAD
        return ano;
    }
}
=======
        return valor;
    }
}
>>>>>>> d5b9a2b666c04c80a3e4cd50746fcf3d36698097
