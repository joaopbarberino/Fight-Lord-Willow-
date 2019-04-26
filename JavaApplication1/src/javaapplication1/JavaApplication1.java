/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.util.*;
import Estrutura.*;
/**
 *
 * @author joao.pbsilva20
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Armadilha_lava a = new Armadilha_lava();
        System.out.println(a.getAtaque());
        a.teste();
        
        
        
//        Base base = new Base();
//        int vetor[] = new int[3];
//
//        int round = 1;
//        int qtd_inimigos_terrestres_leves = 0;
//        int qtd_inimigos_terrestres_pesados = 0;
//        int qtd_inimigos_aereos_leves = 0;
//        int qtd_inimigos_aereos_pesados = 0;
//
//        // Vetor com as quantidades de inimigos
//        // posição 0 = inimigos terrestres leves
//        // posição 1 = inimigos terrestres pesados
//        // posição 2 = inimigos aereos leves
//        // posição 3 = inimigos aereos pesados
//        int qtds[] = new int[4];
//        List lista_terrestres_leves = new ArrayList();
//        while (true) {
//            geraWave(round, qtd_inimigos_terrestres_leves, qtd_inimigos_terrestres_pesados,
//                    qtd_inimigos_aereos_leves, qtd_inimigos_aereos_pesados, qtds);
//
//            for (int i = 0; i < qtds[0]; i++) {
//                Base terrestre = new Base();
//            }
//            switch (round) {
//                case 1:
//                    //contar qts inimigos tem na wave
//                    for (int i = 0; i < qtds.length; i ++){
//                        for (int j = 0; i < qtds[i] && i == 0; j ++){
//                            Base bicho = new Base();
//                            lista_terrestres_leves.add(bicho);
//                        }            
//                    }
//                    //se inimigos = 0
//                    //round++
//                    break;
//                case 2:
//                    break;
//                case 3:
//                    break;
//                case 4:
//                    break;
//                case 5:
//                    break;
//            }
//        }
    }

    public static int[] geraWave(int round, int qtd_inimigos_terrestres_leves,
            int qtd_inimigos_terrestres_pesados, int qtd_inimigos_aereos_leves,
            int qtd_inimigos_aereos_pesados, int qtds[]) {

        switch (round) {
            case 1:
                qtd_inimigos_terrestres_leves = 10;
                qtds[0] = qtd_inimigos_terrestres_leves;
                break;
        }

        return qtds;

    }
}
