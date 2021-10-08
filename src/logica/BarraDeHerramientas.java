package logica;

import javax.swing.*;

public class BarraDeHerramientas {

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

    private JMenuItem menuEdicionOpcion;
    public BarraDeHerramientas() {
        initBarraDeHerramientas();
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

    // Set & Get
    public JMenuBar getMenu() {
        return menu;
    }

    public void setMenu(JMenuBar menu) {
        this.menu = menu;
    }
}