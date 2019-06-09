/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estrutura;

import javaapplication1.Desenhavel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author emerson.mferreira1
 */
public class Torre_terrestre extends Estrutura implements Desenhavel {

    private final BufferedImage SPRITE;

    public Torre_terrestre(int pos, BufferedImage sprite) {
        super(1, 3, 3, pos);
        this.SPRITE = sprite;
    }

    @Override
    public void paintComponent(Graphics g) {

    }

}
