package com.template.controller;

<<<<<<< HEAD
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
=======
import com.template.model.dto.MusicasDTO;
import com.template.service.MusicasService;
import com.template.util.MusicasViewUtil;
import com.template.util.DialogUtil;
import com.template.validator.IMusicasValidator;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.logging.Level;
import java.util.logging.Logger;
>>>>>>> d5b9a2b666c04c80a3e4cd50746fcf3d36698097

public class MainController {

    private static final Logger LOGGER = Logger.getLogger(MainController.class.getName());

<<<<<<< HEAD
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
=======
    private final MusicasService service = new MusicasService();
    private final IMusicasValidator validador;

    public MainController(IMusicasValidator validador) {
        this.validador = validador;
    }

    // Botões com suporte a nomes alternativos do FXML
    @FXML private Button btnSalvar;
    @FXML private Button btnEditar;
    @FXML private Button btnAtualizar;
    @FXML private Button btnDeletar;
    @FXML private Button btnExcluir;
    @FXML private Button btnLimpar;

    @FXML private TextField txtNome;
    @FXML private TextField txtGenero;
    @FXML private TextField txtArtista;
    @FXML private TextField txtAno;

    @FXML private Label lblMensagem;

    // Tabelas com suporte a plural/singular
    @FXML private TableView<MusicasDTO> tblMusica;
    @FXML private TableView<MusicasDTO> tblMusicas;

    @FXML private TableColumn<MusicasDTO, Integer> colId;
    @FXML private TableColumn<MusicasDTO, String> colNome;
    @FXML private TableColumn<MusicasDTO, String> colGenero;
    @FXML private TableColumn<MusicasDTO, String> colArtista;
    @FXML private TableColumn<MusicasDTO, Integer> colAno;

    private Button getBtnEditarEfetivo() {
        return btnEditar != null ? btnEditar : btnAtualizar;
    }

    private Button getBtnDeletarEfetivo() {
        return btnDeletar != null ? btnDeletar : btnExcluir;
    }

    private TableView<MusicasDTO> getTabelaEfetiva() {
        return tblMusica != null ? tblMusica : tblMusicas;
    }

    @FXML
    private void carregarCampos() {
        MusicasDTO selecionado = MusicasViewUtil.obterSelecionado(getTabelaEfetiva());
        if (selecionado != null) {
            MusicasViewUtil.preencherCampos(selecionado, txtNome, txtGenero, txtArtista, txtAno, lblMensagem);
        }
    }

    @FXML
    private void btnSalvarAction() {
        salvarMusica();
    }

    @FXML
    private void btnAtualizarAction() {
        editarMusica();
    }

    @FXML
    private void btnEditarAction() {
        editarMusica();
    }

    @FXML
    private void btnExcluirAction() {
        deletarMusica();
    }

    @FXML
    private void btnDeletarAction() {
        deletarMusica();
    }

    @FXML
    private void btnLimparAction() {
        MusicasViewUtil.limparCampos(txtNome, txtGenero, txtArtista, txtAno, lblMensagem, getTabelaEfetiva());
    }

    private void salvarMusica() {
        try {
            String erro = validador.validarMusica(
                    txtNome != null ? txtNome.getText() : "",
                    txtArtista != null ? txtArtista.getText() : "",
                    txtGenero != null ? txtGenero.getText() : "",
                    txtAno != null ? txtAno.getText() : ""
            );

            if (erro != null) {
                throw new IllegalArgumentException(erro);
            }

            service.salvar(
                    txtNome.getText(),
                    txtGenero.getText(),
                    txtArtista.getText(),
                    txtAno.getText()
            );

            carregarMusicas();
            MusicasViewUtil.limparCampos(txtNome, txtGenero, txtArtista, txtAno, lblMensagem, getTabelaEfetiva());
            MusicasViewUtil.mostrarMensagem(lblMensagem, "Música salva com sucesso!", "green");

        } catch (IllegalArgumentException e) {
            MusicasViewUtil.mostrarMensagem(lblMensagem, e.getMessage(), "red");
            MusicasViewUtil.focarCampoComErro(e.getMessage(), txtNome, txtGenero, txtArtista, txtAno);
        } catch (Exception e) {
            MusicasViewUtil.mostrarMensagem(lblMensagem, "Erro ao salvar a música.", "red");
            LOGGER.log(Level.SEVERE, "Erro ao salvar música.", e);
        }
    }

    private void editarMusica() {
        MusicasDTO selecionado = MusicasViewUtil.obterSelecionado(getTabelaEfetiva());
        if (selecionado == null) return;

        try {
            String erro = validador.validarMusica(
                    txtNome != null ? txtNome.getText() : "",
                    txtArtista != null ? txtArtista.getText() : "",
                    txtGenero != null ? txtGenero.getText() : "",
                    txtAno != null ? txtAno.getText() : ""
            );

            if (erro != null) {
                throw new IllegalArgumentException(erro);
            }

            service.editar(
                    selecionado.getId(),
                    txtNome.getText(),
                    txtGenero.getText(),
                    txtArtista.getText(),
                    txtAno.getText()
            );

            carregarMusicas();
            MusicasViewUtil.limparCampos(txtNome, txtGenero, txtArtista, txtAno, lblMensagem, getTabelaEfetiva());
            MusicasViewUtil.mostrarMensagem(lblMensagem, "Música editada com sucesso!", "green");

        } catch (IllegalArgumentException e) {
            MusicasViewUtil.mostrarMensagem(lblMensagem, e.getMessage(), "red");
            MusicasViewUtil.focarCampoComErro(e.getMessage(), txtNome, txtGenero, txtArtista, txtAno);
        } catch (Exception e) {
            MusicasViewUtil.mostrarMensagem(lblMensagem, "Erro ao editar a música.", "red");
            LOGGER.log(Level.SEVERE, "Erro ao editar música.", e);
        }
    }

    private void deletarMusica() {
        MusicasDTO selecionado = MusicasViewUtil.obterSelecionado(getTabelaEfetiva());
        if (selecionado == null) return;

        boolean confirmou = DialogUtil.confirmar(
                "Confirmar Exclusão",
                "Deseja realmente excluir a música \"" + selecionado.getNome() + "\"?"
        );

        if (!confirmou) return;

        try {
            service.deletar(selecionado.getId());
            carregarMusicas();
            MusicasViewUtil.limparCampos(txtNome, txtGenero, txtArtista, txtAno, lblMensagem, getTabelaEfetiva());
            MusicasViewUtil.mostrarMensagem(lblMensagem, "Música deletada com sucesso.", "blue");
        } catch (Exception e) {
            MusicasViewUtil.mostrarMensagem(lblMensagem, "Erro ao deletar a música.", "red");
            LOGGER.log(Level.SEVERE, "Erro ao deletar música.", e);
        }
    }

    private void carregarMusicas() {
        MusicasViewUtil.carregarTabela(getTabelaEfetiva(), service.listar());
    }

    @FXML
    private void initialize() {
        TableView<MusicasDTO> tabela = getTabelaEfetiva();

        MusicasViewUtil.configurarTabela(colId, colNome, colGenero, colArtista, colAno);
        MusicasViewUtil.configurarBotoes(btnSalvar, getBtnEditarEfetivo(), getBtnDeletarEfetivo(), tabela);

        if (tabela != null) {
            tabela.getSelectionModel().selectedItemProperty().addListener(
                    (obs, antigo, novo) -> MusicasViewUtil.preencherCampos(novo, txtNome, txtGenero, txtArtista, txtAno, lblMensagem)
            );
        }

        carregarMusicas();
        LOGGER.info("FXML carregado com sucesso!");
>>>>>>> d5b9a2b666c04c80a3e4cd50746fcf3d36698097
    }
}