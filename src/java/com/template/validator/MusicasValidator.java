package com.template.validator;

<<<<<<< HEAD
public class MusicasValidator implements IMusicaValidador {

    private final MusicaValidador validador = new MusicaValidador();

    @Override
    public boolean validarMusica(String nome, String artista, String genero, String ano) {
        return validador.validarMusica(nome, artista, genero, ano);
    }

    public static boolean validarCampos(String nome, String artista, String genero, String ano) {
        return new MusicaValidador().validarMusica(nome, artista, genero, ano);
    }
=======
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
>>>>>>> d5b9a2b666c04c80a3e4cd50746fcf3d36698097
}