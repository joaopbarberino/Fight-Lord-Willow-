/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author joao.pbsilva20
 */
public class Terrestre_pesado extends Inimigo {

    private BufferedImage[] minotaur_anda, minotaur_parado;

    public Terrestre_pesado() {
        // Valores de vida, ataque, defesa, velocidade de movimento, gold, xp 
        // e tipo, respectivamente
        super(20, 2, 2, 1, 10, 20, "terrestre", null);
        this.minotaur_anda = new BufferedImage[4];
        this.minotaur_parado = new BufferedImage[4];
        Sprite_sheet s1 = new Sprite_sheet("/pics/minitaur.png");
        for (int i = 0; i < this.minotaur_anda.length; i++) {
            this.minotaur_anda[i] = s1.getSprite(i * Engine.TILE_SIZE, 0, Engine.TILE_WIDTH, Engine.TILE_HEIGTH);
        }
    }

    public void render() {
        if (super.isAndando()) {
            super.render(this.minotaur_anda);
        } else {
            super.render(this.minotaur_anda);
        }
    }
}
