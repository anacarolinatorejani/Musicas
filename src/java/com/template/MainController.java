package com.template;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class MainController
{
    @FXML private Button btnSalvar;
    @FXML private Button btnAlterar;
    @FXML private Button btnExcluir;
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
    private void initialize()
    {
        System.out.println("FXML loaded successfully!");
    }
    @FXML
    private void btnCadastrarAction(ActionEvent event) {
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

            txtNome.clear();
            txtArtista.clear();
            txtGenero.clear();
            txtAno.clear();

        } catch (NumberFormatException e) {
            System.out.println("Ano inválido");
        }
    }
}