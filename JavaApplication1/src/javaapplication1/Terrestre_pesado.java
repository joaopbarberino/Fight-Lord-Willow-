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
public class Terrestre_pesado extends Inimigo implements Desenhavel {

    private final BufferedImage SPRITE;
    private final int qtdColunas = 20;
    private int posAtual, proxPos, difPos;
    private int x, y, prox_x, prox_y;
    private int x_aux, y_aux, por_x, por_y, velX, velY;
    private boolean pode_andar = false, andou = true;
    private String movimento = "";

    public Terrestre_pesado(BufferedImage sprite, ArrayList<Integer> caminho) {
        // Valores de vida, ataque, defesa, velocidade de movimento, gold, xp 
        // e tipo, respectivamente
        //coloquei null no ultimo parametro que seria o da img só pra parar de dar erro.
        super(20, 2, 2, 1, 10, 20, "terrestre", caminho);
        this.SPRITE = sprite;

    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public BufferedImage getSprite() {
        return this.SPRITE;
    }
    //ESSA TA PRONTA,SÓ FALTA FAZER OS CALCULOS AQUI EMBAIXO E NOS VALORES DENTRO DO CONSTRUTOR

    public void andar() {
        super.andar(pode_andar);
    }

    public void update() {
        if (andou) {

            this.posAtual = this.getPos();
            //System.out.println("Atual: " + this.posAtual);
            this.proxPos = super.getProxPos();
            //System.out.println("Prox: " + this.proxPos);
            this.difPos = proxPos - posAtual;

            if (difPos == 20) {
                this.movimento = "baixo";
            } else if (difPos == -20) {
                this.movimento = "cima";
            } else if (difPos == 1) {
                this.movimento = "direita";
            }

            //System.out.println("sdpofjdsf");
            //System.out.println("eba" + this.movimento);
            this.x = (int) ((this.getPos() % qtdColunas) * Engine.TILE_SIZE);
            //System.out.println("x :"+this.x);
            this.prox_x = (int) ((proxPos % qtdColunas) * Engine.TILE_SIZE);
            //System.out.println("prox_x :"+this.prox_x);
            this.y = (int) ((this.getPos() / qtdColunas) * Engine.TILE_SIZE);
            //System.out.println("y :"+this.y);
            this.prox_y = (int) ((proxPos / qtdColunas) * Engine.TILE_SIZE);
            //System.out.println("prox y :"+this.prox_y);
            this.y_aux = y;
            this.x_aux = x;
            if (movimento.equals("baixo")) {
                this.y_aux = y;
                //this.por_y = x / 100;
                setVelY(5);
                setVelX(0);
            } else if (movimento.equals("direita")) {
                this.x_aux = x;
                //this.por_x = x / 100;
                setVelX(5);
                setVelY(0);
            } else if (movimento.equals("cima")) {
                setVelX(0);
                setVelY(5);
                //this.por_y = x / 100;
            }
            andou = false;
            pode_andar = false;
            System.out.println("aux_Y :" + this.y_aux);
            System.out.println("prox_Y :" + this.prox_y);
            System.out.println("Aux_x" + this.x_aux);
            System.out.println("prox_X :" + this.prox_x);
            System.out.println(this.movimento);
            switch (movimento) {
                case "baixo":
                    y_aux += this.velY;
                    System.out.println("Y_aux depois :"+y_aux);
                case "cima":
                    y_aux -= this.velY;
                case "direita":
                    x_aux += this.velX;
                default:
                    break;
            }
        } else if (x_aux == prox_x || y_aux == prox_y) {
            System.out.println("aaaa eu entrei");
            pode_andar = true;
            andou = true;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        update();
//        switch (movimento) {
//            case "baixo":
//                y_aux += por_y;
//            case "cima":
//                y_aux -= por_y;
//            case "direita":
//                x_aux += por_x;
//            default:
//                break;
//        }

        g.drawImage(this.SPRITE.getSubimage(0, 0, 40, 40), x_aux, y_aux, null);
        if(movimento.equals("baixo") || y_aux != prox_y) {
            g.drawImage(this.SPRITE.getSubimage(0, 0, 40, 40), x_aux, y_aux+=this.velY, null);
        }
    }
}
