package logica;

import javax.swing.*;
import java.awt.print.PrinterException;

public class AreaTexto extends JScrollPane {
    private JTextArea espacioEscribir;

    public AreaTexto (){

        espacioEscribir = new JTextArea();
        this.setViewportView(espacioEscribir);
    }

    public JTextArea getEspacioEscribir() {
        return espacioEscribir;
    }

    public void setText(String texto){
        espacioEscribir.setText(texto);
    }

    public String getText(){
        return espacioEscribir.getText();
    }

    public void print(){
        try {
            espacioEscribir.print();
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }
}
