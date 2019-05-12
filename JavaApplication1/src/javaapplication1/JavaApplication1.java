/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.*;
import Estrutura.*;
import javax.swing.JFrame;
import mapa.*;

/**
 *
 * @author joao.pbsilva20
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Integer> caminho = new ArrayList();
        /*Tela jogo = new Tela();
         jogo.Initialize();

         Base jogador = new Base();*/
        int round = 1;

        // Vetor com as quantidades de inimigos
        // posição 0 = inimigos terrestres leves
        // posição 1 = inimigos terrestres pesados
        // posição 2 = inimigos aereos leves
        // posição 3 = inimigos aereos pesados
        int qtds[] = new int[4];
        boolean gameLoop = true;
        // Instanciar o mapa da fase
        Mapa mapa = new Mapa(20, 20);
        Mapa_exec(mapa, caminho);

        Estrutura teste = new Torre_terrestre(20);

        while (gameLoop) {
            // Cria uma lista de objetos Inimigo - Terrestre Leve  e assim por diante
            ArrayList<Terrestre_leve> lista_terrestres_leves = new ArrayList();
            ArrayList<Terrestre_pesado> lista_terrestres_pesados = new ArrayList();
            ArrayList<Aereo_leve> lista_aereos_leves = new ArrayList();
            ArrayList<Aereo_pesado> lista_aereos_pesados = new ArrayList();

            // Colocar timing para o jogador pensar (em segundos)
            // Checa se o jogador nao vai construir ou melhorar alguma torre
            // Caso construa, adiciona a estrutura numa lista de estruturas
            // Gera wave baseado em valores pré determinados com base no round
            geraWave(round, qtds);

            // Conta qts inimigos tem de cada tipo
            // Instancia esses inimigos
            // Da o caminho à eles
            // Adiciona na sua respectiva lista
            for (int i = 0; i < qtds[0]; i++) {
                Terrestre_leve inimigo = new Terrestre_leve();
                inimigo.setCaminho(caminho);
                lista_terrestres_leves.add(inimigo);
                //System.out.println(lista_terrestres_leves.get(i).getVida());
            }
            for (int i = 0; i < qtds[1]; i++) {
                Terrestre_pesado inimigo = new Terrestre_pesado();
                inimigo.setCaminho(caminho);
                lista_terrestres_pesados.add(inimigo);
            }
            for (int i = 0; i < qtds[2]; i++) {
                Aereo_leve inimigo = new Aereo_leve();
                inimigo.setCaminho(caminho);
                lista_aereos_leves.add(inimigo);
            }
            for (int i = 0; i < qtds[3]; i++) {
                Aereo_pesado inimigo = new Aereo_pesado();
                inimigo.setCaminho(caminho);
                lista_aereos_pesados.add(inimigo);
            }
                    
            // UPDATE: *Não precisava daquele switch case aqui pois a logica de cada
            // round é a mesma, só muda a quantidade de inimigos, coisa que
            // a funcao geraWave já controla.* //////

            /*
                     
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

             -> Se não estiver
             -> Inimigo anda
             -> Se chegou no destino, se mata e da dano no jogador
             -> Se vida atual do jogador <= 0, gameLoop = false, break;
                                    
                    
             FAZER TRATAMENTO DE FPS
             */
            // Caso todos os inimigos estejam mortos, round acaba
            if (lista_terrestres_leves.isEmpty()
                    && lista_terrestres_pesados.isEmpty()
                    && lista_aereos_leves.isEmpty()
                    && lista_aereos_pesados.isEmpty()) {

                round++;
            }

            gameLoop = false;

            // Checa se o jogador tem xp o suficente para subir de nivel
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
                qtds[1] = qtd_inimigos_terrestres_pesados;
                qtds[2] = qtd_inimigos_aereos_leves;
                qtds[3] = qtd_inimigos_aereos_pesados;
                break;
        }

        return qtds;

    }

    // public static void gameState (listas, jogador)
    private static void Mapa_exec(Mapa mapa, List caminho) {

        for (int i = 0; i < 400; i++) {
            mapa.getMapa().get(i).setBloqueado(true);
        }
        for (int i = 0; i < 400; i += 2) {
            mapa.getMapa().get(i).setBloqueado(false);
        }
        /*for (int i = 361; i <= 400; i += 4) {
         mapa.getMapa().get(i).setBloqueado(false);
         }*/
        /*for (int i = 23; i <= 39; i += 4) {
         mapa.getMapa().get(i).setBloqueado(false);
         }*/

        AEstrela.aEstrela(mapa.getMapa().get(0), mapa.getMapa().get(360), mapa, caminho);
        System.out.println(caminho);
        //AEstrela.desenha(mapa);
        //BuscaEmLargura.bucaEmLargura(mapa.getMapa().get(0), mapa.getMapa().get(302), mapa);

    }

}
