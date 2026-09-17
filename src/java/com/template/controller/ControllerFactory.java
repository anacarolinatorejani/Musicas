package com.template.controller;

import com.template.service.IMusicasService;
import com.template.validator.IMusicaValidador;
import javafx.util.Callback;

public class ControllerFactory implements Callback<Class<?>, Object> {

    private final IMusicasService musicasService;
    private final IMusicaValidador musicaValidador;

    public ControllerFactory(IMusicasService musicasService, IMusicaValidador musicaValidador) {
        this.musicasService = musicasService;
        this.musicaValidador = musicaValidador;
    }

    @Override
    public Object call(Class<?> controllerClass) {
        if (controllerClass == MainController.class) {
            return new MainController(musicasService, musicaValidador);
        }
        try {
            return controllerClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao instanciar controller pela fábrica: " + controllerClass.getName(), e);
        }
    }
}
