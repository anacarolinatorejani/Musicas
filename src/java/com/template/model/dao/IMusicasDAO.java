package com.template.model.dao;

import com.template.model.dto.MusicasDTO;
import java.util.ArrayList;

public interface IMusicasDAO {

    ArrayList<MusicasDTO> listarMusicas();

    void cadastrarMusicas(MusicasDTO musica);

    void atualizarMusicas(MusicasDTO musica);

    void deletarMusicas(int id);
}
