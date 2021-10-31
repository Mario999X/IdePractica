package logica;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.print.PrinterException;

//CLASE ENCARGADA DE GENERAR LAS CARACTERISTICAS DE UN TERMINAL O CONSOLA PERSONALIZADA
public class Terminal extends JScrollPane {
    private JTextArea terminal;

    public Terminal(){

        terminal = new JTextArea();
        terminal.setEditable(false);
        terminal.setBackground(Color.DARK_GRAY);
        terminal.setForeground(Color.WHITE);
        terminal.setBorder(new EmptyBorder(10,20,10,10));
        this.setViewportView(terminal);
    }

    public JTextArea getTerminal() {
        return terminal;
    }

    public void setText(String text){
        terminal.setText(text);
    }

    public String getText(){
        return terminal.getText();
    }

    public void print(){
        try {
            terminal.print();
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }

}