/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.awt.image.BufferedImage;

/**
 *
 * @author joao.pbsilva20
 */
public class Terrestre_pesado extends Inimigo {
    private BufferedImage minotaur;
    
    public Terrestre_pesado() {
        // Valores de vida, ataque, defesa, velocidade de movimento, gold, xp 
        // e tipo, respectivamente
        super(20, 2, 2, 1, 10, 20, "terrestre", null);
        Sprite_sheet s1 = new Sprite_sheet("edtfhjfgz");
        minotaur = s1.getSprite(112, 16, 16, 16);
    }
}
