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
public class DrawPanel extends JPanel{
    private Tile_layer layer;
    private BufferedImage minotaur = null;    
    
    public DrawPanel(){
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
        // esse super estava no video, mas ele n estava aqui no código
        super.paintComponent(g);
        layer.Draw_layer(g);
        g.drawImage(minotaur.getSubimage(0, 0, 40, 40), 125, 100, null);
        g.drawImage(minotaur.getSubimage(160/* aqui a gente mexe no sprite, só mexe nesse
                pq é um vetor(só uma linha) e não uma matriz(spritesheet)*/, 0, 40, 40), 125, 140, null);
        //g.drawImage(minotaur, 100, 100, this);
        
    }
}

