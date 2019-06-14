//***********************************************************/
//********************Sem Referencias************************/
//***********************************************************/
package Inimigo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Interfaces.Desenhavel;
import Fight.Lord.Willow.Engine;

/**
 *
 * @author joao.pbsilva20
 */
public class Terrestre_leve extends Inimigo implements Desenhavel {

    private final BufferedImage SPRITE, SPRITE_MORTE;
    private final int qtd_colunas = 20;
    private final int VEL_MOVIMENTO = 10;

    private int pos_atual, prox_pos, dif_pos;
    private int x, y, prox_x, prox_y;
    private int x_aux, y_aux, por_x, por_y;
    private int conta_sprite = 0;
    private int conta_sprite_morte = 0;
    private int max_sprite = 8;
    private int max_sprite_morte = 5;
    private boolean troca_animacao = false;
    private boolean troca_animacao_morte = false;
    public boolean acabou_animacao_morte = false;
    private boolean pode_andar = false, andou = true;
    private String movimento = "";

    //Vida:10
    //Dano: 2 
    //Defesa:2
    //Gold: +15 
    //Exp: +10 
    public Terrestre_leve(ArrayList<BufferedImage> sprites, ArrayList<Integer> caminho) {
        //Valores de vida, ataque, defesa, velocidade de movimento, gold, xp 
        //e tipo, respectivamente
        //coloquei null no ultimo parametro que seria o da img só pra parar de dar erro.
        super(10, 2, 2, 15, 10, "terrestre", caminho, 4);
        this.SPRITE = sprites.get(4);
        this.SPRITE_MORTE = sprites.get(5);

    }

    public BufferedImage getSprite() {
        return this.SPRITE;
    }

    public void andar() {
        super.andar(pode_andar);
    }

    public void update() {

        if (this.andou == true) {

            this.pos_atual = this.getPos();
            this.prox_pos = super.getProxPos();
            this.dif_pos = prox_pos - pos_atual;

            if (dif_pos == 20 || dif_pos == 0) {
                this.movimento = "baixo";
            } else if (dif_pos == -20) {
                this.movimento = "cima";
            } else if (dif_pos == 1) {
                this.movimento = "direita";
            }

            this.x = ((pos_atual % qtd_colunas) * Engine.TILE_SIZE);
            this.prox_x = ((prox_pos % qtd_colunas) * Engine.TILE_SIZE);
            this.y = ((pos_atual / qtd_colunas) * Engine.TILE_SIZE);
            this.prox_y = ((prox_pos / qtd_colunas) * Engine.TILE_SIZE);

            this.y_aux = y;
            this.x_aux = x;

            switch (movimento) {
                case "baixo":
                    this.y_aux = y;
                    this.por_y = dif_pos / VEL_MOVIMENTO;
                    break;

                case "direita":
                    this.x_aux = x;
                    this.por_x = dif_pos / VEL_MOVIMENTO;
                    break;

                case "cima":
                    this.y_aux = y;
                    this.por_y = Math.abs(dif_pos) / VEL_MOVIMENTO;
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
        if (troca_animacao == true) {
            conta_sprite++;
        }

        if (troca_animacao_morte == true && conta_sprite_morte < max_sprite_morte) {
            conta_sprite_morte++;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        update();

        if (!this.isMorto()) {
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

            if (conta_sprite == max_sprite) {
                conta_sprite = 0;
            }

            g.drawImage(this.SPRITE.getSubimage(conta_sprite * 40, 0, 40, 40), x_aux, y_aux, null);
            troca_animacao = !troca_animacao;

        } else if (this.isMorto()) {
            g.drawImage(this.SPRITE_MORTE.getSubimage(conta_sprite_morte * 40, 0, 40, 40), x_aux, y_aux, null);
            troca_animacao_morte = !troca_animacao_morte;
            if (conta_sprite_morte == max_sprite_morte) {
                this.finalizarAnimacaoMorte();
            }
        }
    }
}
