package logica;

import javax.swing.*;

public class ExploradorArchivos extends JScrollPane {
    private JTree espacioArchivos;

    public ExploradorArchivos (){

        espacioArchivos = new JTree();
        this.setViewportView(espacioArchivos);

    }

}