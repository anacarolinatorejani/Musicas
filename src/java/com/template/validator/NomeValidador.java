package com.template.validator;

<<<<<<< HEAD
public class NomeValidador implements Validador<String> {
=======
    public class NomeValidador implements Validador<String> {
>>>>>>> d5b9a2b666c04c80a3e4cd50746fcf3d36698097

    private final String nome;

    public NomeValidador(String nome) {
        this.nome = nome;
    }

<<<<<<< HEAD
    @Override
    public boolean validar(String valorAtual) {
        return this.nome != null && !this.nome.trim().isEmpty() && this.nome.trim().length() >= 2;
    }
=======
        @Override
        public boolean validar(String valorAtual) {
            return this.nome != null && !this.nome.trim().isEmpty();
        }
>>>>>>> d5b9a2b666c04c80a3e4cd50746fcf3d36698097

    @Override
    public String getMensagemErro() {
        return "Digite um nome válido (mínimo de 2 caracteres).";
    }

    @Override
    public String getValor() {
        return nome;
    }
}