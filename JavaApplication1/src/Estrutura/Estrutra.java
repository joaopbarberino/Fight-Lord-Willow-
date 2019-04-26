/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estrutura;

/**
 *
 * @author joao.pbsilva20
 */
public abstract class Estrutra {
    private int ataque;

    public Estrutra(int ataque) {
        this.ataque = ataque;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }
    
}
