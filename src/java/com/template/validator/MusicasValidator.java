package com.template.validator;

import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class MusicasValidator {

    public static boolean validarUsuario(String nome, String email, String senha, String login) {

        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || login.isEmpty()) {
            JOptionPane.showMessageDialog(
                    null,
                    "Preencha todos os campos antes de prosseguir.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        }

        if (!validarEmail(email)) {
            JOptionPane.showMessageDialog(
                    null,
                    "Digite um e-mail válido (exemplo@dominio.com)!",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        }

        return true;
    }

    public static boolean validarEmail(String email) {
        return Pattern.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
                email
        );
    }
}
