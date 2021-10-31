package logica;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.URISyntaxException;
import java.util.Objects;

public class Ventana extends JFrame {

    private AreaTexto areaTexto;
    private Terminal areaTerminal;
    private ExploradorArchivos exploradorArchivos;
    private JPanel panelPrincipal;
    private File archivo;
    private Clipboard clipboard;
    private UndoManager deshacer;
    private JSplitPane superior;
    private JSplitPane inferior;


    //Menu
    private JMenuBar menu;
    private JMenu menuArchivo;
    private JMenuItem menuArchivoOpcionNuevo;
    private JMenuItem menuArchivoOpcionAbrir;
    private JMenuItem menuArchivoOpcionGuardar;
    private JMenuItem menuArchivoOpcionGuardarComo;
    private JMenuItem menuArchivoOpcionImprimir;
    private JButton compilar;
    private JButton ejecutar;

    private JMenu menuEdicion;
    private JMenuItem menuEdicionOpcionDeshacer;
    private JMenuItem menuEdicionOpcionRehacer;
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
        deshacer = new UndoManager();
        deshacer.setLimit(10);
        initBarraDeHerramientas();
        initComponents();
    }

    public void initComponents() {
        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        panelPrincipal = new JPanel();
        panelPrincipal.setSize(500, 500);
        panelPrincipal.setLayout(new BorderLayout());
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        setJMenuBar(menu);
        superior = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, exploradorArchivos, areaTexto);
        inferior = new JSplitPane(JSplitPane.VERTICAL_SPLIT, superior, areaTerminal);
        panelPrincipal.add(inferior, BorderLayout.CENTER);
        add(panelPrincipal);
        this.setTitle("Ide Mario Resa - " + "Sin titulo");

        pack();
    }

    private void initBarraDeHerramientas() {

        menu = new JMenuBar();

        compilar = new JButton("Compilar");
        ejecutar = new JButton("Run");

        //MENU ARCHIVO
        //Generacion general
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
        menuEdicionOpcionRehacer = new JMenuItem("Rehacer");
        menuEdicionOpcionCopiar = new JMenuItem("Copiar");
        menuEdicionOpcionCortar = new JMenuItem("Cortar");
        menuEdicionOpcionPegar = new JMenuItem("Pegar");
        menuEdicionOpcionEliminar = new JMenuItem("Eliminar");

        menuEdicion.add(menuEdicionOpcionDeshacer);
        menuEdicion.add(menuEdicionOpcionRehacer);
        menuEdicion.add(menuEdicionOpcionCopiar);
        menuEdicion.add(menuEdicionOpcionCortar);
        menuEdicion.add(menuEdicionOpcionPegar);
        menuEdicion.add(menuEdicionOpcionEliminar);

        menuEdicionOpcionCopiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { copiarAccion();

            }
        });

        menuEdicionOpcionCortar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { cortarAccion();

            }
        });

        menuEdicionOpcionDeshacer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { deshacerAccion();

            }
        });

        menuEdicionOpcionPegar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { pegarAccion();

            }
        });

        menuEdicionOpcionRehacer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { rehacerAccion();

            }

        });

        menuEdicionOpcionEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { eliminarAccion();

            }
        });

        ejecutar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    run();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        compilar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    compilacion();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        // Menu Ayuda
        menuAyuda = new JMenu();
        menuAyuda.setText("Ayuda");

        menuAyudaOpcionAcercaDe = new JMenuItem("Acerca de");
        menuAyudaOpcionVerAyuda = new JMenuItem("Ver Ayuda");

        menuAyuda.add(menuAyudaOpcionAcercaDe);
        menuAyuda.add(menuAyudaOpcionVerAyuda);

        menuAyudaOpcionAcercaDe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inf();
            }
        });

        menuAyudaOpcionVerAyuda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    url();
                } catch (URISyntaxException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        //Agrego menu
        menu.add(menuArchivo);
        menu.add(menuEdicion);
        menu.add(menuAyuda);
        menu.add(ejecutar);
        menu.add(compilar);

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
            if(JOptionPane.showConfirmDialog(menuArchivoOpcionAbrir, "Deberias guardar el archivo actual","Warning", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE)==JOptionPane.YES_OPTION);
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

    private void copiarAccion(){
        if (areaTexto.getEspacioEscribir().getSelectedText() != null){
            StringSelection selector = new StringSelection("" + areaTexto.getEspacioEscribir().getSelectedText());
            clipboard.setContents(selector,selector);
        }
    }


    private void cortarAccion(){

        if (areaTexto.getEspacioEscribir().getSelectedText() != null){
            StringSelection selector = new StringSelection("" + areaTexto.getEspacioEscribir().getSelectedText());
            clipboard.setContents(selector,selector);
            areaTexto.getEspacioEscribir().replaceSelection("");
        }
    }


    private void pegarAccion(){

        Transferable datos = clipboard.getContents(null);
        try{
            if (datos != null && datos.isDataFlavorSupported(DataFlavor.stringFlavor))
                areaTexto.getEspacioEscribir().replaceSelection("" + datos.getTransferData(DataFlavor.stringFlavor));
        } catch (UnsupportedFlavorException | IOException ex){System.err.println(ex);}
    }

    private void deshacerAccion(){
        if (deshacer.canUndo()){
            deshacer.undo();
        }
    }

    private void rehacerAccion(){
        if (deshacer.canRedo()){
            deshacer.redo();
        }
    }

    private void eliminarAccion(){
        if (areaTexto.getEspacioEscribir().getSelectedText() != null){
            areaTexto.getEspacioEscribir().replaceSelection("");
        }
    }



    private void run() throws IOException {

        if (Objects.equals(areaTexto.getText(), "") || archivo == null) {
            JOptionPane.showMessageDialog(null, " Selecciona un archivo válido ", "Dialogo de alerta",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            guardarAccion();
            Runtime cmd = Runtime.getRuntime();
            String runJava = "java " + archivo.getPath();
            readInConsole(cmd, runJava);
            JOptionPane.showMessageDialog(null, "En funcionamiento", "Message Dialog", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void compilacion() throws IOException {
        if (Objects.equals(areaTexto.getText(), "") || archivo == null) {
            JOptionPane.showMessageDialog(null, " Selecciona un archivo valido", "Dialogo de alerta",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            guardarAccion();
            Runtime cmd = Runtime.getRuntime();
            String buildJava = "javac " + archivo.getPath();
            readInConsole(cmd, buildJava);
            JOptionPane.showMessageDialog(null, "Compilacion realizada correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void readInConsole(Runtime cmd, String command) throws IOException {

        Process proc = cmd.exec(command);
        InputStream inputStream = proc.getInputStream();
        InputStream errorStream = proc.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        InputStreamReader errorStreamReader = new InputStreamReader(errorStream);
        BufferedReader inputBufferedReader = new BufferedReader(inputStreamReader);
        BufferedReader errorBufferedReader = new BufferedReader(errorStreamReader);
        String inputline = "";
        String errorline = "";
        areaTerminal.setText("");
        areaTerminal.getTerminal().setForeground(Color.WHITE);
        while ((inputline = inputBufferedReader.readLine()) != null) {
            areaTerminal.getTerminal().append(inputline + "\n");
        }
        while ((errorline = errorBufferedReader.readLine()) != null) {
            areaTerminal.getTerminal().append(errorline + "\n");
            areaTerminal.getTerminal().setForeground(Color.RED);
        }
    }

    private void inf(){
        JOptionPane.showMessageDialog(this,"Mario Resa");
    }

    private void url() throws URISyntaxException, IOException {
        if (java.awt.Desktop.isDesktopSupported()) {
            java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

            if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
                java.net.URI uri = new java.net.URI("https://github.com/Mario999X/IdePractica");
                desktop.browse(uri);
            }
        }
    }


}

