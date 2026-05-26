package com.template;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MusicasDAO {

    private static final Logger logger = Logger.getLogger(MusicasDAO.class.getName());

    public void selecionarMusicas() {
        String sql = "SELECT * FROM musicas";

        try (Connection conexao = new Conexao().conectaBD();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {

            while (resultado.next()) {
                logger.log(Level.INFO, "{0} - {1} - {2} - {3} - {4}", new Object[]{
                        resultado.getInt("id"),
                        resultado.getString("genero"),
                        resultado.getString("artista"),
                        resultado.getString("nome"),
                        resultado.getInt("ano_lancamento")
                });
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao listar músicas", e);
        }
    }

    public void cadastrarMusicas(MusicasDTO musica) {
        String sql = "INSERT INTO musicas (genero, artista, nome, ano_lancamento) VALUES (?, ?, ?, ?)";

        try (Connection conexao = new Conexao().conectaBD();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, musica.getGenero());
            comando.setString(2, musica.getArtista());
            comando.setString(3, musica.getNome());
            comando.setInt(4, musica.getAno());

            comando.executeUpdate();
            logger.info("Música cadastrada com sucesso!");

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao cadastrar música", e);
        }
    }

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
            logger.info("Música atualizada com sucesso!");

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao atualizar música", e);
        }
    }

    public void deletarMusicas(int id) {
        String sql = "DELETE FROM musicas WHERE id=?";

        try (Connection conexao = new Conexao().conectaBD();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, id);
            comando.executeUpdate();
            logger.log(Level.INFO, "Música deletada com sucesso!", id);

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao deletar música", e);
        }
    }
}