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
    private int x_aux, y_aux, por_x, por_y;
    private boolean pode_andar = false, andou = true;
    private String movimento = "";

    public Terrestre_pesado(BufferedImage sprite, ArrayList<Integer> caminho) {
        // Valores de vida, ataque, defesa, velocidade de movimento, gold, xp 
        // e tipo, respectivamente
        //coloquei null no ultimo parametro que seria o da img só pra parar de dar erro.
        super(20, 2, 2, 1, 10, 20, "terrestre", caminho);
        this.SPRITE = sprite;

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
            System.out.println("Atual: " + this.posAtual);
            this.proxPos = super.getProxPos();
            System.out.println("Prox: " + this.proxPos);
            this.difPos = proxPos - posAtual;

            if (difPos == 20) {
                this.movimento = "baixo";
            } else if (difPos == -20) {
                this.movimento = "cima";
            } else if (difPos == 1) {
                this.movimento = "direita";
            }

            System.out.println("sdpofjdsf");
            this.x = (int) ((this.getPos() % qtdColunas) * Engine.TILE_SIZE);
            this.prox_x = (int) ((proxPos % qtdColunas) * Engine.TILE_SIZE);
            this.y = (int) ((this.getPos() / qtdColunas) * Engine.TILE_SIZE);
            this.prox_y = (int) ((proxPos / qtdColunas) * Engine.TILE_SIZE);

            if (movimento.equals("baixo") || movimento.equals("cima")) {
                this.y_aux = y;
                this.por_y = y / 100;
            } else if (movimento.equals("direita")) {
                this.x_aux = x;
                this.por_x = x / 100;
            }

            andou = false;
            pode_andar = false;
        } else if (x_aux == prox_x || y_aux == prox_y) {
            pode_andar = true;
            andou = true;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        update();
        switch (movimento) {
            case "baixo":
                y_aux += por_y;
            case "cima":
                y_aux -= por_y;
            case "direita":
                x_aux += por_x;
            default:
                break;
        }

        g.drawImage(this.SPRITE.getSubimage(0, 0, 40, 40), x_aux, y_aux, null);
    }
}
