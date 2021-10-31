package main;

import logica.Ventana;

import javax.swing.*;

//CLASE ENCARGADA DE INICIAR EL PROYECTO
public class Principal {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }
}
