package logica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana extends JFrame {

    private AreaTexto areaTexto;
    private Terminal areaTerminal;
    private ExploradorArchivos exploradorArchivos;
    private JPanel panelPrincipal;

    //Menu
    private JMenuBar menu;
    private JMenu menuArchivo;
    private JMenuItem menuArchivoOpcionNuevo;
    private JMenuItem menuArchivoOpcionAbrir;
    private JFileChooser menuArchivoOpcionAbrirAccion;
    private JMenuItem menuArchivoOpcionGuardar;
    private JMenuItem menuArchivoOpcionGuardarComo;
    private JMenuItem menuArchivoOpcionImprimir;

    private JMenu menuEdicion;
    private JMenuItem menuEdicionOpcionDeshacer;
    private JMenuItem menuEdicionOpcionCopiar;
    private JMenuItem menuEdicionOpcionCortar;
    private JMenuItem menuEdicionOpcionPegar;
    private JMenuItem menuEdicionOpcionEliminar;

    private JMenu menuAyuda;
    private JMenuItem menuAyudaOpcionAcercaDe;
    private JMenuItem menuAyudaOpcionVerAyuda;
    

    public Ventana() {
        areaTerminal = new Terminal();
        areaTexto = new AreaTexto();
        exploradorArchivos = new ExploradorArchivos();
        initBarraDeHerramientas();
        initComponents();
    }

    public void initComponents() {
        panelPrincipal = new JPanel();
        panelPrincipal.setSize(500, 500);
        panelPrincipal.setLayout(new BorderLayout());
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        setJMenuBar(menu);
        panelPrincipal.add(areaTexto, BorderLayout.CENTER);
        panelPrincipal.add(areaTerminal, BorderLayout.SOUTH);
        areaTerminal.setPreferredSize(new Dimension(200,200));
        panelPrincipal.add(exploradorArchivos, BorderLayout.WEST);
        exploradorArchivos.setPreferredSize(new Dimension(150,150));
        add(panelPrincipal);

        pack();
    }

        private void initBarraDeHerramientas() {

            menu = new JMenuBar();

            //Menu Archivo
            menuArchivo = new JMenu();
            menuArchivo.setText("Archivo");

            menuArchivoOpcionNuevo = new JMenuItem("Nuevo");
            menuArchivoOpcionAbrir = new JMenuItem("Abrir");
            menuArchivoOpcionGuardar = new JMenuItem("Guardar");
            menuArchivoOpcionGuardarComo = new JMenuItem("Guardar Como");
            menuArchivoOpcionImprimir = new JMenuItem("Imprimir");

            menuArchivo.add(menuArchivoOpcionNuevo);
            menuArchivo.add(menuArchivoOpcionAbrir);
            menuArchivo.add(menuArchivoOpcionGuardar);
            menuArchivo.add(menuArchivoOpcionGuardarComo);
            menuArchivo.add(menuArchivoOpcionImprimir);

            menuArchivoOpcionImprimir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    areaTexto.print();
                }
            });

            //Menu Edicion
            menuEdicion = new JMenu();
            menuEdicion.setText("Edición");

            menuEdicionOpcionDeshacer = new JMenuItem("Deshacer");
            menuEdicionOpcionCopiar = new JMenuItem("Copiar");
            menuEdicionOpcionCortar = new JMenuItem("Cortar");
            menuEdicionOpcionPegar = new JMenuItem("Pegar");
            menuEdicionOpcionEliminar = new JMenuItem("Eliminar");

            menuEdicion.add(menuEdicionOpcionDeshacer);
            menuEdicion.add(menuEdicionOpcionCopiar);
            menuEdicion.add(menuEdicionOpcionCortar);
            menuEdicion.add(menuEdicionOpcionPegar);
            menuEdicion.add(menuEdicionOpcionEliminar);

            // Menu Ayuda
            menuAyuda = new JMenu();
            menuAyuda.setText("Ayuda");

            menuAyudaOpcionAcercaDe = new JMenuItem("Acerca de");
            menuAyudaOpcionVerAyuda = new JMenuItem("Ver Ayuda");

            menuAyuda.add(menuAyudaOpcionAcercaDe);
            menuAyuda.add(menuAyudaOpcionVerAyuda);

            //Agrego menu
            menu.add(menuArchivo);
            menu.add(menuEdicion);
            menu.add(menuAyuda);

    }
}


