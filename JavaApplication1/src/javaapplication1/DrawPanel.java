package javaapplication1;

import java.awt.Color;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author pedro.hlaredes
 */
public class DrawPanel {//extends JPanel implements Desenhavel {
    private Tile_layer layer;
    private BufferedImage minotaur = null;    
    
    /*public DrawPanel(){
        layer = Tile_layer.From_file("mapa.txt");
        
        try {
            this.minotaur = ImageIO.read(new File("minitaur.png"));
        } catch (IOException e) {
            System.out.print("Nao consegui ler a imagem!");
        }
    }
    
    public void setSprite(BufferedImage sprite){
        this.minotaur = sprite;
    }
    
    @Override public void paintComponent(Graphics g){
        layer.paintComponent(g);
                
    }*/
}

