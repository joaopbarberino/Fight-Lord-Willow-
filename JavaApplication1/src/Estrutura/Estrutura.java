/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estrutura;

import java.io.IOException;
import javaapplication1.Inimigo;
import java.util.*;
import javax.swing.JPanel;
import javaapplication1.Window;
import mapa.*;

/**
 *
 * @author joao.pbsilva20
 */
public class Estrutura extends JPanel {

    private int ataque;
    public int maximo_de_alvos = 1;
    private ArrayList<Inimigo> alvos = new ArrayList();
    private int alcance;
    private int velo_atk;
    private int preco;
    private int pos;
    private int nivel = 1;
    private ArrayList<Integer> casas_no_alcance = new ArrayList();
    private Mapa mapa;

    public Estrutura(int ataque, int alcance, int preco, int pos) {
        this.ataque = ataque;
        this.alcance = alcance;
        this.preco = preco;
        this.pos = pos;
    }

    // Retorna o alcance de ataque da estrutura
    public int getAlcance() {
        return alcance;
    }

    public int getPos() {
        return this.pos;
    }
    
    // Modifica o alcance da estrutura
    public void setAlcance(int alcance) {
        this.alcance = alcance;
        this.set_casas_no_alcance();
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
    
    // Verifica se um dado inimigo está no alcance de ataque, retorna true se sim
    // e else se não
    public boolean isNoRange(Inimigo inimigo) {
        return casas_no_alcance.contains(inimigo.getPos());
    }

    public int getPreco(){
        return this.preco;
    }
    
    // Da um mapa pra estrutura e calcula as casas que ela pode atacar
    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    // Aumenta o nivel da torre
    public void upgrade() {
        this.nivel++;
        this.ataque++;
        this.alcance++;
        this.set_casas_no_alcance();
    }

    // mostra as casas no alcance da torre
    public void get_casas_no_alcance() {
        System.out.println(this.casas_no_alcance);
    }

    // responsável por calcular quais posições do mapa estão no alcance da torre
    public void set_casas_no_alcance() {
        int x1, x2;
        int y1, y2;
        int diagonal_sup_esq, diagonal_inf_esq;
        int diagonal_sup_dir, diagonal_inf_dir;
        int qtdColunas = 20; // tamanho da linha em caso de lista

        // for, alcance --
        for (int j = this.alcance; j > 0; j--) {
            x1 = this.pos - j;
            x2 = this.pos + j;
            y1 = this.pos - (qtdColunas * j);
            y2 = this.pos + (qtdColunas * j);
            diagonal_sup_esq = y1 - j;
            diagonal_sup_dir = y1 + j;
            diagonal_inf_esq = y2 - j;
            diagonal_inf_dir = y2 + j;

            ArrayList<Integer> telhado = new ArrayList();
            ArrayList<Integer> chao = new ArrayList();
            ArrayList<Integer> parede_esq = new ArrayList();
            ArrayList<Integer> parede_dir = new ArrayList();

            // telhado = diagonal sup esq até diagonal sup dir (casa por casa)
            for (int i = diagonal_sup_esq; i <= diagonal_sup_dir; i++) {
                telhado.add(i);
            }

            // chão = diagonal inf esq até diagonal inf dir (casa por casa)		
            for (int i = diagonal_inf_esq; i <= diagonal_inf_dir; i++) {
                chao.add(i);
            }

            // parede esq = diagonal sup esq até diagonal inf esq (de 20 em 20 [linha])
            for (int i = diagonal_sup_esq; i <= diagonal_inf_esq; i += qtdColunas) {
                parede_esq.add(i);
            }

            // parede dir = diagonal sup dir até diagonal inf dir (de 20 em 20 [linha])				
            for (int i = diagonal_sup_dir; i <= diagonal_inf_dir; i += qtdColunas) {
                parede_dir.add(i);
            }

            // adicionar telhado chao e paredes a lista principal
            telhado.forEach((casa) -> {
                if (!this.casas_no_alcance.contains(casa)) {
                    this.casas_no_alcance.add(casa);
                }
            });
            telhado.clear();
            chao.forEach((casa) -> {
                if (!this.casas_no_alcance.contains(casa)) {
                    this.casas_no_alcance.add(casa);
                }
            });
            chao.clear();
            parede_dir.forEach((casa) -> {
                if (!this.casas_no_alcance.contains(casa)) {
                    this.casas_no_alcance.add(casa);
                }
            });
            parede_dir.clear();
            parede_esq.forEach((casa) -> {
                if (!this.casas_no_alcance.contains(casa)) {
                    this.casas_no_alcance.add(casa);
                }
            });
            parede_esq.clear();

        }

    }

}
