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
import java.util.ArrayList;
import javaapplication1.Engine;
import javaapplication1.Som;

/**
 *
 * @author emerson.mferreira1
 */
public class Torre_terrestre extends Estrutura implements Desenhavel {

    private final BufferedImage SPRITE;
    private final ArrayList<Som> Sons;
    private final BufferedImage SPRITE_ATAQUE;
    private int x, y;
    private boolean atacando, hitou = false;
    private Inimigo alvo;
    private int contador = 0;
    private int conta_sprite = 1;

    private int pos_tiro, alvo_tiro_novo, alvo_tiro_antigo, x_tiro, y_tiro, x_alvo, y_alvo, dif_pos;
    private String movimento = "";

    public Torre_terrestre(int pos, BufferedImage sprite, BufferedImage sprite_ataque, ArrayList<Som> sounds) {
        // ataque, alcance, preco, pos
        super(5, 1, 50, pos);
        this.SPRITE = sprite;
        this.pos_tiro = pos;
        this.SPRITE_ATAQUE = sprite_ataque;
        this.x = ((pos % Engine.QTD_COLUNAS) * Engine.TILE_SIZE);
        this.x_tiro = x;
        this.y = ((pos / Engine.QTD_COLUNAS) * Engine.TILE_SIZE);
        this.y_tiro = y;
        this.Sons = sounds;
    }

    public void atacar(Inimigo inimigo) {
        //System.out.println(inimigo);

        //if (inimigo.getTipo().equals("terrestre")) {
        this.alvo = inimigo;
        this.atacando = true;
        this.x_alvo = ((alvo.getPos() % Engine.QTD_COLUNAS) * Engine.TILE_SIZE);
        this.y_alvo = ((alvo.getPos() / Engine.QTD_COLUNAS) * Engine.TILE_SIZE);
        this.alvo_tiro_antigo = alvo.getPos();
        this.dif_pos = alvo.getPos() - this.getPos();

        if (dif_pos == -1) {
            this.movimento = "esquerda";
        } else if (dif_pos == 1) {
            this.movimento = "direita";
        } else if (dif_pos == -20) {
            this.movimento = "cima";
        } else if (dif_pos == 20) {
            this.movimento = "baixo";
        } else if (dif_pos == -21) {
            this.movimento = "diagonal sup esquerda";
        } else if (dif_pos == 21) {
            this.movimento = "diagonal inf direita";
        } else if (dif_pos == 19) {
            this.movimento = "diagonal inf esquerda";
        } else if (dif_pos == -19) {
            this.movimento = "diagonal sup direita";
        }

        //}
    }

    private void update() {

        if (atacando) {
            this.alvo_tiro_novo = alvo.getPos();
            if (alvo_tiro_antigo != alvo_tiro_novo) {
                //System.out.println(alvo.getPos());
                this.alvo_tiro_antigo = alvo_tiro_novo;
                //System.out.println("tiro seguiu");
            }

            if (!this.isNoRange(alvo) || this.alvo.isMorto()) {
                this.alvo = null;
                this.atacando = false;
                x_tiro = this.x;
                y_tiro = this.y;
            }
        }
    }

    public void tocaSom(int ref) {
        Sons.get(ref).tocaUmaVez();
    }

    @Override
    public void paintComponent(Graphics g) {
        update();
        g.drawImage(SPRITE.getSubimage(0, 0, 40, 40), x, y, null);

        if (atacando) {

            if (this.contador % 20 == 0) {
                if (this.alvo != null) {
                    this.alvo_tiro_antigo = alvo.getPos();
                    alvo.reduzVida(this.getAtaque());
                    this.contador = 0;
                }
            }
            this.contador++;

            if (!hitou) {
//                if (movimento.equals("cima")) {
//                    
//                } else if (movimento.equals("diagonal sup direita")) {
//                    
//                    
//                } else if (movimento.equals("direita")) {
//                    
//                } else if (movimento.equals("diagonal inf esquerda")) {
//                    
//                    
//                } else if (movimento.equals("baixo")) {
//                   
//                } else if (movimento.equals("diagonal inf esquerda")) {
//                    
//                    
//                } else if (movimento.equals("esquerda")) {
//                    
//                } else if (movimento.equals("diagonal sup direita")) {
//                    
//                }

                if (y_tiro == y_alvo && x_tiro == x_alvo) {
                    hitou = true;
                }

                g.drawImage(SPRITE_ATAQUE.getSubimage(68, 160, 40, 40), x_tiro, y_tiro, null);
            } else {
                hitou = false;
                x_tiro = this.x;
                y_tiro = this.y;
            }
        }
    }

}
