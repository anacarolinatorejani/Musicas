package com.template.service;

import com.template.model.MusicasDAO;
import com.template.model.MusicasDTO;

import java.util.ArrayList;

public class MusicasService {

    private final MusicasDAO musicasdao;

    public MusicasService() {
        this.musicasdao = new MusicasDAO();
    }

    public ArrayList<MusicasDTO> listarMusicas() {
        return musicasdao.listarMusicas();
    }

    public void cadastrarMusica(MusicasDTO musica) {
        musicasdao.cadastrarMusicas(musica);
    }

    public void atualizarMusica(MusicasDTO musica) {
        musicasdao.atualizarMusicas(musica);
    }

    public void deletarMusica(int id) {
        musicasdao.deletarMusicas(id);
    }
}