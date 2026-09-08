package com.template.main;

import com.template.factory.ControllerFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/template/main.fxml"));

        // Configura a fábrica para instanciar controllers com injeção de dependências
        loader.setControllerFactory(new ControllerFactory());

        Scene scene = new Scene(loader.load(), 700, 500);

        stage.setTitle("Cadastro de Músicas");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}