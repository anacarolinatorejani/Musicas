package com.template.factory;

import com.template.controller.MainController;
import com.template.validator.IMusicasValidator;
import com.template.validator.MusicasValidator;
import javafx.util.Callback;

    public class ControllerFactory implements Callback<Class<?>, Object> {

        private final IMusicasValidator musicasValidator;

        public ControllerFactory() {
            this.musicasValidator = new MusicasValidator();
        }

        @Override
        public Object call(Class<?> clazz) {
            if (clazz == MainController.class) {
                return new MainController(musicasValidator);
            }
            try {
                return clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Erro ao instanciar o controller: " + clazz.getName(), e);
            }
        }
    }