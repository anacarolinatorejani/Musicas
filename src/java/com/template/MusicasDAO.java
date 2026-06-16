package com.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MusicasDAO {

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
            throw new RuntimeException("Erro ao listar músicas.", e);
        }

        return lista;
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

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar música.", e);
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

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar música.", e);
        }
    }

    public void deletarMusicas(int id) {

        String sql = "DELETE FROM musicas WHERE id=?";

        try (Connection conexao = new Conexao().conectaBD();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, id);

            comando.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir música.", e);
        }
    }
}