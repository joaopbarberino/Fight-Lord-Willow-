//***********************************************************/
//********************Sem Referencias************************/
//***********************************************************/
package javaapplication1;

import java.awt.image.BufferedImage;
import java.util.*;
import javax.swing.JPanel;
/**
 *
 * @author joao.pbsilva20
 */
public abstract class Inimigo extends JPanel {

    private int vida;
    private int ataque;
    private int defesa;
    private int gold;
    private int xp;
    private int pos; // Posição do mapa em que o inimigo está
    private ArrayList<Integer> caminho = null;
    private int qtd_passos = 0; // Quantos passos ele já deu
    private boolean andando = true;
    private boolean chegou_no_destino = false;
    private String tipo;
    private final int SOM;

    public static BufferedImage terrestre_pesado;

    public Inimigo(int vida, int ataque, int defesa, int gold, int xp, String tipo, ArrayList caminho, int som) {
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.gold = gold;
        this.xp = xp;
        this.tipo = tipo;
        this.setCaminho(caminho);
        this.SOM = som;
    }

    // Retorna a vida atual do inimigo
    public int getVida() {
        return this.vida;
    }

    // Reduz a vida do inimigo
    public void reduzVida(int valor) {
        this.vida -= valor;
    }

    // Retorna o valor do ataque do inimigo
    public int getAtaque() {
        return this.ataque;
    }

    // Retorna o valor da defesa do inimigo
    public int getDefesa() {
        return this.defesa;
    }

    // Retorna quanto de ouro o inimigo vale
    public int getGold() {
        return this.gold;
    }

    // Retorna quanto de xp o inimigo vale
    public int getXp() {
        return this.xp;
    }

    // Retorna o tipo do inimigo
    public String getTipo() {
        return this.tipo;
    }

    // Retorna true se o inimigo está morto e false se não
    public boolean isMorto() {
        return this.vida <= 0;
    }

    // Mata o inimigo, reduzindo sua vida a 0
    private void matar() {
        this.vida = 0;
    }

    public void setCaminho(ArrayList caminho) {
        this.caminho = caminho;
        this.pos = this.caminho.get(this.qtd_passos);
    }

    public ArrayList getCaminho() {
        return this.caminho;
    }
    // Faz o inimigo andar caso haja um caminho, ele irá para a posição dada na
    // string de caminhos baseado em quantos passos ele já deu, começa em 1 pois
    // 0, o primeiro char da string de caminho, sempre vai ser 0, o ponto inicial,
    // o qual o inimigo já está.
    public void andar(boolean pode_andar) {
        if (pode_andar) {

            if (this.qtd_passos != this.caminho.size() - 1) {
                this.pos = (int) this.caminho.get(this.qtd_passos);
                this.qtd_passos++;
                this.andando = true;

            } else if (this.qtd_passos == this.caminho.size() - 1) {
                this.matar();
                this.andando = false;
                this.chegou_no_destino = true;
            }
        }
    }
    
    public boolean chegouNoDestino(){
        return this.chegou_no_destino;
    }

    public boolean isAndando() {
        return this.andando;
    }

    // Retorna em qual posição do mapa o inimigo está
    public int getPos() {
        return this.pos;
    }

    public int getProxPos() {
        return this.caminho.get(this.qtd_passos);
    }
    
    public int getSom(){
        return this.SOM;
    }

}
