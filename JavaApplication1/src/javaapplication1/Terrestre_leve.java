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
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author joao.pbsilva20
 */
public class Terrestre_leve extends Inimigo implements Desenhavel {

    private final BufferedImage SPRITE;
    private final int qtdColunas = 20;

    public Terrestre_leve(BufferedImage sprite, ArrayList<Integer> caminho) {
        //Valores de vida, ataque, defesa, velocidade de movimento, gold, xp 
        //e tipo, respectivamente
        //coloquei null no ultimo parametro que seria o da img só pra parar de dar erro.
        super(20, 2, 2, 1, 10, 20, "terrestre", caminho);
        this.SPRITE = sprite;
    }

    public BufferedImage getSprite() {
        return this.SPRITE;
    }
        //ESSA TA PRONTA,SÓ FALTA FAZER OS CALCULOS AQUI EMBAIXO E NOS VALORES DENTRO DO CONSTRUTOR

    @Override
    public void paintComponent(Graphics g) {
        int x = (int) ((this.getPos() % qtdColunas)*Engine.TILE_SIZE);  
        int y = (int) ((this.getPos() / qtdColunas) * Engine.TILE_SIZE);
      
        g.drawImage(this.SPRITE.getSubimage(0, 0, 40, 40), x, y, null);
    }
}
