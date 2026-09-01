package com.template.controller;

import com.template.model.MusicasDTO;
import com.template.service.MusicasService;
import com.template.validator.AnoValidador;
import com.template.validator.CampoObrigatorioValidador;

import com.template.validator.IUsuarioValidator;
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

import static com.template.util.DialogUtil.*;

public class MainController {

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnLimpar;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtArtista;

    @FXML
    private TextField txtGenero;

    @FXML
    private TextField txtAno;

    @FXML
    private TableView<MusicasDTO> tblMusicas;

    @FXML
    private TableColumn<MusicasDTO, Integer> colId;

    @FXML
    private TableColumn<MusicasDTO, String> colNome;

    @FXML
    private TableColumn<MusicasDTO, String> colArtista;

    @FXML
    private TableColumn<MusicasDTO, String> colGenero;

    @FXML
    private TableColumn<MusicasDTO, Integer> colAno;

    private final MusicasService service = new MusicasService();

    private final IUsuarioValidator uvalidador;
    public MainController(IUsuarioValidator uvalidador) {
        this.uvalidador = uvalidador;
    }

    @FXML
    private void initialize() {

        configurarTabela();
        configurarCampos();
        carregarMusicas();
    }

    private void configurarTabela() {

        colId.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        colNome.setCellValueFactory(
                new PropertyValueFactory<>("nome")
        );

        colArtista.setCellValueFactory(
                new PropertyValueFactory<>("artista")
        );

        colGenero.setCellValueFactory(
                new PropertyValueFactory<>("genero")
        );

        colAno.setCellValueFactory(
                new PropertyValueFactory<>("ano")
        );

        colId.setPrefWidth(60);
        colNome.setPrefWidth(180);
        colArtista.setPrefWidth(180);
        colGenero.setPrefWidth(140);
        colAno.setPrefWidth(100);
    }

    private void configurarCampos() {

        txtId.setEditable(false);

        txtNome.setPromptText("Digite o nome da música");
        txtArtista.setPromptText("Digite o artista");
        txtGenero.setPromptText("Digite o gênero");
        txtAno.setPromptText("Digite o ano");

        btnAtualizar.setDisable(true);
        btnDeletar.setDisable(true);

        txtAno.textProperty().addListener(
                (obs, oldValue, newValue) -> {

                    if (!newValue.matches("\\d*")) {
                        txtAno.setText(
                                newValue.replaceAll("[^\\d]", "")
                        );
                    }
                }
        );
    }

    private void carregarMusicas() {

        ObservableList<MusicasDTO> lista =
                FXCollections.observableArrayList(
                        service.listarMusicas()
                );

        tblMusicas.setItems(lista);
    }

    @FXML
    private void btnSalvarAction(ActionEvent event) {

        if (!validarCampos()) {
            return;
        }

        MusicasDTO musica = criarMusica();

        service.cadastrarMusica(musica);

        carregarMusicas();
        limparCampos();

        showInfo("Música cadastrada com sucesso!");
    }

    @FXML
    private void btnAtualizarAction(ActionEvent event) {

        if (!validarCampos()) {
            return;
        }

        MusicasDTO musica = criarMusica();

        musica.setId(
                Integer.parseInt(txtId.getText())
        );

        service.atualizarMusica(musica);

        carregarMusicas();
        limparCampos();

        showInfo("Música atualizada com sucesso!");
    }

    @FXML
    private void btnDeletarAction(ActionEvent event) {

        if (txtId.getText().isEmpty()) {
            return;
        }

        int id = Integer.parseInt(txtId.getText());

        service.deletarMusica(id);

        carregarMusicas();
        limparCampos();

        showInfo("Música excluída com sucesso!");
    }

    @FXML
    private void btnLimparAction(ActionEvent event) {

        limparCampos();
    }

    private MusicasDTO criarMusica() {

        MusicasDTO musica = new MusicasDTO();

        musica.setNome(txtNome.getText());
        musica.setArtista(txtArtista.getText());
        musica.setGenero(txtGenero.getText());

        musica.setAno(
                Integer.parseInt(txtAno.getText())
        );

        return musica;
    }

    private boolean validarCampos() {

        CampoObrigatorioValidador nome =
                new CampoObrigatorioValidador(
                        "Nome",
                        txtNome.getText()
                );

        CampoObrigatorioValidador artista =
                new CampoObrigatorioValidador(
                        "Artista",
                        txtArtista.getText()
                );

        CampoObrigatorioValidador genero =
                new CampoObrigatorioValidador(
                        "Gênero",
                        txtGenero.getText()
                );

        AnoValidador ano =
                new AnoValidador(
                        txtAno.getText()
                );

        if (!nome.validar(nome.getValor())) {
            showError(nome.getMensagemErro());
            return false;
        }

        if (!artista.validar(artista.getValor())) {
            showError(artista.getMensagemErro());
            return false;
        }

        if (!genero.validar(genero.getValor())) {
            showError(genero.getMensagemErro());
            return false;
        }

        if (!ano.validar(ano.getValor())) {
            showError(ano.getMensagemErro());
            return false;
        }

        return true;
    }

    private void limparCampos() {

        txtId.clear();
        txtNome.clear();
        txtArtista.clear();
        txtGenero.clear();
        txtAno.clear();

        btnAtualizar.setDisable(true);
        btnDeletar.setDisable(true);

        txtNome.requestFocus();
    }

    @FXML
    private void carregarCampos(MouseEvent event) {

        MusicasDTO musicaSelecionada =
                tblMusicas.getSelectionModel().getSelectedItem();

        if (musicaSelecionada != null) {

            txtId.setText(
                    String.valueOf(musicaSelecionada.getId())
            );

            txtNome.setText(
                    musicaSelecionada.getNome()
            );

            txtArtista.setText(
                    musicaSelecionada.getArtista()
            );

            txtGenero.setText(
                    musicaSelecionada.getGenero()
            );

            txtAno.setText(
                    String.valueOf(musicaSelecionada.getAno())
            );

            btnAtualizar.setDisable(false);
            btnDeletar.setDisable(false);
        }
    }
    public class UsuarioValidator implements IUsuarioValidator{

        @Override
        public boolean validarUsuario(String nome, String genero, String ano, String artista) {
            return false;
        }

        @Override
        public boolean validarNome(String nome) {
            return false;
        }

        @Override
        public boolean validarGenero(String genero) {
            return false;
        }

        @Override
        public boolean validarAno(String ano) {
            return false;
        }

        @Override
        public boolean validarArtista(String artista) {
            return false;
        }
    }
}