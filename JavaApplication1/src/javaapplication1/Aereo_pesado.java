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
public class Aereo_pesado extends Inimigo{

    public Aereo_pesado() {
        super(10, 2, 5, 1, 10, 20, 0, "aereo");
    }

    public void reduzVida(int valor) {
        super.reduzVida(valor);
    }

    public boolean morte() {
        return super.getVida() == 0;
    }
}
