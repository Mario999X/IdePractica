package logica;

import javax.swing.*;
import java.awt.print.PrinterException;

public class Terminal extends JScrollPane {
    private JTextArea espacioTerminal;

    public Terminal (){

        espacioTerminal = new JTextArea();
        this.setViewportView(espacioTerminal);
    }

    public void setText(String texto){
        espacioTerminal.setText(texto);
    }

    public String getText(){
        return espacioTerminal.getText();
    }

    public void print(){
        try {
            espacioTerminal.print();
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }
}