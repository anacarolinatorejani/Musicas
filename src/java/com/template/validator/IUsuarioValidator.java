package com.template.validator;

public interface IUsuarioValidator {
    boolean validarUsuario(String nome, String genero, String ano, String artista);
    boolean validarNome(String nome);
    boolean validarGenero(String genero);
    boolean validarAno(String ano);
    boolean validarArtista(String artista);
}
