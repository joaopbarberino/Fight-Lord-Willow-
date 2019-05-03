/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

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
    private String tipo;

    public Inimigo(int vida, int ataque, int defesa, int vel_mov, int gold, int xp, String tipo) {
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.vel_mov = vel_mov;
        this.gold = gold;
        this.xp = xp;
        this.tipo = tipo;
    }

    public int getVida() {
        return vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public int getVel_mov() {
        return vel_mov;
    }

    public int getGold() {
        return gold;
    }

    public int getXp() {
        return xp;
    }

    public String getTipo() {
        return tipo;
    }
    
    public void reduzVida(int valor){
        this.vida -= valor;
    }
    
    public void reduzVel_mov(int valor){
        this.vel_mov -= valor;
    }
    
    
}
