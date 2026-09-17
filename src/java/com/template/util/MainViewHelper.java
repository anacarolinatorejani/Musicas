package com.template.util;

import com.template.model.MusicasDTO;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class MainViewHelper {

    public static void configurarTabela(
            TableColumn<MusicasDTO, Integer> colId,
            TableColumn<MusicasDTO, String> colNome,
            TableColumn<MusicasDTO, String> colArtista,
            TableColumn<MusicasDTO, String> colGenero,
            TableColumn<MusicasDTO, Integer> colAno) {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colArtista.setCellValueFactory(new PropertyValueFactory<>("artista"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        colAno.setCellValueFactory(new PropertyValueFactory<>("ano"));

        colId.setPrefWidth(60);
        colNome.setPrefWidth(180);
        colArtista.setPrefWidth(180);
        colGenero.setPrefWidth(140);
        colAno.setPrefWidth(100);
    }

    public static void configurarCampos(
            TextField txtId,
            TextField txtNome,
            TextField txtArtista,
            TextField txtGenero,
            TextField txtAno,
            Button btnAtualizar,
            Button btnDeletar) {

        txtId.setEditable(false);

        txtNome.setPromptText("Digite o nome da música");
        txtArtista.setPromptText("Digite o artista");
        txtGenero.setPromptText("Digite o gênero");
        txtAno.setPromptText("Digite o ano");

        btnAtualizar.setDisable(true);
        btnDeletar.setDisable(true);

        txtAno.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtAno.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    public static void atualizarTabela(TableView<MusicasDTO> tblMusicas, List<MusicasDTO> musicas) {
        tblMusicas.setItems(FXCollections.observableArrayList(musicas));
    }

    public static void limparCampos(
            TextField txtId,
            TextField txtNome,
            TextField txtArtista,
            TextField txtGenero,
            TextField txtAno,
            Button btnAtualizar,
            Button btnDeletar) {

        txtId.clear();
        txtNome.clear();
        txtArtista.clear();
        txtGenero.clear();
        txtAno.clear();

        btnAtualizar.setDisable(true);
        btnDeletar.setDisable(true);

        txtNome.requestFocus();
    }

    public static void preencherCampos(
            MusicasDTO musicaSelecionada,
            TextField txtId,
            TextField txtNome,
            TextField txtArtista,
            TextField txtGenero,
            TextField txtAno,
            Button btnAtualizar,
            Button btnDeletar) {

        if (musicaSelecionada != null) {
            txtId.setText(String.valueOf(musicaSelecionada.getId()));
            txtNome.setText(musicaSelecionada.getNome());
            txtArtista.setText(musicaSelecionada.getArtista());
            txtGenero.setText(musicaSelecionada.getGenero());
            txtAno.setText(String.valueOf(musicaSelecionada.getAno()));

            btnAtualizar.setDisable(false);
            btnDeletar.setDisable(false);
        }
    }
}
