package logica;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {

    private BarraDeHerramientas menu;
    private AreaTexto areaTexto;
    private Terminal areaTerminal;
    private ExploradorArchivos exploradorArchivos;
    private JPanel panelPrincipal;

    public Ventana() {
        menu = new BarraDeHerramientas();
        areaTerminal = new Terminal();
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
        panelPrincipal.add(areaTerminal, BorderLayout.SOUTH);
        areaTerminal.setPreferredSize(new Dimension(200,200));
        panelPrincipal.add(exploradorArchivos, BorderLayout.WEST);
        exploradorArchivos.setPreferredSize(new Dimension(150,150));
        add(panelPrincipal);
        pack();
    }
}
