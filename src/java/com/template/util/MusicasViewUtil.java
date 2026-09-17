package com.template.util;

import com.template.model.dto.MusicasDTO;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class MusicasViewUtil {

    private MusicasViewUtil() {
    }

    public static void configurarTabela(
            TableColumn<MusicasDTO, Integer> colId,
            TableColumn<MusicasDTO, String> colNome,
            TableColumn<MusicasDTO, String> colGenero,
            TableColumn<MusicasDTO, String> colArtista,
            TableColumn<MusicasDTO, Integer> colAno
    ) {
        if (colId != null) colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        if (colNome != null) colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        if (colGenero != null) colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        if (colArtista != null) colArtista.setCellValueFactory(new PropertyValueFactory<>("artista"));
        if (colAno != null) colAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
    }

    public static void configurarBotoes(
            Button btnSalvar,
            Button btnEditar,
            Button btnDeletar,
            TableView<MusicasDTO> tabela
    ) {
        if (tabela == null) return;

        if (btnEditar != null) {
            btnEditar.disableProperty().bind(
                    tabela.getSelectionModel().selectedItemProperty().isNull()
            );
        }

        if (btnDeletar != null) {
            btnDeletar.disableProperty().bind(
                    tabela.getSelectionModel().selectedItemProperty().isNull()
            );
        }

        if (btnSalvar != null) {
            btnSalvar.disableProperty().bind(
                    tabela.getSelectionModel().selectedItemProperty().isNotNull()
            );
        }
    }

    public static void carregarTabela(
            TableView<MusicasDTO> tabela,
            List<MusicasDTO> musicas
    ) {
        if (tabela != null && musicas != null) {
            tabela.setItems(FXCollections.observableArrayList(musicas));
        }
    }

    public static MusicasDTO obterSelecionado(TableView<MusicasDTO> tabela) {
        return tabela != null ? tabela.getSelectionModel().getSelectedItem() : null;
    }

    public static void preencherCampos(
            MusicasDTO musica,
            TextField txtNome,
            TextField txtGenero,
            TextField txtArtista,
            TextField txtAno,
            Label lblMensagem
    ) {
        if (musica == null) return;

        if (txtNome != null) txtNome.setText(musica.getNome());
        if (txtGenero != null) txtGenero.setText(musica.getGenero());
        if (txtArtista != null) txtArtista.setText(musica.getArtista());
        if (txtAno != null) txtAno.setText(String.valueOf(musica.getAno()));

        limparMensagem(lblMensagem);
    }

    public static void limparCampos(
            TextField txtNome,
            TextField txtGenero,
            TextField txtArtista,
            TextField txtAno,
            Label lblMensagem,
            TableView<MusicasDTO> tabela
    ) {
        if (txtNome != null) txtNome.clear();
        if (txtGenero != null) txtGenero.clear();
        if (txtArtista != null) txtArtista.clear();
        if (txtAno != null) txtAno.clear();

        limparMensagem(lblMensagem);
        if (tabela != null) tabela.getSelectionModel().clearSelection();
    }

    public static void mostrarMensagem(Label label, String mensagem, String cor) {
        if (label != null) {
            label.setStyle("-fx-text-fill: " + cor + ";");
            label.setText(mensagem);
        }
    }

    public static void limparMensagem(Label label) {
        if (label != null) {
            label.setText("");
        }
    }

    public static void focarCampoComErro(
            String erro,
            TextField txtNome,
            TextField txtGenero,
            TextField txtArtista,
            TextField txtAno
    ) {
        if (erro == null) return;

        if (erro.contains("Nome") && txtNome != null) {
            txtNome.requestFocus();
        } else if (erro.contains("Gênero") && txtGenero != null) {
            txtGenero.requestFocus();
        } else if (erro.contains("Artista") && txtArtista != null) {
            txtArtista.requestFocus();
        } else if (erro.contains("Ano") && txtAno != null) {
            txtAno.requestFocus();
        }
    }
}