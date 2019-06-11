package javaapplication1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Base implements Desenhavel {

    private BufferedImage SPRITE;
    public ArrayList<BufferedImage> sprites;
    private final ArrayList<Som> Sons;
    /*
     Essa classe é responsável pela base do jogador
    
     */
    private int vida_maxima;
    private int vida_atual;
    private int xp;
    private int gold;
    private int nivel;
    private int x, y;
    private boolean morreu;

    public Base(int x, int y, int vida, int xp, int gold, int nivel, BufferedImage sprite, ArrayList<BufferedImage> sprites,ArrayList<Som> sound) {
        this.vida_maxima = vida;
        this.vida_atual = vida;
        this.xp = xp;
        this.gold = gold;
        this.nivel = nivel;
        this.SPRITE = sprite;
        this.x = x;
        this.y = y;
        this.sprites = sprites;
        this.Sons = sound;
    }

    public int getVidaMaxima() {
        return vida_maxima;
    }

    private void setVidaMaxima(int vida_maxima) {
        this.vida_maxima = vida_maxima;
    }

    public int getVidaAtual() {
        return vida_atual;
    }

    public void setVidaAtual(int vida_atual) {
        this.vida_atual = vida_atual;
    }

    public void perdeVida(int valor) {
        this.vida_atual -= valor;
        tocaSom(0);
    }

    public int getXp() {
        return xp;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isMorto() {
        return morreu;
    }

    public void setMorreu(boolean morreu) {
        this.morreu = morreu;
    }

    public void ganhaXp(int xp) {
        this.xp += xp;
    }

    public void ganhaGold(int valor) {
        this.gold += valor;
    }

    public int getGold() {
        return this.gold;
    }

    public void recebeDano(int dano) {
        this.vida_atual = this.vida_atual - dano;
    }

    public void reduzGold(int valor) {
        this.gold -= valor;
    }

    public void upgrade() {
        if (this.xp >= 500 && this.nivel == 0) {
            this.vida_maxima += 10;
            this.vida_atual += 10;
            if (vida_atual > vida_maxima) {
                this.vida_atual = vida_maxima;
            }
            this.nivel++;
            this.SPRITE = this.sprites.get(11);
        }
        if (this.xp >= 1000 && this.nivel == 1) {
            this.vida_maxima += 30;
            this.vida_atual += 30;
            if (vida_atual > vida_maxima) {
                this.vida_atual = vida_maxima;
            }
            this.nivel++;
            this.SPRITE = this.sprites.get(12);
        }
    }

    public void tocaSom(int ref) {
        Sons.get(ref).tocaUmaVez();
    }

    @Override
    public void paintComponent(Graphics g) {

        g.setFont(new Font("Serif", Font.BOLD, 12));
        g.drawImage(this.SPRITE, x, y, null);
        g.drawString("" + this.gold, 295, 748);
        g.drawString("" + this.xp, 380, 780);
        g.drawString("" + this.vida_atual, 295, 780);

    }
}
