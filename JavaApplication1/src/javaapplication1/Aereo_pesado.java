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
public class Aereo_pesado extends Inimigo {

    private BufferedImage[] minotaurs_anda, minotaurs_parado;

    public Aereo_pesado() {
        // Valores de vida, ataque, defesa, velocidade de movimento, gold, xp 
        // e tipo, respectivamente
        super(10, 2, 5, 1, 10, 20, "aereo", null);
        minotaurs_anda = new BufferedImage[4];
        minotaurs_parado = new BufferedImage[4];
        Sprite_sheet minotaur_anda = new Sprite_sheet("/pics/minitaur.png");
        for (int i = 0; i < minotaurs_anda.length; i++) {
            minotaurs_anda[i] = minotaur_anda.getSprite(i*Engine.TILE_SIZE, 0, Engine.TILE_WIDTH, Engine.TILE_HEIGTH);
        }
    }
    @Override
    public void render(Graphics g) {
        //caso minotauro ande, carrega sprite dele andando, caso contrario, dele parado
        if (andar()) {
            for (int i = 0; i < minotaurs_anda.length; i++) {
                g.drawImage(minotaurs_anda[i], 150, 150, null);
            }
            //g.drawImage(sprites[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
        } else {
            for (int i = 0; i < minotaurs_anda.length; i++) {
                g.drawImage(minotaurs_anda[i], 150, 150, null);
            }
            //g.drawImage(Entity.ENEMY_FEEDBACK, this.getX() - Camera.x, this.getY() - Camera.y, null);
        }
        //g.setColor(Color.blue);
        //g.fillRect(this.getX() + maskX - Camera.x,this.getY() + maskY - Camera.y, maskW,maskH);
    }

}
