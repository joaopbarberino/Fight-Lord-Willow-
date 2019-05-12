/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estrutura;

import javaapplication1.Inimigo;
import java.util.*;
import mapa.*;

/**
 *
 * @author joao.pbsilva20
 */
public class Estrutura {

    private int ataque;
    private int alcance;
    private int velo_atk;
    private int pos;
    private int nivel = 1;
    private ArrayList<Integer> casas_no_alcance = new ArrayList();
    private Mapa mapa;

    public Estrutura(int ataque, int alcance, int velo_atk, int pos) {
        this.ataque = ataque;
        this.alcance = alcance;
        this.velo_atk = velo_atk;
        this.pos = pos;
    }

//  
//    public void construir(int pos) {
//        // desenha na tela
//        // poe no objeto mapa pelo main
//    }
//      
    // Retorna o alcance de ataque da estrutura
    public int getAlcance() {
        return alcance;
    }

    // Modifica o alcance da estrutura
    public void setAlcance(int alcance) {
        this.alcance = alcance;
        //this.setRange();
    }

    // Retorna a velocidade de ataque da estrutura
    public int getVelo_atk() {
        return velo_atk;
    }

    // Modifica a velocidade de ataque da estrutura
    public void setVelo_atk(int velo_atk) {
        this.velo_atk = velo_atk;
    }

    // Retorna o valor de ataque da estrutura
    public int getAtaque() {
        return ataque;
    }

    // Modifica o valor de ataque da estrutura
    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    // Ataca um dado inimigo, reduzindo sua vida
    public void atacar(Inimigo inimigo) {
        inimigo.reduzVida(this.ataque);
    }

    // Verifica se um dado inimigo está no alcance de ataque, retorna true se sim
    // e else se não
    public boolean isNoRange(Inimigo inimigo) {
        if (this.casas_no_alcance.contains(inimigo.getPos())) {
            return true;
        }
        return false;
    }

    // Da um mapa pra estrutura e calcula as casas que ela pode atacar
    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
        //this.setRange();
    }

    // Aumenta o nivel da torre
    public void update() {
        this.nivel++;
        this.ataque++;
        this.alcance++;
        //this.setRange();    
    }

//    responsável por calcular quais posições do mapa estão no alcance da torre
//    private void setRange() {
//        int x;
//        this.casas_no_alcance.add(this.mapa.getMapa.get(x));
//    }
//   
}
