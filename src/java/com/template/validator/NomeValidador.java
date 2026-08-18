package com.template.validator;

    class NomeValidador implements Validador<String> {

        private final String nome;

        public NomeValidador(String nome) {
            this.nome = nome;
        }

        @Override
        public boolean validar(String valorAtual) {
            // O valor do parâmetro é o que será validado neste ciclo
            return this.nome != null && !this.nome.trim().isEmpty();
        }

        @Override
        public String getMensagemErro() {
            return "Digite um nome válido.";
        }

        @Override
        public String getValor() {
            return nome;
        }
    }