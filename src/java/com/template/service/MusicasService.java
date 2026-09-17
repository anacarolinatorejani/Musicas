package com.template.service;

<<<<<<< HEAD
import com.template.model.IMusicasDAO;
import com.template.model.MusicasDAO;
import com.template.model.MusicasDTO;
=======
import com.template.model.dao.MusicasDAO;
import com.template.model.dto.MusicasDTO;
import com.template.validator.MusicasValidator;
>>>>>>> d5b9a2b666c04c80a3e4cd50746fcf3d36698097

import java.util.List;

public class MusicasService implements IMusicasService {

<<<<<<< HEAD
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
=======
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
>>>>>>> d5b9a2b666c04c80a3e4cd50746fcf3d36698097
    }
}