package javaapplication1;

import java.awt.Color;
import javax.swing.JFrame;
import java.awt.Graphics;
import javax.swing.*;

/**
 *
 * @author pedro.hlaredes
 */
public class Tela extends JFrame {
    JPanel panel = new JPanel();

    public Tela() {
        super("Attack, Lord Willow!");
        this.setSize(1280, 900);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void Initialize() {
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        
        // limpa a janela
        //this.panel.setBounds(0, 0, 1280, 900);
        //this.panel.setBackground(Color.green);
        //this.add(panel);
        
        g.setColor(Color.green);

        g.fillRect(0,0,1280, 900);
        
        g.setColor(Color.red);
        g.fillOval(0, 0, 50, 50);
        
    }
}
