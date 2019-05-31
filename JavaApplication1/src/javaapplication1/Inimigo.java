/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.*;
/**
 *
 * @author joao.pbsilva20
 */
public abstract class Inimigo {

    private int vida;
    private int ataque;
    private int defesa;
    private int vel_mov;
    private int gold;
    private int xp;
    private int pos; // Posição do mapa em que o inimigo está
    private List caminho = null;
    private int qtdPassos = 1; // Quantos passos ele já deu
    private String tipo;
    private BufferedImage sprite;
    public static BufferedImage terrestre_pesado;

    public Inimigo(int vida, int ataque, int defesa, int vel_mov, int gold, int xp, String tipo, BufferedImage sprite) {
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.vel_mov = vel_mov;
        this.gold = gold;
        this.xp = xp;
        this.pos = 0;
        this.tipo = tipo;
        this.sprite = sprite;
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

    // Retorna o valor da velocidade de movimento do inimigo
    public int getVel_mov() {
        return this.vel_mov;
    }

    // Reduz a velocidade de movimento do inimigo
    public void reduzVel_mov(int valor) {
        this.vel_mov -= valor;
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
        return this.vida == 0;
    }
    
    // Mata o inimigo, reduzindo sua vida a 0
    public void matar() {
        this.vida = 0;
    }
    
    public void setCaminho (List caminho){
        this.caminho = caminho;
    }
    // Faz o inimigo andar caso haja um caminho, ele irá para a posição dada na
    // string de caminhos baseado em quantos passos ele já deu, começa em 1 pois
    // 0, o primeiro char da string de caminho, sempre vai ser 0, o ponto inicial,
    // o qual o inimigo já está.
    public boolean andar() {
        if (this.caminho != null) {
            this.pos = (int) this.caminho.get(this.qtdPassos);
            this.qtdPassos ++;
            return true;
        } else
            return false;
    }
    
    // Retorna em qual posição do mapa o inimigo está
    public int getPos(){
        return this.pos;   
    }
    
        public void render(Graphics g) {
            g.drawImage(sprite, 150, 150, null);
    }

}
