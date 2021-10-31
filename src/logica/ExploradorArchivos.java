package logica;

import javax.swing.*;

//CLASE ENCARGADA DE CREAR LAS CARACTERISTICAS UN EXPLORADOR DE ARCHIVOS PERSONALIZADO (NO FUNCION)
public class ExploradorArchivos extends JScrollPane {
    private JTree espacioArchivos;

    public ExploradorArchivos (){

        espacioArchivos = new JTree();
        this.setViewportView(espacioArchivos);

    }

}