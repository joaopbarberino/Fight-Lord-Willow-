/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.*;
import Estrutura.*;
import java.awt.Dimension;
import java.awt.Point;
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
        tela_init();
        Mapa_exec(caminho);
                 
        // 
        Estrutura x = new Torre_terrestre(265);
        x.set_casas_no_alcance();
        x.get_casas_no_alcance();
        
        
        /*Tela jogo = new Tela();
        jogo.Initialize();
        Armadilha_lava a = new Armadilha_lava();
        System.out.println(a.getAtaque());
        a.teste();

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

        while (gameLoop) {
            // Cria uma lista de objetos Inimigo - Terrestre Leve  e assim por diante
            ArrayList<Terrestre_leve> lista_terrestres_leves = new ArrayList();
            ArrayList<Terrestre_pesado> lista_terrestres_pesado = new ArrayList();
            ArrayList<Aereo_leve> lista_aereos_leves = new ArrayList();
            ArrayList<Aereo_pesado> lista_aereos_pesados = new ArrayList();
   

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
                            //System.out.println(lista_terrestres_leves.get(i).getVida());
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

    private static void Mapa_exec(List caminho) {
        Mapa mapa = new Mapa(20,20);
        for(int i = 0;i<400;i++){
            mapa.getMapa().get(i).setBloqueado(true);
        }
        for (int i = 3; i < 343; i += 20) {
            mapa.getMapa().get(i).setBloqueado(false);
        }
        for (int i = 65; i <= 325; i += 20) {
            mapa.getMapa().get(i).setBloqueado(false);
        }
        for (int i = 67; i <= 327; i += 20) {
            mapa.getMapa().get(i).setBloqueado(false);
        }
        for (int i = 69; i <= 345; i += 20) {
            mapa.getMapa().get(i).setBloqueado(false);
        }
        for (int i = 71; i <= 345; i += 20) {
            mapa.getMapa().get(i).setBloqueado(false);
        }
        for (int i = 73; i <= 345; i += 20) {
            mapa.getMapa().get(i).setBloqueado(false);
        }
        for (int i = 75; i <= 345; i += 20) {
            mapa.getMapa().get(i).setBloqueado(false);
        }
        for (int i = 77; i <= 345; i += 20) {
            mapa.getMapa().get(i).setBloqueado(false);
        }
        for (int i = 78; i <= 79; i ++) {
            mapa.getMapa().get(i).setBloqueado(false);
        }
        for (int i = 324; i < 340; i += 4) {
            mapa.getMapa().get(i).setBloqueado(false);
        }
        for (int i = 66; i < 76; i += 4) {
            mapa.getMapa().get(i).setBloqueado(false);
            
        }
        for (int i = 42; i < 360; i += 20) {
            mapa.getMapa().get(i).setBloqueado(false);
            mapa.getMapa().get(i).setConstruivel(true);
        }
        for (int i = 343; i < 358; i ++) {
            mapa.getMapa().get(i).setBloqueado(false);
            mapa.getMapa().get(i).setConstruivel(true);
        }
        for (int i = 44; i < 58; i ++) {
            mapa.getMapa().get(i).setBloqueado(false);
            mapa.getMapa().get(i).setConstruivel(true);
        }
        for (int i = 64; i < 324; i+=20) {
            mapa.getMapa().get(i).setBloqueado(false);
            mapa.getMapa().get(i).setConstruivel(true);
        }
        for (int i = 86; i < 344; i+=20) {
            mapa.getMapa().get(i).setBloqueado(false);
            mapa.getMapa().get(i).setConstruivel(true);
        }
        for (int i = 68; i < 324; i+=20) {
            mapa.getMapa().get(i).setBloqueado(false);
            mapa.getMapa().get(i).setConstruivel(true);
        }
        

        AEstrela.aEstrela(mapa.getMapa().get(0), mapa.getMapa().get(79), mapa,caminho);
        System.out.println(caminho);
        AEstrela.desenha(mapa);
        //BuscaEmLargura.bucaEmLargura(mapa.getMapa().get(0), mapa.getMapa().get(302), mapa);

    }

    private static void tela_init() {      
        JFrame frame = new JFrame("Attack, Lord Willow!");
        DrawPanel panel = new DrawPanel();
        frame.setSize(new Dimension(816, 838));
        frame.setLocation(new Point(500,100));
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


}
