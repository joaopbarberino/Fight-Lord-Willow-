/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estrutura;

import java.util.*;

/**
 *
 * @author joao.pbsilva20
 */
public class Estrutura {

    private int ataque;
    private int alcance;
    private int velo_atk;
    private int round;
//    private ArrayList<No> alcance_de_ataque = new ArrayList();
//    private No pos;
//    private Mapa mapa;

    public Estrutura(int ataque, int alcance, int velo_atk, int round) {
        this.ataque = ataque;
        this.alcance = alcance;
        this.velo_atk = velo_atk;
        this.round = round;
       // this.mapa = mapa;
    }
    
    public void print(){
        System.out.println(this.round);
    }

//    public void construir(No pos) {
//        // desenha na tela
//        setRange();
//    }
//
//    private void setRange(Mapa mapa) {
//        int x;
//        this.alcance_de_ataque.add(mapa.getMapa.get(x));
//    }

    public int getAlcance() {
        return alcance;
    }

    public void setAlcance(int alcance) {
        this.alcance = alcance;
    }

    public int getVelo_atk() {
        return velo_atk;
    }

    public void setVelo_atk(int velo_atk) {
        this.velo_atk = velo_atk;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public void atacar(Object inimigo) {

    }

}
