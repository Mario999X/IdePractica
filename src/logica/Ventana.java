package logica;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {

    private BarraDeHerramientas menu;
    private AreaTexto areaTexto;
    private ExploradorArchivos exploradorArchivos;
    private JPanel panelPrincipal;

    public Ventana() {
        menu = new BarraDeHerramientas();
        areaTexto = new AreaTexto();
        exploradorArchivos = new ExploradorArchivos();
        initComponents();

    }

    public void initComponents() {
        panelPrincipal = new JPanel();
        panelPrincipal.setSize(500, 500);
        panelPrincipal.setLayout(new BorderLayout());
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        setJMenuBar(menu.getMenu());
        panelPrincipal.add(areaTexto, BorderLayout.CENTER);
        //panelPrincipal.add(exploradorArchivos.getPanelIzquierda());
        add(panelPrincipal);
        pack();
    }
}
