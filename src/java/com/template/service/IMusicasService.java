package com.template.service;

import com.template.model.dto.MusicasDTO;
import java.util.ArrayList;

public interface IMusicasService {

    ArrayList<MusicasDTO> listarMusicas();

    void cadastrarMusica(MusicasDTO musica);

    void atualizarMusica(MusicasDTO musica);

    void deletarMusica(int id);

    MusicasDTO criarMusica(String nome, String artista, String genero, String ano);
}
