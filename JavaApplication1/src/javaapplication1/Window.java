/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import Estrutura.Estrutura;
import Estrutura.Torre_terrestre;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import static javaapplication1.JavaApplication1.geraWave;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import mapa.AEstrela;
import mapa.Mapa;

/**
 *
 * @author mathe
 */
public class Window extends JFrame {
    //private BufferedImage minotaur = null;    
    private Tile_layer MAP;
    private ArrayList<BufferedImage> sprites;
    
    public Window(ArrayList<BufferedImage> sprites, Tile_layer layer) {
        //frame.setResizable(false);
        super("Attack, Lord Willow!");
        this.setBounds(500, 200, 816, 838);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.MAP = layer;
        this.sprites = sprites;
        Terrestre_pesado batata = new Terrestre_pesado();
    }

    public void run() {
        Sprite_sheet sprite_terrestre;
        //sprite_terrestre = new Sprite_sheet("/pics/minitaur.png");
        ArrayList<Integer> caminho = new ArrayList();
        //List<Terrestre_pesado> lista_terrestre_pesado;
        // Instanciar o mapa da fase
        caminho = Mapa_exec(caminho);
        System.out.println("Caminho: " + caminho);

        // 
        Estrutura x = new Torre_terrestre(83);
        x.set_casas_no_alcance();
        x.get_casas_no_alcance();

        Base jogador = new Base();
        int round = 1;

        // Vetor com as quantidades de inimigos
        // posição 0 = inimigos terrestres leves
        // posição 1 = inimigos terrestres pesados
        // posição 2 = inimigos aereos leves
        // posição 3 = inimigos aereos pesados
        int qtds[] = new int[4];
        boolean gameLoop = true;

        while (gameLoop) {
            // Cria uma lista de objetos Inimigo - Terrestre Leve  e assim por diante
            //ArrayList<Terrestre_leve> lista_terrestres_leves = new ArrayList();
            //lista_terrestre_pesado = new ArrayList<Terrestre_pesado>();
            ArrayList<Terrestre_pesado> lista_terrestres_pesado = new ArrayList();
            //ArrayList<Aereo_leve> lista_aereos_leves = new ArrayList();
            //ArrayList<Aereo_pesado> lista_aereos_pesados = new ArrayList();

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
                            Terrestre_pesado inimigo = new Terrestre_pesado();
                            // Adiciona na sua respectiva lista
                            lista_terrestres_pesado.add(inimigo);
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
                    if (lista_terrestres_pesado.isEmpty()) {
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
            repaint();
        }
    }

    private static ArrayList Mapa_exec(ArrayList caminho) {
        Mapa mapa = new Mapa(20, 20);
        for (int i = 0; i < 400; i++) {
            mapa.getMapa().get(i).setBloqueado(true);
        }
        //comeca a liberar o caminho q os inimigos vao percorrer
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
        for (int i = 78; i <= 79; i++) {
            mapa.getMapa().get(i).setBloqueado(false);
        }
        for (int i = 324; i < 340; i += 4) {
            mapa.getMapa().get(i).setBloqueado(false);
        }
        for (int i = 66; i < 76; i += 4) {
            mapa.getMapa().get(i).setBloqueado(false);

        }
        //Comeca a setar os construiveis
        for (int i = 2; i < 360; i += 20) {
            mapa.getMapa().get(i).setBloqueado(false);
            mapa.getMapa().get(i).setConstruivel(true);
        }
        for (int i = 343; i < 358; i++) {
            mapa.getMapa().get(i).setBloqueado(false);
            mapa.getMapa().get(i).setConstruivel(true);
        }
        for (int i = 44; i <= 58; i++) {
            mapa.getMapa().get(i).setBloqueado(false);
            mapa.getMapa().get(i).setConstruivel(true);
        }
        for (int i = 4; i < 324; i += 20) {
            mapa.getMapa().get(i).setBloqueado(false);
            mapa.getMapa().get(i).setConstruivel(true);
        }
        for (int i = 86; i < 344; i += 20) {
            mapa.getMapa().get(i).setBloqueado(false);
            mapa.getMapa().get(i).setConstruivel(true);
        }
        for (int i = 68; i < 324; i += 20) {
            mapa.getMapa().get(i).setBloqueado(false);
            mapa.getMapa().get(i).setConstruivel(true);
        }
        for (int i = 59; i <= 99; i += 40) {
            mapa.getMapa().get(i).setBloqueado(false);
            mapa.getMapa().get(i).setConstruivel(true);
        }
        for (int i = 90; i < 350; i += 20) {
            mapa.getMapa().get(i).setBloqueado(false);
            mapa.getMapa().get(i).setConstruivel(true);
        }
        for (int i = 52; i < 332; i += 20) {
            mapa.getMapa().get(i).setBloqueado(false);
            mapa.getMapa().get(i).setConstruivel(true);
        }
        for (int i = 94; i < 354; i += 20) {
            mapa.getMapa().get(i).setBloqueado(false);
            mapa.getMapa().get(i).setConstruivel(true);
        }
        for (int i = 76; i < 336; i += 20) {
            mapa.getMapa().get(i).setBloqueado(false);
            mapa.getMapa().get(i).setConstruivel(true);
        }
        for (int i = 98; i < 378; i += 20) {
            mapa.getMapa().get(i).setBloqueado(false);
            mapa.getMapa().get(i).setConstruivel(true);
        }

        AEstrela.aEstrela(mapa.getMapa().get(3), mapa.getMapa().get(79), mapa);
        caminho = AEstrela.caminhos;

        return caminho;
    }
    
    @Override
    public void paint(Graphics g) {
        //try {
        this.MAP.paintComponent(g);
        
        for (BufferedImage sprite : sprites) {
            g.drawImage(sprite.getSubimage(0, 0, 40, 40), 125, 100, null);
        }    
            
        //} catch (IOException e) {
          //  System.out.print("Nao consegui ler a imagem!");
        //}
        
       // g.drawImage(minotaur.getSubimage(160/* aqui a gente mexe no sprite, só mexe nesse
            //   pq é um vetor(só uma linha) e não uma matriz(spritesheet), 0, 40, 40), 125, 140, null);
        //g.drawImage(minotaur, 100, 100, this);
    }
}
