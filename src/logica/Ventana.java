package logica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Ventana extends JFrame {

    private AreaTexto areaTexto;
    private Terminal areaTerminal;
    private ExploradorArchivos exploradorArchivos;
    private JPanel panelPrincipal;
    private File archivo;

    //Menu
    private JMenuBar menu;
    private JMenu menuArchivo;
    private JMenuItem menuArchivoOpcionNuevo;
    private JMenuItem menuArchivoOpcionAbrir;
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
        this.setTitle("Ide Mario Resa - " + "Sin titulo");

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

            menuArchivoOpcionAbrir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        abrirAccion();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }
            });

            menuArchivoOpcionGuardarComo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { guardarComoAccion();
                }
            });

            menuArchivoOpcionGuardar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        guardarAccion();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }
            });

            menuArchivoOpcionNuevo.addActionListener(e -> nuevoAccion());

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

    private void guardarComoAccion(){

            JFileChooser selector=new JFileChooser();
            int opcion = selector.showSaveDialog(this);
            archivo = selector.getSelectedFile();
            try (FileWriter escritor = new FileWriter(archivo)) {
                if (opcion == JFileChooser.APPROVE_OPTION)
                    if(archivo !=null)
                        escritor.write(areaTexto.getText());
                        JOptionPane.showMessageDialog(null, "El archivo se ha guardado satisfactoriamente");
            }
            catch(IOException e) {
                System.out.println("Error: "+ e.getMessage());
            }
    }

    private void guardarAccion() throws IOException {
        if (archivo == null){
            guardarComoAccion();
        }else {
            FileWriter escritor = new FileWriter(archivo);
            escritor.write(areaTexto.getText());
            JOptionPane.showMessageDialog(null, "El archivo se ha guardado satisfactoriamente");
            escritor.close();
        }
    }

    private void abrirAccion() throws IOException {
        if(!areaTexto.getText().equals("")){
            if(JOptionPane.showConfirmDialog(menuArchivoOpcionAbrir, "Deberias guardar","Warning", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE)==JOptionPane.YES_OPTION);
            guardarAccion();
        }
        JFileChooser selector = new JFileChooser();

        int opcion = selector.showOpenDialog(this);
        archivo = selector.getSelectedFile();
        this.setTitle("Ide Mario Resa - " + archivo.getName());
        if(opcion == JFileChooser.APPROVE_OPTION){
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            areaTexto.setText("");
            String line = br.readLine();
            while(!(line == null)){
                areaTexto.getEspacioEscribir().append(line + "\n");
                line = br.readLine();
            }br.close();
        }

    }

    private void nuevoAccion(){
        archivo = null;
        areaTexto.setText("");
        this.setTitle("Ide Mario Resa - " + "Sin titulo");

    }

}


