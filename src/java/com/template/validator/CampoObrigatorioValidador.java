package com.template.validator;

<<<<<<< HEAD
public class CampoObrigatorioValidador extends CamposObrigatoriosValidador {

    public CampoObrigatorioValidador(String nomeCampo, String valor) {
        super(nomeCampo, valor);
=======
public class CampoObrigatorioValidador implements Validador<String> {

    private final String nomeCampo;
    private final String valor;

    public CampoObrigatorioValidador(String nomeCampo, String valor) {
        this.nomeCampo = nomeCampo;
        this.valor = valor;
    }

    @Override
    public boolean validar(String valorAtual) {
        return this.valor != null && !this.valor.trim().isEmpty();
    }

    @Override
    public String getMensagemErro() {
        return "O campo " + nomeCampo + " deve ser preenchido.";
    }

    @Override
    public String getValor() {
        return valor;
>>>>>>> d5b9a2b666c04c80a3e4cd50746fcf3d36698097
    }
}