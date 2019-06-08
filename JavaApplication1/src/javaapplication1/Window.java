/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import Estrutura.Estrutura;
import Estrutura.Torre_terrestre;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import static javaapplication1.JavaApplication1.geraWave;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import mapa.AEstrela;
import mapa.Mapa;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.Iterator;

/**
 *
 * @author mathe
 */
public class Window extends JFrame implements KeyListener {

    //private BufferedImage minotaur = null;    
    private Tile_layer MAP;
    private ArrayList<BufferedImage> sprites;
    private ArrayList<Desenhavel> desenhaveis = new ArrayList();
    private ArrayList<Terrestre_pesado> lista_terrestres_pesado = new ArrayList();
    private ArrayList<Terrestre_pesado> lista_terrestres_pesado_clone = new ArrayList();
    private ArrayList<Terrestre_leve> lista_Terrestre_leves = new ArrayList();
    private ArrayList<Aereo_pesado> lista_aereo_pesado = new ArrayList();
    private ArrayList<Aereo_leve> lista_aereo_leve = new ArrayList();

    public Window(ArrayList<BufferedImage> sprites, Tile_layer layer) {
        super("Attack, Lord Willow!");
        this.setResizable(false);
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addKeyListener(this);
        this.MAP = layer;
        this.sprites = sprites;
    }

    public void run() throws InterruptedException {
        Sprite_sheet sprite_terrestre;
        boolean gameLoop = true;

        long excess = 0;
        long noDelays = 0;
        long a = 0;

        final long DESIRED_UPDATE_TIME = 20;
        final long NO_DELAYS_PER_YIELD = 4;
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

        // Cria double-buffering strategy genérico
        this.createBufferStrategy(2);

        // Cria uma lista de objetos Inimigo - Terrestre Leve  e assim por diante
        lista_terrestres_pesado = new ArrayList();
        lista_terrestres_pesado_clone = (ArrayList<Terrestre_pesado>) lista_terrestres_pesado.clone();
        lista_Terrestre_leves = new ArrayList();
        lista_aereo_pesado = new ArrayList();
        lista_aereo_leve = new ArrayList();

        Terrestre_pesado minotauro = new Terrestre_pesado(sprites.get(0), caminho);
        Aereo_leve morcego = new Aereo_leve(sprites.get(2), caminho);
        lista_terrestres_pesado.add(minotauro);
        lista_aereo_leve.add(morcego);
        desenhaveis.add(minotauro);
        desenhaveis.add(morcego);
        while (gameLoop) {
            long beforeTime = System.currentTimeMillis();

            // Colocar timing para o jogador pensar (em segundos)
            // Checa se o jogador nao vai construir ou melhorar alguma torre
            // Caso construa, adiciona a estrutura numa lista de estruturas
            // Gera wave baseado em valores pré determinados com base no round
            geraWave(round, qtds);

            switch (round) {

                case 1:
                    while (excess > DESIRED_UPDATE_TIME) {
                        for (Terrestre_pesado inimigo : lista_terrestres_pesado) {
                            inimigo.andar();
                            if (inimigo.isMorto()) {
                                desenhaveis.remove(inimigo);
                            }

                        }
                        for (Aereo_leve inimigo : lista_aereo_leve) {
                            inimigo.andar();
                            if (inimigo.isMorto()) {
                                desenhaveis.remove(inimigo);
                            }

                        }

                        excess -= DESIRED_UPDATE_TIME;
                    }
                    for (int i = 0; i < desenhaveis.size(); i++) {

                    }
                    // Conta qts inimigos tem de cada tipo
                    //for (int j = 0; j < qtds.length; j++) {
                    //for (int i = 0; i < qtds[j]; i++) {
                    // Instancia esses inimigos
                    //Terrestre_leve inimigo = new Terrestre_leve(sprites.get(4),caminho);
                    //Aereo_pesado inimigo = new Aereo_pesado(sprites.get(6), caminho);
                    //Aereo_leve inimigo = new Aereo_leve(sprites.get(2), caminho);
                    // Adiciona na sua respectiva lista

                    //lista_Terrestre_leves.add(inimigo);
                    //lista_aereo_pesado.add(inimigo);
                    //lista_aereo_leve.add(inimigo);
                    for (Terrestre_pesado inimigo : lista_terrestres_pesado) {

                        inimigo.andar();
                        if (inimigo.isMorto()) {
                            desenhaveis.remove(inimigo);
                        }
                    }

                    for (Aereo_leve inimigo : lista_aereo_leve) {
                        inimigo.andar();
                        if (inimigo.isMorto()) {
                            desenhaveis.remove(inimigo);
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
                                    
                    
                     */
                    // Caso todos os inimigos estejam mortos, round acaba
                    if (lista_terrestres_pesado.isEmpty()) {
                        round++;
                    }

                    //gameLoop = false;
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

            excess -= DESIRED_UPDATE_TIME;

            long afterTime = System.currentTimeMillis();
            long sleepTime = afterTime - beforeTime;

            if (sleepTime < DESIRED_UPDATE_TIME) {
                Thread.sleep(DESIRED_UPDATE_TIME - sleepTime);
                noDelays = 0;
            } else {
                excess += sleepTime - DESIRED_UPDATE_TIME;

                if (++noDelays == NO_DELAYS_PER_YIELD) {
                    Thread.yield();
                }
            }
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

        BufferStrategy strategy = this.getBufferStrategy();
        if (strategy == null) {
            return;
        }
        do {
            do {
                Graphics graphics = strategy.getDrawGraphics();
                // Clear the previous frame
                graphics.clearRect(0, 0, 800, 800);
                // For each drawable object in list, paint
                this.MAP.paintComponent(graphics);
                for (Desenhavel objeto : desenhaveis) {
                    objeto.paintComponent(graphics);
                }
                graphics.dispose();
            } while (strategy.contentsRestored());
            strategy.show();
        } while (strategy.contentsLost());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == '1') {
            // mostra tipo da torre e onde pode consturir 
            System.out.println("APERTEI A 1");
        }
        if (e.getKeyChar() == '2') {
            // mostra tipo da torre e onde pode consturir 
            System.out.println("APERTEI A 2");
        }
        if (e.getKeyChar() == '3') {
            // mostra tipo da torre e onde pode consturir 
            System.out.println("APERTEI A 3");
        }
        if (e.getKeyChar() == '4') {
            // mostra tipo da torre e onde pode consturir 
            System.out.println("APERTEI A 4");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == '1') {
            // constroe torre terreste
            System.out.println("SOLTEI A 1");
        }
        if (e.getKeyChar() == '2') {
            // constroe torre aerea
            System.out.println("SOLTEI A 2");
        }
        if (e.getKeyChar() == '3') {
            //  constroe torre hibrida
            System.out.println("SOLTEI A 3");
        }
        if (e.getKeyChar() == '4') {
            //  constroe armadilha
            System.out.println("SOLTEI A 4");
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
