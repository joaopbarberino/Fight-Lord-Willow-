//***********************************************************/
//********************Sem Referencias************************/
//***********************************************************/
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
 * @author PEDROKREIDABALA
 */
public class Torre_aerea extends Estrutura implements Desenhavel {

    private final BufferedImage SPRITE, SPRITE_ATAQUE, SPRITE_TIRO;
    private final ArrayList<Som> Sons;
    private int x, y;
    private boolean atacando, hitou = false;
    private Inimigo alvo = null;
    private int contador = 0;
    private int conta_sprite = 0, max_sprite = 10, conta_sprite_ataque = 0, max_sprite_ataque = 6, conta_sprite_tiro = 0, max_sprite_tiro = 4;
    private boolean troca_animacao = false, troca_animacao_ataque = false, troca_animacao_tiro = false;

    private int x_tiro, y_tiro, x_tiro_por, y_tiro_por, x_alvo, y_alvo;

    public Torre_aerea(int pos, ArrayList<BufferedImage> sprites, ArrayList<Som> sounds) {
        // ataque, alcance, preco, pos
        super(4, 1, 100, pos);
        this.SPRITE = sprites.get(13);
        this.SPRITE_ATAQUE = sprites.get(15);
        this.SPRITE_TIRO = sprites.get(9);
        this.x = ((pos % Engine.QTD_COLUNAS) * Engine.TILE_SIZE);
        this.x_tiro = x;
        this.y = ((pos / Engine.QTD_COLUNAS) * Engine.TILE_SIZE);
        this.y_tiro = y;
        this.Sons = sounds;
    }

    public void atacar(Inimigo inimigo) {

        if (inimigo.getTipo().equals("aereo")) {
            this.alvo = inimigo;
            this.atacando = true;

            this.x_alvo = ((alvo.getPos() % Engine.QTD_COLUNAS) * Engine.TILE_SIZE);
            this.y_alvo = ((alvo.getPos() / Engine.QTD_COLUNAS) * Engine.TILE_SIZE);

            this.x_tiro_por = (x_alvo - x_tiro) / 5;
            this.y_tiro_por = (y_alvo - y_tiro) / 5;

        }
    }

    private void update() {

        if (atacando) {
            this.x_alvo = ((alvo.getPos() % Engine.QTD_COLUNAS) * Engine.TILE_SIZE);
            this.y_alvo = ((alvo.getPos() / Engine.QTD_COLUNAS) * Engine.TILE_SIZE);

            if (!this.isNoRange(alvo) || this.alvo.isMorto()) {
                this.alvo = null;
                this.atacando = false;
                x_tiro = this.x;
                y_tiro = this.y;
            }
        }
        if (!atacando) {
            if (troca_animacao == true) {
                this.conta_sprite++;
            }
        }
        if (atacando) {
            if (troca_animacao_ataque == true) {
                this.conta_sprite_ataque++;
            }
            this.conta_sprite_tiro++;

        }
    }

    public void tocaSom(int ref) {
        Sons.get(ref).tocaUmaVez();
    }

    @Override
    public void paintComponent(Graphics g) {
        update();

        if (!atacando) {

            if (conta_sprite == max_sprite) {
                this.conta_sprite = 0;
            }
            g.drawImage(SPRITE.getSubimage(conta_sprite * 40, 0, 40, 40), x, y, null);
            this.troca_animacao = !troca_animacao;

        } else if (atacando) {

            if (conta_sprite_ataque == max_sprite_ataque) {
                this.conta_sprite_ataque = 0;
            }

            if (conta_sprite_tiro == max_sprite_tiro) {
                this.conta_sprite_tiro = 0;
            }

            g.drawImage(SPRITE_ATAQUE.getSubimage(conta_sprite_ataque * 40, 0, 40, 40), x, y, null);
            this.troca_animacao_ataque = !troca_animacao_ataque;

            // Velocidade de ataque da torre
            if (this.contador % 20 == 0) {
                if (this.alvo != null) {
                    alvo.reduzVida(this.getAtaque());
                    this.contador = 0;
                }
            }

            this.contador++;

            if (y_tiro == y_alvo && x_tiro == x_alvo) {
                x_tiro = x;
                y_tiro = y;
            }

            g.drawImage(SPRITE_TIRO.getSubimage(conta_sprite_tiro * 40, 0, 40, 40), x_tiro, y_tiro, null);
            this.x_tiro += x_tiro_por;
            this.y_tiro += y_tiro_por;

        }
    }

}
