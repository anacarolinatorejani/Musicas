package com.template.service;

import com.template.model.dao.MusicasDAO;
import com.template.model.dto.MusicasDTO;
import com.template.validator.MusicasValidator;

import java.util.List;

public class MusicasService {

    private final MusicasDAO dao = new MusicasDAO();

    public void salvar(
            String nome,
            String genero,
            String artista,
            String ano
    ) {
        MusicasDTO musica = new MusicasDTO();
        musica.setNome(nome.trim());
        musica.setGenero(genero.trim());
        musica.setArtista(artista.trim());
        musica.setAno(Integer.parseInt(ano.trim()));

        dao.cadastrarMusicas(musica);
    }

    public void editar(
            int id,
            String nome,
            String genero,
            String artista,
            String ano
    ) {
        MusicasDTO musica = new MusicasDTO();
        musica.setId(id);
        musica.setNome(nome.trim());
        musica.setGenero(genero.trim());
        musica.setArtista(artista.trim());
        musica.setAno(Integer.parseInt(ano.trim()));

        // Altere "atualizar" para o nome do método de edição do seu DAO
        dao.atualizarMusicas(musica);
    }

    public void deletar(int id) {
        // Altere "deletar" para o nome do método de exclusão do seu DAO (ex: excluir)
        dao.deletarMusicas(id);
    }

    public List<MusicasDTO> listar() {
        return dao.listarMusicas();
    }
}