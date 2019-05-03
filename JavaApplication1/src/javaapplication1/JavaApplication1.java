/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.*;
import Estrutura.*;
import javax.swing.JFrame;

/**
 *
 * @author joao.pbsilva20
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        config_Tela();
        Armadilha_lava a = new Armadilha_lava();
        System.out.println(a.getAtaque());
        a.teste();

        Base jogador = new Base();

        int round = 1;

        // Vetor com as quantidades de inimigos
        // posição 0 = inimigos terrestres leves
        // posição 1 = inimigos terrestres pesados
        // posição 2 = inimigos aereos leves
        // posição 3 = inimigos aereos pesados
        int qtds[] = new int[4];
        boolean gameLoop = true;
        // Instanciar o mapa da fase

        while (gameLoop) {
            // Cria uma lista de objetos Inimigo - Terrestre Leve  e assim por diante
            ArrayList<Terrestre_leve> lista_terrestres_leves = new ArrayList();

            //ArrayList<Terrestre_pesado> lista_terrestres_pesado = new ArrayList();
            //ArrayList<Aereo_leve> lista_aereo_leves = new ArrayList();
            //ArrayList<Aereo_pesado> lista_terrestres_leves = new ArrayList();
            

            // Colocar timing para o jogador pensar (em segundos)
            // Checa se o jogador nao vai construir ou melhorar alguma torre
                // Caso construa, adiciona a estrutura numa lista de estruturas

            // Gera wave baseado em valores pré determinados com base no round
            geraWave(round, qtds);

            switch (round) {
                case 1:
                    // Conta qts inimigos tem de cada tipo
                    for (int j = 0; j < qtds.length; j++) {
                        for (int i = 0; i < qtds[j]; i++) {
                            // Instancia esses inimigos
                            Terrestre_leve inimigo = new Terrestre_leve();
                            // Adiciona na sua respectiva lista
                            lista_terrestres_leves.add(inimigo);
                            System.out.println(lista_terrestres_leves.get(i).getVida());
                        }
                    }
                    /*
                     Acha caminho e da pro inimigo seguir
                     
                     Para cada inimigo da lista diferente de null
                    
                     Chama função abaixo:
                        Checa se ele não está no range de todas as torres
                           -> Se tiver, a torre ataca enquanto o inimigo estiver no range {
                               -> Inimigo recebe o ataque
                                   -> Se vida do inimigo => 0, ele morre e é removido da lista
                                   -> Se não, inimigo anda

                                   -> Se chegou no destino, se mata e da dano no jogador
                                       -> Se vida atual do jogador <= 0, gameLoop = false, break;

                               -> Se o inimigo morrer, jogador recebe o gold e o xp q ele vale
                           }

                           -> Se não estiver
                               -> Inimigo anda
                                   -> Se chegou no destino, se mata e da dano no jogador
                                       -> Se vida atual do jogador <= 0, gameLoop = false, break;
                                    
                    
                     FAZER TRATAMENTO DE FPS
                     */

                    // Caso todos os inimigos estejam mortos, round acaba
                    if (lista_terrestres_leves.isEmpty()) {
                        round++;
                    }

                    gameLoop = false;

                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    break;

                // Checa se o jogador tem xp o suficente para subir de nivel
            }
        }
    }

    public static int[] geraWave(int round, int qtds[]) {
        int qtd_inimigos_terrestres_leves = 0;
        int qtd_inimigos_terrestres_pesados = 0;
        int qtd_inimigos_aereos_leves = 0;
        int qtd_inimigos_aereos_pesados = 0;

        switch (round) {
            case 1:
                qtd_inimigos_terrestres_leves = 10;
                qtds[0] = qtd_inimigos_terrestres_leves;

                break;
        }

        return qtds;

    }
    
    // public static void gameState (listas, jogador)

    private static void config_Tela() {
        JFrame tela = new JFrame("Fight, Lord Willow!");
        tela.setSize(1280, 900);
        tela.setLocationRelativeTo(null);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setVisible(true);
    }
}
