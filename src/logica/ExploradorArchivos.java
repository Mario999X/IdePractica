/**package logica;

import javax.swing.*;
import java.awt.*;

public class ExploradorArchivos {
    private JPanel panelIzquierda;
    private JTextArea prueba;

    public ExploradorArchivos (){
        //initExploradorArchivos ();
    }

    private void initExploradorArchivos(){
        panelIzquierda = new JPanel();
        panelIzquierda.setLayout(new BorderLayout());
        panelIzquierda.add(prueba, BorderLayout.CENTER);
    }

    public JPanel getPanelIzquierda() {
        return panelIzquierda;
    }

    public void setPanelIzquierda(JPanel panelIzquierda) {
        this.panelIzquierda = panelIzquierda;
    }
}
*/
package logica;

import javax.swing.*;
import java.awt.print.PrinterException;

public class ExploradorArchivos extends JScrollPane {
    private JTextArea espacioArchivos;

    public ExploradorArchivos (){

        espacioArchivos = new JTextArea();
        this.setViewportView(espacioArchivos);
    }

    public void setText(String texto){
        espacioArchivos.setText(texto);
    }

    public String getText(){
        return espacioArchivos.getText();
    }

    public void print(){
        try {
            espacioArchivos.print();
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }
}