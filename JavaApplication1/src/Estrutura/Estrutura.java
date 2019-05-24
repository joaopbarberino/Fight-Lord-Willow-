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
        this.setRange();
    }

    // Aumenta o nivel da torre
    public void update() {
        this.nivel++;
        this.ataque++;
        this.alcance++;
        //this.setRange();    
    }

//    responsável por calcular quais posições do mapa estão no alcance da torre
    private void setRange() {
        int x1, x2;
        int y1, y2;
        int diagonal_sup_esq, diagonal_inf_esq;
        int diagonal_sup_dir, diagonal_inf_dir;
        int qtdColunas = 20; // tamanho da linha em caso de lista
        int range = this.alcance;

        // for, range --
        for (int j = range; j < 0; j--) {
            x1 = this.pos - this.alcance;
            casas_no_alcance.add(x1);
            x2 = this.pos + this.alcance;
            casas_no_alcance.add(x2);
            y1 = this.pos - (qtdColunas * range);
            casas_no_alcance.add(y1);
            y2 = this.pos + (qtdColunas * range);
            casas_no_alcance.add(y2);
            diagonal_sup_esq = y1 - range;
            casas_no_alcance.add(diagonal_sup_esq);
            diagonal_sup_dir = y1 + range;
            casas_no_alcance.add(diagonal_sup_dir);
            diagonal_inf_esq = y2 - range;
            casas_no_alcance.add(diagonal_inf_esq);
            diagonal_inf_dir = y2 + range;
            casas_no_alcance.add(diagonal_inf_dir);
            ArrayList<Integer> telhado = new ArrayList();
            ArrayList<Integer> chao = new ArrayList();
            ArrayList<Integer> parede_esq = new ArrayList();
            ArrayList<Integer> parede_dir = new ArrayList();
            // telhado = diagonal sup esq até diagonal sup dir (casa por casa)
            for (int i = diagonal_sup_esq; i < diagonal_sup_dir; i++) {
                telhado.add(i);
            }

            // chão = diagonal inf esq até diagonal inf dir (casa por casa)		
            for (int i = diagonal_inf_esq; i < diagonal_inf_dir; i++) {
                chao.add(i);
            }

            // parede esq = diagonal sup esq até diagonal inf esq (de 20 em 20 [linha])
            for (int i = diagonal_sup_esq; i < diagonal_inf_esq; i += qtdColunas) {
                parede_esq.add(i);
            }

            // parede dir = diagonal sup dir até diagonal inf dir (de 20 em 20 [linha])				
            for (int i = diagonal_sup_dir; i < diagonal_inf_dir; i += qtdColunas) {
                parede_dir.add(i);
            }
        }
        // TODO adicionar telhado chao e paredes a lista principal

    }

}
