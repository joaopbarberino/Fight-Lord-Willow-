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
    private final int VEL_MOVIMENTO = 10;
    
    private int posAtual, proxPos, difPos;
    private int x, y, prox_x, prox_y;
    private int x_aux, y_aux, por_x, por_y;
    private int contaSprite = 0;
    private int maxSprite = 8;
    private boolean trocaAnimação = false;
    private boolean pode_andar = false, andou = true;
    private String movimento = "";
//Vida:10
//Dano: 2 
//Defesa:2
//Gold: +10 
//Exp: +10 

    public Terrestre_leve(BufferedImage sprite, ArrayList<Integer> caminho) {
        //Valores de vida, ataque, defesa, velocidade de movimento, gold, xp 
        //e tipo, respectivamente
        //coloquei null no ultimo parametro que seria o da img só pra parar de dar erro.
        super(10, 2, 2, 10, 10, "terrestre", caminho);
        this.SPRITE = sprite;
    }

    public BufferedImage getSprite() {
        return this.SPRITE;
    }

    public void andar() {
        super.andar(pode_andar);
    }
    
    public void update() {

        if (this.andou == true) {

            this.posAtual = this.getPos();
            this.proxPos = super.getProxPos();
            this.difPos = proxPos - posAtual;

            if (difPos == 20 || difPos == 0) {
                this.movimento = "baixo";
            } else if (difPos == -20) {
                this.movimento = "cima";
            } else if (difPos == 1) {
                this.movimento = "direita";
            }

            this.x = ((posAtual % qtdColunas) * Engine.TILE_SIZE);
            this.prox_x = ((proxPos % qtdColunas) * Engine.TILE_SIZE);
            this.y = ((posAtual / qtdColunas) * Engine.TILE_SIZE);
            this.prox_y = ((proxPos / qtdColunas) * Engine.TILE_SIZE);

            this.y_aux = y;
            this.x_aux = x;

            switch (movimento) {
                case "baixo":
                    this.y_aux = y;
                    this.por_y = difPos / VEL_MOVIMENTO;
                    break;

                case "direita":
                    this.x_aux = x;
                    this.por_x = difPos / VEL_MOVIMENTO;
                    break;

                case "cima":
                    this.y_aux = y;
                    this.por_y = Math.abs(difPos) / VEL_MOVIMENTO;
                    break;
            }

            if (por_x == 0 && movimento.equals("direita")) {
                this.por_x = 1;
                this.por_y = 0;
            }

            if (por_y == 0 && (movimento.equals("cima") || movimento.equals("baixo"))) {
                this.por_y = 1;
                this.por_x = 0;
            }

            this.andou = false;
            this.pode_andar = false;
        }
         if (trocaAnimação == true) {
            contaSprite++;
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        update();

        switch (movimento) {
            case "baixo":
                this.y_aux += por_y;
                if (y_aux >= prox_y) {
                    this.andou = true;
                    this.pode_andar = true;
                }
                break;

            case "cima":
                this.y_aux -= por_y;
                if (y_aux <= prox_y) {
                    this.andou = true;
                    this.pode_andar = true;
                }
                break;

            case "direita":
                this.x_aux += por_x;
                if (x_aux >= prox_x) {
                    this.andou = true;
                    this.pode_andar = true;
                }
                break;

            default:
                break;
        }

        if (contaSprite == maxSprite) {
            contaSprite = 0;
        }


        g.drawImage(this.SPRITE.getSubimage(contaSprite * 40, 0, 40, 40), x_aux, y_aux, null);
        trocaAnimação = !trocaAnimação;

    }
}
