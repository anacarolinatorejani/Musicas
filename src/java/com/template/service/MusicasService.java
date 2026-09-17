package com.template.service;

import com.template.model.dao.IMusicasDAO;
import com.template.model.dao.MusicasDAO;
import com.template.model.dto.MusicasDTO;

import java.util.ArrayList;

public class MusicasService implements IMusicasService {

    private final IMusicasDAO musicasDAO;

    public MusicasService() {
        this(new MusicasDAO());
    }

    public MusicasService(IMusicasDAO musicasDAO) {
        this.musicasDAO = musicasDAO;
    }

    @Override
    public ArrayList<MusicasDTO> listarMusicas() {
        return musicasDAO.listarMusicas();
    }

    @Override
    public void cadastrarMusica(MusicasDTO musica) {
        musicasDAO.cadastrarMusicas(musica);
    }

    @Override
    public void atualizarMusica(MusicasDTO musica) {
        musicasDAO.atualizarMusicas(musica);
    }

    @Override
    public void deletarMusica(int id) {
        musicasDAO.deletarMusicas(id);
    }

    @Override
    public MusicasDTO criarMusica(String nome, String artista, String genero, String ano) {
        MusicasDTO musica = new MusicasDTO();
        musica.setNome(nome != null ? nome.trim() : "");
        musica.setArtista(artista != null ? artista.trim() : "");
        musica.setGenero(genero != null ? genero.trim() : "");
        if (ano != null && !ano.trim().isEmpty()) {
            try {
                musica.setAno(Integer.parseInt(ano.trim()));
            } catch (NumberFormatException ignored) {
                musica.setAno(0);
            }
        }
        return musica;
    }
}