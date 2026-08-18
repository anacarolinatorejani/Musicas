package com.template.service;

import com.template.model.MusicasDAO;
import com.template.model.MusicasDTO;

import java.util.ArrayList;

public class MusicasService {

    private final MusicasDAO dao;

    public MusicasService() {
        this.dao = new MusicasDAO();
    }

    public ArrayList<MusicasDTO> listarMusicas() {
        return dao.listarMusicas();
    }

    public void cadastrarMusica(MusicasDTO musica) {
        dao.cadastrarMusicas(musica);
    }

    public void atualizarMusica(MusicasDTO musica) {
        dao.atualizarMusicas(musica);
    }

    public void deletarMusica(int id) {
        dao.deletarMusicas(id);
    }
}