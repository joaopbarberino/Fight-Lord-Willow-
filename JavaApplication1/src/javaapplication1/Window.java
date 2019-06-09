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
    private ArrayList<Integer> caminho = new ArrayList();
    private ArrayList<Desenhavel> desenhaveis = new ArrayList();
    private ArrayList<Terrestre_pesado> lista_terrestres_pesados = new ArrayList();
    private ArrayList<Terrestre_leve> lista_terrestres_leves = new ArrayList();
    private ArrayList<Aereo_pesado> lista_aereos_pesados = new ArrayList();
    private ArrayList<Aereo_leve> lista_aereos_leves = new ArrayList();
    private boolean trocou_round = true;

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

        final long DESIRED_UPDATE_TIME = 30;
        final long NO_DELAYS_PER_YIELD = 4;

        // Instanciar o mapa da fase
        caminho = Mapa_exec(caminho);
        System.out.println("Caminho: " + caminho);

        Estrutura x = new Torre_terrestre(83);
        x.set_casas_no_alcance();
        x.get_casas_no_alcance();
        
        Base jogador = new Base(710,25,50,0,5000,0,sprites.get(8));
        desenhaveis.add(jogador);
        int round = 1;

        // Cria double-buffering strategy genérico
        this.createBufferStrategy(2);

        while (gameLoop) {

            if (trocou_round) {
                geraWave(round);
            }

            long beforeTime = System.currentTimeMillis();

            // Colocar timing para o jogador pensar (em segundos)
            // Checa se o jogador nao vai construir ou melhorar alguma torre
            // Caso construa, adiciona a estrutura numa lista de estruturas
            // Gera wave baseado em valores pré determinados com base no round
            this.trocou_round = false;
            while (excess > DESIRED_UPDATE_TIME) {
                moverInimigos();
                limparListas();
                excess -= DESIRED_UPDATE_TIME;
            }
            moverInimigos();
            limparListas();
                    //System.out.println(lista_aereos_leves);
            // Conta qts inimigos tem de cada tipo
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
            if (lista_aereos_leves.isEmpty() && lista_aereos_pesados.isEmpty() && lista_terrestres_leves.isEmpty() && lista_terrestres_pesados.isEmpty()) {
                System.out.println("!!!!!!");
                round++;
                this.trocou_round = true;
            }

                // Checa se o jogador tem xp o suficente para subir de nivel
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

    public void moverInimigos() {
        for (Terrestre_leve inimigo : lista_terrestres_leves) {
            inimigo.andar();
            if (inimigo.isMorto()) {
                desenhaveis.remove(inimigo);
            }
        }

        for (Terrestre_pesado inimigo : lista_terrestres_pesados) {
            inimigo.andar();
            if (inimigo.isMorto()) {
                desenhaveis.remove(inimigo);
            }
        }

        for (Aereo_leve inimigo : lista_aereos_leves) {
            inimigo.andar();
            if (inimigo.isMorto()) {
                desenhaveis.remove(inimigo);
            }
        }

        for (Aereo_pesado inimigo : lista_aereos_pesados) {
            inimigo.andar();
            if (inimigo.isMorto()) {
                desenhaveis.remove(inimigo);
            }
        }
    }

    public void limparListas() {
        ArrayList<Terrestre_pesado> lista_terrestres_pesados_clone = (ArrayList<Terrestre_pesado>) lista_terrestres_pesados.clone();
        ArrayList<Terrestre_leve> lista_terrestres_leves_clone = (ArrayList<Terrestre_leve>) lista_terrestres_leves.clone();
        ArrayList<Aereo_pesado> lista_aereos_pesados_clone = (ArrayList<Aereo_pesado>) lista_aereos_pesados.clone();
        ArrayList<Aereo_leve> lista_aereos_leves_clone = (ArrayList<Aereo_leve>) lista_aereos_leves.clone();

        for (Terrestre_leve inimigo : lista_terrestres_leves_clone) {
            if (inimigo.isMorto()) {
                this.lista_terrestres_leves.remove(inimigo);
            }
        }

        for (Terrestre_pesado inimigo : lista_terrestres_pesados_clone) {
            if (inimigo.isMorto()) {
                this.lista_terrestres_pesados.remove(inimigo);
            }
        }

        for (Aereo_leve inimigo : lista_aereos_leves_clone) {
            if (inimigo.isMorto()) {
                this.lista_aereos_leves.remove(inimigo);
            }
        }

        for (Aereo_pesado inimigo : lista_aereos_pesados_clone) {
            if (inimigo.isMorto()) {
                this.lista_aereos_pesados.remove(inimigo);
            }
        }
    }

    public void geraWave(int round) {
        switch (round) {
            case 1:
                for (int j = 0; j < 4; j++) {
                    for (int i = 0; i < 10; i++) {
                        // Instancia esses inimigos
                        // Adiciona na sua respectiva lista
                        if (j == 0) {
                            System.out.println("sdfgjmisda");
                            Terrestre_leve inimigo = new Terrestre_leve(sprites.get(4), caminho);
                            this.lista_terrestres_leves.add(inimigo);
                            this.desenhaveis.add(inimigo);
                        } else if (j == 1) {
                            System.out.println("345345345");
                            Terrestre_pesado inimigo = new Terrestre_pesado(sprites.get(0), caminho);
                            this.lista_terrestres_pesados.add(inimigo);
                            this.desenhaveis.add(inimigo);
                        } else if (j == 2) {
                            System.out.println("#@$#@$@#$");
                            Aereo_leve inimigo = new Aereo_leve(sprites.get(2), caminho);
                            this.lista_aereos_leves.add(inimigo);
                            this.desenhaveis.add(inimigo);
                        } else if (j == 3) {
                            System.out.println("><><><><><><><");
                            Aereo_pesado inimigo = new Aereo_pesado(sprites.get(6), caminho);
                            this.lista_aereos_pesados.add(inimigo);
                            this.desenhaveis.add(inimigo);
                        }

                    }
                }
                break;

            case 2:
                for (int j = 0; j < 4; j++) {
                    for (int i = 0; i < 10; i++) {
                        // Instancia esses inimigos
                        // Adiciona na sua respectiva lista
                        if (j == 0) {
                            System.out.println("sdfgjmisda");
                            Terrestre_leve inimigo = new Terrestre_leve(sprites.get(4), caminho);
                            this.lista_terrestres_leves.add(inimigo);
                            this.desenhaveis.add(inimigo);
                        } else if (j == 1) {
                            System.out.println("345345345");
                            Terrestre_pesado inimigo = new Terrestre_pesado(sprites.get(0), caminho);
                            this.lista_terrestres_pesados.add(inimigo);
                            this.desenhaveis.add(inimigo);
                        } else if (j == 2) {
                            System.out.println("#@$#@$@#$");
                            Aereo_leve inimigo = new Aereo_leve(sprites.get(2), caminho);
                            this.lista_aereos_leves.add(inimigo);
                            this.desenhaveis.add(inimigo);
                        } else if (j == 3) {
                            System.out.println("><><><><><><><");
                            Aereo_pesado inimigo = new Aereo_pesado(sprites.get(6), caminho);
                            this.lista_aereos_pesados.add(inimigo);
                            this.desenhaveis.add(inimigo);
                        }

                    }
                }
                break;
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
