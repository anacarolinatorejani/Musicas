package com.template.main;

<<<<<<< HEAD
import com.template.controller.ControllerFactory;
import com.template.model.IMusicasDAO;
import com.template.model.MusicasDAO;
import com.template.service.IMusicasService;
import com.template.service.MusicasService;
import com.template.validator.IMusicaValidador;
import com.template.validator.MusicaValidador;
=======
import com.template.controller.MainController;
import com.template.validator.IMusicasValidator;
import com.template.validator.MusicasValidator;
>>>>>>> d5b9a2b666c04c80a3e4cd50746fcf3d36698097
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

<<<<<<< HEAD
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

=======
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
>>>>>>> d5b9a2b666c04c80a3e4cd50746fcf3d36698097
        primaryStage.setTitle("Cadastro de Músicas");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}