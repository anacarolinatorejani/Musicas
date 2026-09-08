package com.template.main;

import com.template.controller.MainController;
import com.template.validator.IMusicasValidator;
import com.template.validator.MusicasValidator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        IMusicasValidator validador = new MusicasValidator();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/template/main.fxml"));
        loader.setControllerFactory(clazz -> {
            if (clazz == MainController.class) {
                return new MainController(validador);
            }
            try {
                return clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("Cadastro de Músicas");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}