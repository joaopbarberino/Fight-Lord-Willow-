/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import Estrutura.*;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import mapa.No;

/**
 *
 * @author mathe
 */
public class Window extends JFrame implements KeyListener {

    //private BufferedImage minotaur = null;    
    private Tile_layer MAP;
    public ArrayList<BufferedImage> sprites;
    private ArrayList<Integer> caminho = new ArrayList();
    private Mapa mapa = null;
    public ArrayList<Desenhavel> desenhaveis = new ArrayList();
    public ArrayList<Desenhavel> torres_desenhaveis = new ArrayList();
    private ArrayList<Terrestre_pesado> lista_terrestres_pesados = new ArrayList();
    private ArrayList<Terrestre_leve> lista_terrestres_leves = new ArrayList();
    private ArrayList<Aereo_pesado> lista_aereos_pesados = new ArrayList();
    private ArrayList<Aereo_leve> lista_aereos_leves = new ArrayList();
    private ArrayList<Inimigo> inimigos = new ArrayList();
    private ArrayList<Torre_terrestre> lista_torres_terrestres = new ArrayList();
    private static ArrayList<int[]> posicoes = new ArrayList<int[]>();
    private static ArrayList<No> construiveis = new ArrayList<No>();
    private int clickX, clickY, id;
    private static int elementoLista[] = new int[2];
    private No teste = null;

    private int qtds[] = new int[4], pula_geracao = 0, segura_wave = 1;
    private boolean setou = true, comecou_round = false, torre_1 = false, torre_2 = false;

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
        this.addMouseListener(new MouseAdapter() {// Precisa adicionar o evento na window para q o clique funcione apenas dentro da tela
            @Override //Override apenas para o metodo de demonstracao
            public void mousePressed(MouseEvent e) {
                clickX = e.getX();
                clickY = e.getY();
                System.out.println("X :" + clickX + ", y: " + clickY);
            }
        });
    }

    public void run() throws InterruptedException, IOException {
        Sprite_sheet sprite_terrestre;
        boolean gameLoop = true;

        long excess = 0;
        long noDelays = 0;

        final long DESIRED_UPDATE_TIME = 30;
        final long NO_DELAYS_PER_YIELD = 4;

        // Instanciar o mapa da fase
        caminho = Mapa_exec(caminho);
        System.out.println("Caminho: " + caminho);
        System.out.println("contruiveis: " + construiveis.toString());

        Base jogador = new Base(710, 25, 50, 0, 5000, 0, sprites.get(8));
        desenhaveis.add(jogador);
        int round = 1;

        // Cria double-buffering strategy genérico
        this.createBufferStrategy(2);

        teste = construiveis.get(0);
        while (gameLoop) {
            if (!comecou_round) {
                if (clickX != 0 && clickY != 0) {
                    for (int i = 0; i < construiveis.size(); i++) {
                        teste = construiveis.get(i);
                        if (Math.abs(teste.getX() - clickX) < 40 && Math.abs(teste.getY() - clickY) < 40 && (torre_1 == true || torre_2 == true)) {
                            System.out.println("Construiu");
                            mapa.getMapa().get(teste.getId()).setBloqueado(true);
                            mapa.getMapa().get(teste.getId()).setConstruivel(false);
                            Torre_terrestre torre = new Torre_terrestre(teste.getId(), sprites.get(10), sprites.get(9));
                            torre.set_casas_no_alcance();
                            lista_torres_terrestres.add(torre);
                            desenhaveis.add(torre);
                            torre_1 = false;
                            torre_2 = false;
                            break;
                        }
                    }
                    clickX = -1;
                    clickY = -1;
                }
            }

            if (segura_wave % 300 == 0) {
                geraWave(round);
            } else {
                this.segura_wave++;
            }

            long beforeTime = System.currentTimeMillis();

            // Colocar timing para o jogador pensar (em segundos)
            // Checa se o jogador nao vai construir ou melhorar alguma torre
            // Caso construa, adiciona a estrutura numa lista de estruturas
            while (excess > DESIRED_UPDATE_TIME) {
                moverInimigos();
                limparListas();

                excess -= DESIRED_UPDATE_TIME;
            }
            moverInimigos();
            limparListas();

//            for (Torre_terrestre x : lista_torres_terrestres) {
//                for (Inimigo inimigo : inimigos) {
//                    if (x.isNoRange(inimigo)) {
//                        x.setAlvo(inimigo);
//                    }
//                }
//                x.atacar();
//                System.out.println(x.getAlvos_sendo_atacados());
//                x.limpaAlvos();
//            }
//            for (Inimigo inimigo : inimigos) {
//                for (Torre_terrestre x : lista_torres_terrestres) {
//                    if (x.isNoRange(inimigo)) {
//                        x.atacar(inimigo);
//                    } else if (!x.isNoRange(inimigo)) {
//                        x.naoAtacar(inimigo);
//                    }
//                }
//            }

            /*
                         
             Para cada inimigo da lista diferente de null
                    
             Chama função abaixo:
             Checa se ele não está no range de todas as torres
             -> Se tiver, a torre ataca enquanto o inimigo estiver no range {
             -> Inimigo recebe o ataque
             -> Se vida do inimigo <= 0, ele morre e é removido da lista
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
            if (comecou_round) {
                if (lista_aereos_leves.isEmpty() && lista_aereos_pesados.isEmpty() && lista_terrestres_leves.isEmpty() && lista_terrestres_pesados.isEmpty()) {
                    System.out.println("!!!!!!");
                    this.setou = true;
                    this.pula_geracao = 0;
                    if (round < 7) {
                        round++;
                    }
                    comecou_round = false;
                }
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

//        for (Desenhavel objeto : desenhaveis_clone) {
//            this.desenhaveis.add(objeto);
//        }
//        desenhaveis_clone.clear();
        for (Terrestre_leve inimigo : lista_terrestres_leves_clone) {
            if (inimigo.isMorto()) {
                this.lista_terrestres_leves.remove(inimigo);
                this.inimigos.remove(inimigo);
            }
        }

        for (Terrestre_pesado inimigo : lista_terrestres_pesados_clone) {
            if (inimigo.isMorto()) {
                this.lista_terrestres_pesados.remove(inimigo);
                this.inimigos.remove(inimigo);
            }
        }

        for (Aereo_leve inimigo : lista_aereos_leves_clone) {
            if (inimigo.isMorto()) {
                this.lista_aereos_leves.remove(inimigo);
                this.inimigos.remove(inimigo);
            }
        }

        for (Aereo_pesado inimigo : lista_aereos_pesados_clone) {
            if (inimigo.isMorto()) {
                this.lista_aereos_pesados.remove(inimigo);
                this.inimigos.remove(inimigo);
            }
        }

    }

    public void geraWave(int round) {
        this.comecou_round = true;
        //qtds[0] == terrestre leve
        //qtds[1] == terrestre pesado
        //qtds[2] == aereo leve
        //qtds[3] == areo pesado
        switch (round) {
            case 1:
                if (setou) {
                    this.qtds[0] = 10;
                    this.qtds[1] = 1;
                    this.qtds[2] = 0;
                    this.qtds[3] = 0;
                    this.setou = false;
                }
                break;

            case 2:
                if (setou) {
                    this.qtds[0] = 10;
                    this.qtds[1] = 3;
                    this.qtds[2] = 0;
                    this.qtds[3] = 0;
                    this.setou = false;
                }
                break;

            case 3:
                if (setou) {
                    this.qtds[0] = 10;
                    this.qtds[1] = 3;
                    this.qtds[2] = 2;
                    this.qtds[3] = 0;
                    this.setou = false;
                }
                break;

            case 4:
                if (setou) {
                    this.qtds[0] = 10;
                    this.qtds[1] = 3;
                    this.qtds[2] = 2;
                    this.qtds[3] = 2;
                    this.setou = false;
                }
                break;

            case 5:
                if (setou) {
                    this.qtds[0] = 15;
                    this.qtds[1] = 5;
                    this.qtds[2] = 5;
                    this.qtds[3] = 3;
                    this.setou = false;
                }
                break;

            case 6:
                if (setou) {
                    this.qtds[0] = 25;
                    this.qtds[1] = 10;
                    this.qtds[2] = 7;
                    this.qtds[3] = 4;
                    this.setou = false;
                }
                break;

            case 7:
                if (setou) {
                    this.qtds[0] = 10;
                    this.qtds[1] = 0;
                    this.qtds[2] = 0;
                    this.qtds[3] = 0;
                    this.setou = false;
                }
                break;

        }

        // Instancia esses inimigos
        // Adiciona na sua respectiva lista
        if (pula_geracao % 10 == 0) {
            if (qtds[0] > 0) {
                Terrestre_leve inimigo = new Terrestre_leve(sprites.get(4), caminho);
                this.lista_terrestres_leves.add(inimigo);
                this.inimigos.add(inimigo);
                this.desenhaveis.add(inimigo);
                this.qtds[0]--;

            }
            if (qtds[1] > 0) {
                Terrestre_pesado inimigo = new Terrestre_pesado(sprites.get(0), caminho, sprites.get(1));
                this.lista_terrestres_pesados.add(inimigo);
                this.inimigos.add(inimigo);
                this.desenhaveis.add(inimigo);
                this.qtds[1]--;

            }
            if (qtds[2] > 0) {
                Aereo_leve inimigo = new Aereo_leve(sprites.get(2), caminho);
                this.lista_aereos_leves.add(inimigo);
                this.inimigos.add(inimigo);
                this.desenhaveis.add(inimigo);
                this.qtds[2]--;

            }
            if (qtds[3] > 0) {
                Aereo_pesado inimigo = new Aereo_pesado(sprites.get(6), caminho);
                this.lista_aereos_pesados.add(inimigo);
                this.inimigos.add(inimigo);
                this.desenhaveis.add(inimigo);
                this.qtds[3]--;
            }
        }
        this.pula_geracao++;

    }

    private ArrayList Mapa_exec(ArrayList caminho) {
        this.mapa = new Mapa(20, 20);
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
        posicoes = mapa.pega_Pos();
        elementoLista = posicoes.get(200);
        construiveis = mapa.Get_construiveis();

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
        }
        if (e.getKeyChar() == '2') {
            // mostra tipo da torre e onde pode consturir 
        }
        if (e.getKeyChar() == '3') {
            // mostra tipo da torre e onde pode consturir 
        }
        if (e.getKeyChar() == '4') {
            // mostra tipo da torre e onde pode consturir 
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == '1') {
            // constroe torre terreste
            torre_1 = true;
        }
        if (e.getKeyChar() == '2') {
            // constroe torre aerea
            torre_2 = true;
        }
        if (e.getKeyChar() == '3') {
            //  constroe torre hibrida
        }
        if (e.getKeyChar() == '4') {
            //  constroe armadilha
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
