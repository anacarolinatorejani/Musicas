package com.template.model.dao;

import com.template.model.Conexao;
import com.template.model.dto.MusicasDTO;

import com.template.util.DialogUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MusicasDAO implements IMusicasDAO {

    private static final Logger logger = Logger.getLogger(MusicasDAO.class.getName());

    @Override
    public ArrayList<MusicasDTO> listarMusicas() {
        ArrayList<MusicasDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM musicas";

        try (Connection conexao = new Conexao().conectaBD();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {

            while (resultado.next()) {
                MusicasDTO musica = new MusicasDTO();
                musica.setId(resultado.getInt("id"));
                musica.setGenero(resultado.getString("genero"));
                musica.setArtista(resultado.getString("artista"));
                musica.setNome(resultado.getString("nome"));
                musica.setAno(resultado.getInt("ano_lancamento"));
                lista.add(musica);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao listar músicas", e);
            DialogUtil.showError("Erro ao listar músicas no banco de dados.");
            throw new RuntimeException("Erro ao listar músicas.", e);
        }

        return lista;
    }

    @Override
    public void cadastrarMusicas(MusicasDTO musica) {
        String sql = "INSERT INTO musicas (genero, artista, nome, ano_lancamento) VALUES (?, ?, ?, ?)";

        try (Connection conexao = new Conexao().conectaBD();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, musica.getGenero());
            comando.setString(2, musica.getArtista());
            comando.setString(3, musica.getNome());
            comando.setInt(4, musica.getAno());

            comando.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao cadastrar música", e);
            DialogUtil.showError("Erro ao cadastrar música no banco de dados.");
            throw new RuntimeException("Erro ao cadastrar música.", e);
        }
    }

    @Override
    public void atualizarMusicas(MusicasDTO musica) {
        String sql = "UPDATE musicas SET genero=?, artista=?, nome=?, ano_lancamento=? WHERE id=?";

        try (Connection conexao = new Conexao().conectaBD();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, musica.getGenero());
            comando.setString(2, musica.getArtista());
            comando.setString(3, musica.getNome());
            comando.setInt(4, musica.getAno());
            comando.setInt(5, musica.getId());

            comando.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao atualizar música", e);
            DialogUtil.showError("Erro ao atualizar música no banco de dados.");
            throw new RuntimeException("Erro ao atualizar música.", e);
        }
    }

    @Override
    public void deletarMusicas(int id) {
        String sql = "DELETE FROM musicas WHERE id=?";

        try (Connection conexao = new Conexao().conectaBD();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, id);
            comando.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao excluir música", e);
            DialogUtil.showError("Erro ao excluir música no banco de dados.");
            throw new RuntimeException("Erro ao excluir música.", e);
        }
    }
}