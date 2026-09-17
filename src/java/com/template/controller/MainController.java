package com.template.controller;

import com.template.model.MusicasDTO;
import com.template.service.IMusicasService;
import com.template.service.MusicasService;
import com.template.util.DialogUtil;
import com.template.util.MainViewHelper;
import com.template.validator.IMusicaValidador;
import com.template.validator.MusicaValidador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class MainController {

    private static final Logger LOGGER = Logger.getLogger(MainController.class.getName());

    private final IMusicasService service;
    private final IMusicaValidador musicaValidador;

    public MainController() {
        this(new MusicasService(), new MusicaValidador());
    }

    public MainController(IMusicasService service, IMusicaValidador musicaValidador) {
        this.service = service;
        this.musicaValidador = musicaValidador;
    }

    @FXML
    private void initialize() {
        MainViewHelper.configurarTabela(colId, colNome, colArtista, colGenero, colAno);
        MainViewHelper.configurarCampos(txtId, txtNome, txtArtista, txtGenero, txtAno, btnAtualizar, btnDeletar);
        MainViewHelper.atualizarTabela(tblMusicas, service.listarMusicas());
    }

    @FXML
    private void btnSalvarAction(ActionEvent event) {
        if (!musicaValidador.validarMusica(
                txtNome.getText(),
                txtArtista.getText(),
                txtGenero.getText(),
                txtAno.getText())) {
            return;
        }

        MusicasDTO musica = service.criarMusica(
                txtNome.getText(),
                txtArtista.getText(),
                txtGenero.getText(),
                txtAno.getText()
        );

        service.cadastrarMusica(musica);
        MainViewHelper.atualizarTabela(tblMusicas, service.listarMusicas());
        MainViewHelper.limparCampos(txtId, txtNome, txtArtista, txtGenero, txtAno, btnAtualizar, btnDeletar);

        DialogUtil.showInfo("Música cadastrada com sucesso!");
    }

    @FXML
    private void btnAtualizarAction(ActionEvent event) {
        if (!musicaValidador.validarMusica(
                txtNome.getText(),
                txtArtista.getText(),
                txtGenero.getText(),
                txtAno.getText())) {
            return;
        }

        if (txtId.getText().isEmpty()) {
            DialogUtil.showWarning("Selecione uma música na tabela para atualizar.");
            return;
        }

        MusicasDTO musica = service.criarMusica(
                txtNome.getText(),
                txtArtista.getText(),
                txtGenero.getText(),
                txtAno.getText()
        );
        musica.setId(Integer.parseInt(txtId.getText()));

        service.atualizarMusica(musica);
        MainViewHelper.atualizarTabela(tblMusicas, service.listarMusicas());
        MainViewHelper.limparCampos(txtId, txtNome, txtArtista, txtGenero, txtAno, btnAtualizar, btnDeletar);

        DialogUtil.showInfo("Música atualizada com sucesso!");
    }

    @FXML
    private void btnDeletarAction(ActionEvent event) {
        if (txtId.getText().isEmpty()) {
            DialogUtil.showWarning("Selecione uma música na tabela para excluir.");
            return;
        }

        if (!DialogUtil.showConfirmation("Deseja realmente excluir esta música?")) {
            return;
        }

        int id = Integer.parseInt(txtId.getText());
        service.deletarMusica(id);

        MainViewHelper.atualizarTabela(tblMusicas, service.listarMusicas());
        MainViewHelper.limparCampos(txtId, txtNome, txtArtista, txtGenero, txtAno, btnAtualizar, btnDeletar);

        DialogUtil.showInfo("Música excluída com sucesso!");
    }

    @FXML
    private void btnLimparAction(ActionEvent event) {
        MainViewHelper.limparCampos(txtId, txtNome, txtArtista, txtGenero, txtAno, btnAtualizar, btnDeletar);
    }

    @FXML
    private void carregarCampos(MouseEvent event) {
        MusicasDTO musicaSelecionada = tblMusicas.getSelectionModel().getSelectedItem();
        MainViewHelper.preencherCampos(musicaSelecionada, txtId, txtNome, txtArtista, txtGenero, txtAno, btnAtualizar, btnDeletar);
    }
    
}