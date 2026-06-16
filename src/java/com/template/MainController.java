package com.template;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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

        colId.setPrefWidth(60);
        colNome.setPrefWidth(180);
        colArtista.setPrefWidth(180);
        colGenero.setPrefWidth(140);
        colAno.setPrefWidth(100);

        txtId.setEditable(false);

        txtNome.setPromptText("Digite o nome da música");
        txtArtista.setPromptText("Digite o artista");
        txtGenero.setPromptText("Digite o gênero");
        txtAno.setPromptText("Digite o ano");

        // Permite somente números no campo Ano
        txtAno.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtAno.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        carregarMusicas();
    }

    private void carregarMusicas() {

        ObservableList<MusicasDTO> lista =
                FXCollections.observableArrayList(
                        new MusicasDAO().listarMusicas()
                );

        tblMusicas.setItems(lista);
    }

    @FXML
    private void btnSalvarAction(ActionEvent event) {

        if (txtNome.getText().isEmpty()
                || txtArtista.getText().isEmpty()
                || txtAno.getText().isEmpty()) {

            return;
        }

        MusicasDTO novaMusica = new MusicasDTO();

        novaMusica.setNome(txtNome.getText());
        novaMusica.setArtista(txtArtista.getText());
        novaMusica.setGenero(txtGenero.getText());
        novaMusica.setAno(Integer.parseInt(txtAno.getText()));

        new MusicasDAO().cadastrarMusicas(novaMusica);

        carregarMusicas();
        limparCampos();
    }

    @FXML
    private void btnAtualizarAction(ActionEvent event) {

        if (txtId.getText().isEmpty()
                || txtNome.getText().isEmpty()
                || txtArtista.getText().isEmpty()
                || txtAno.getText().isEmpty()) {

            return;
        }

        MusicasDTO musicaEditada = new MusicasDTO();

        musicaEditada.setId(Integer.parseInt(txtId.getText()));
        musicaEditada.setNome(txtNome.getText());
        musicaEditada.setArtista(txtArtista.getText());
        musicaEditada.setGenero(txtGenero.getText());
        musicaEditada.setAno(Integer.parseInt(txtAno.getText()));

        new MusicasDAO().atualizarMusicas(musicaEditada);

        carregarMusicas();
        limparCampos();
    }

    @FXML
    private void btnDeletarAction(ActionEvent event) {

        if (txtId.getText().isEmpty()) {
            return;
        }

        int id = Integer.parseInt(txtId.getText());

        new MusicasDAO().deletarMusicas(id);

        carregarMusicas();
        limparCampos();
    }

    @FXML
    private void btnLimparAction(ActionEvent event) {
        limparCampos();
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

        MusicasDTO musicaSelecionada =
                tblMusicas.getSelectionModel().getSelectedItem();

        if (musicaSelecionada != null) {

            txtId.setText(String.valueOf(musicaSelecionada.getId()));
            txtNome.setText(musicaSelecionada.getNome());
            txtArtista.setText(musicaSelecionada.getArtista());
            txtGenero.setText(musicaSelecionada.getGenero());
            txtAno.setText(String.valueOf(musicaSelecionada.getAno()));
        }
    }
}