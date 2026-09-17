package com.template.model;

import java.util.ArrayList;

public interface IMusicasDAO {

    ArrayList<MusicasDTO> listarMusicas();

    void cadastrarMusicas(MusicasDTO musica);

    void atualizarMusicas(MusicasDTO musica);

    void deletarMusicas(int id);
}
