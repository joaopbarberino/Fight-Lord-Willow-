/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author joao.pbsilva20
 */
public class Terrestre_pesado extends Inimigo implements Desenhavel{

    private final BufferedImage SPRITE;

    public Terrestre_pesado() {
        // Valores de vida, ataque, defesa, velocidade de movimento, gold, xp 
        // e tipo, respectivamente
        super(20, 2, 2, 1, 10, 20, "terrestre");
        this.SPRITE = setSprite();
    }

    private BufferedImage setSprite() {
        try {
            System.out.println("achou img");
            return ImageIO.read(new File("minitaur.png"));

        } catch (IOException e) {
            System.out.println("Não foi possível ler a imagem");
        }

        return null;
    }

    @Override public void paintComponent(Graphics g){ 
       System.out.println("asdsadas");
       g.drawImage(this.SPRITE, 200, 200, null);
    }
}
