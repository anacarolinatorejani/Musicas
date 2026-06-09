package com.template;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;

public class MainController {

    @FXML private Button btnSalvar;
    @FXML private Button btnAtualizar;
    @FXML private Button btnDeletar;
    @FXML private Button btnLimpar;
    @FXML private TextField txtId;
    @FXML private TextField txtNome;
    @FXML private TextField txtArtista;
    @FXML private TextField txtGenero;
    @FXML private TextField txtAno;
    @FXML private TableView<MusicasDTO> tblMusicas;
    @FXML private TableColumn<MusicasDTO, Integer> colId;
    @FXML private TableColumn<MusicasDTO, String> colNome;
    @FXML private TableColumn<MusicasDTO, String> colArtista;
    @FXML private TableColumn<MusicasDTO, String> colGenero;
    @FXML private TableColumn<MusicasDTO, Integer> colAno;

    @FXML
    private void initialize() {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colArtista.setCellValueFactory(new PropertyValueFactory<>("artista"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        colAno.setCellValueFactory(new PropertyValueFactory<>("ano"));

        carregarMusicas();
    }

    private void carregarMusicas() {
        try {
            ObservableList<MusicasDTO> lista =
                    FXCollections.observableArrayList(
                            new MusicasDAO().listarMusicas()
                    );

            tblMusicas.setItems(lista);

        } catch (Exception e) {
            System.out.println("Erro ao carregar músicas: " + e.getMessage());
        }
    }

    @FXML
    private void btnSalvarAction(ActionEvent event) {
        if (txtNome.getText().isEmpty() || txtArtista.getText().isEmpty() || txtAno.getText().isEmpty()) {
            return;
        }

        try {
            MusicasDTO novaMusica = new MusicasDTO();
            novaMusica.setNome(txtNome.getText());
            novaMusica.setArtista(txtArtista.getText());
            novaMusica.setGenero(txtGenero.getText());
            novaMusica.setAno(Integer.parseInt(txtAno.getText()));

            new MusicasDAO().cadastrarMusicas(novaMusica);

            carregarMusicas();
            limparCampos();

        } catch (NumberFormatException e) {
            System.out.println("Ano inválido");
        } catch (Exception e) {
            System.out.println("Erro ao salvar música: " + e.getMessage());
        }
    }

    @FXML
    private void btnAtualizarAction(ActionEvent event) {
        if (txtId.getText().isEmpty() || txtNome.getText().isEmpty() || txtArtista.getText().isEmpty() || txtAno.getText().isEmpty()) {
            return;
        }

        try {
            MusicasDTO musicaEditada = new MusicasDTO();
            musicaEditada.setId(Integer.parseInt(txtId.getText()));
            musicaEditada.setNome(txtNome.getText());
            musicaEditada.setArtista(txtArtista.getText());
            musicaEditada.setGenero(txtGenero.getText());
            musicaEditada.setAno(Integer.parseInt(txtAno.getText()));

            new MusicasDAO().atualizarMusicas(musicaEditada);

            carregarMusicas();
            limparCampos();

        } catch (NumberFormatException e) {
            System.out.println("Dados inválidos");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar música: " + e.getMessage());
        }
    }

    @FXML
    private void btnDeletarAction(ActionEvent event) {
        if (txtId.getText().isEmpty()) {
            return;
        }

        try {
            int id = Integer.parseInt(txtId.getText());

            new MusicasDAO().deletarMusicas(id);

            carregarMusicas();
            limparCampos();

        } catch (NumberFormatException e) {
            System.out.println("ID inválido");
        } catch (Exception e) {
            System.out.println("Erro ao deletar música: " + e.getMessage());
        }
    }

    @FXML
    private void btnLimparAction(ActionEvent event) {
        try {
            limparCampos();
        } catch (Exception e) {
            System.out.println("Erro ao limpar campos: " + e.getMessage());
        }
    }

    private void limparCampos() {
        txtId.clear();
        txtNome.clear();
        txtArtista.clear();
        txtGenero.clear();
        txtAno.clear();
        txtNome.requestFocus();
    }

    @FXML
    private void carregarCampos(MouseEvent event) {
        try {
            MusicasDTO musicaSelecionada = tblMusicas.getSelectionModel().getSelectedItem();

            if (musicaSelecionada != null) {
                txtId.setText(String.valueOf(musicaSelecionada.getId()));
                txtNome.setText(musicaSelecionada.getNome());
                txtArtista.setText(musicaSelecionada.getArtista());
                txtGenero.setText(musicaSelecionada.getGenero());
                txtAno.setText(String.valueOf(musicaSelecionada.getAno()));
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar campos da tabela: " + e.getMessage());
        }
    }
}