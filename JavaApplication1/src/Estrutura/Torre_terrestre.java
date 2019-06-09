/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estrutura;

import javaapplication1.Inimigo;
import javaapplication1.Desenhavel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javaapplication1.Engine;

/**
 *
 * @author emerson.mferreira1
 */
public class Torre_terrestre extends Estrutura implements Desenhavel {

    private final BufferedImage SPRITE;
    private final BufferedImage SPRITE_ATAQUE;
    private int x, y;
    private boolean atacando;

    public Torre_terrestre(int pos, BufferedImage sprite, BufferedImage sprite_ataque) {
        // ataque, alcance, velo_atk, pos
        super(1, 1, 3, pos);
        this.SPRITE = sprite;
        this.SPRITE_ATAQUE = sprite_ataque;
        this.x = ((pos % Engine.QTD_COLUNAS) * Engine.TILE_SIZE);
        this.y = ((pos / Engine.QTD_COLUNAS) * Engine.TILE_SIZE);
    }

    public void atacar(Inimigo inimigo) {
        //System.out.println(inimigo);
        inimigo.reduzVida(this.getAtaque());
        this.atacando = true;
        System.out.println(this.atacando);
    }

    public void naoAtacar(Inimigo inimigo) {
        this.atacando = false;
        System.out.println(this.atacando);
    }

    private void update() {
        if (atacando) {

        } else if (!atacando) {
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        update();
        g.drawImage(SPRITE.getSubimage(0, 0, 40, 40), x, y, null);
        if (atacando) {
            g.drawImage(SPRITE_ATAQUE.getSubimage(68, 160, 40, 40), x, y, null);
        }
    }

}
