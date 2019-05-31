package javaapplication1;

import java.awt.Color;
import javax.swing.JFrame;
import java.awt.Graphics;
import javax.swing.*;

/**
 *
 * @author pedro.hlaredes
 */
public class DrawPanel extends JPanel {
    private Tile_layer layer, minotaur;
    
    public DrawPanel(){
        layer = Tile_layer.From_file("mapa.txt");
        //minotaur = Tile_layer.From_file("minitaur.txt");
    }
    
    @Override public void paintComponent(Graphics g){
        super.paintComponent(g);
        //minotaur.Draw_layer(g);
        layer.Draw_layer(g);
    }
}
