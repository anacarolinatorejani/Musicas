package com.template.main;

import com.template.controller.ControllerFactory;
import com.template.model.dao.IMusicasDAO;
import com.template.model.dao.MusicasDAO;
import com.template.service.IMusicasService;
import com.template.service.MusicasService;
import com.template.validator.IMusicaValidador;
import com.template.validator.MusicaValidador;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 1. Instancia as dependências concretas fora do Controller (Inversão de Dependência)
        IMusicasDAO musicasDAO = new MusicasDAO();
        IMusicasService musicasService = new MusicasService(musicasDAO);
        IMusicaValidador musicaValidador = new MusicaValidador();

        // 2. Cria a Fábrica de Controladores com as dependências
        ControllerFactory controllerFactory = new ControllerFactory(musicasService, musicaValidador);

        // 3. Configura o FXMLLoader com a fábrica
        URL fxmlLocation = getClass().getResource("/com/template/main.fxml");
        if (fxmlLocation == null) {
            System.err.println("Erro: main.fxml não encontrado. Verifique o caminho.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        loader.setControllerFactory(controllerFactory);

        Parent root = loader.load();
        Scene scene = new Scene(root, 700, 500);

        primaryStage.setTitle("Cadastro de Músicas");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}