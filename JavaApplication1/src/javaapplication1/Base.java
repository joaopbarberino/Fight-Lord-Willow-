package javaapplication1;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Base implements Desenhavel {
    private final BufferedImage SPRITE;
    /*
        Essa classe é responsável pela base do jogador
    
     */
    private int vida_maxima;
    private int vida_atual;
    private int xp;
    private int gold;
    private int nivel;
    private int x,y;
    private boolean morreu;

    public Base(int x, int y,int vida,int xp,int gold,int nivel,BufferedImage sprite) {
        this.vida_maxima = vida;
        this.vida_atual = vida;
        this.xp = xp;
        this.gold = gold;
        this.nivel = nivel;
        this.SPRITE = sprite;
        this.x = x;
        this.y = y;
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

    public boolean isMorreu() {
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
    
    public void recebeDano(int dano) {
        this.vida_atual = this.vida_atual - dano;
    }

    public void upgrade() {
        switch (this.xp) {
            case 50:
                this.vida_maxima += 20;
                this.vida_atual = (this.vida_maxima / 3);
                this.nivel ++;
                break;
            
            case 110:
                this.vida_maxima += 40;
                this.vida_atual = (this.vida_maxima / 3);
                this.nivel ++;
                break;
            
            case 160:
                this.vida_maxima += 60;
                this.vida_atual = (this.vida_maxima / 3);
                this.nivel ++;
                break;
            
            default:
                break;               
        }
    }

    @Override
    public void paintComponent(Graphics g) {

        g.drawImage(this.SPRITE, x,y,null);

    }
}
