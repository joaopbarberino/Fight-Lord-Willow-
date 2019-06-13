//********************************************************************/
//*********************** Referencias*********************************/
//***********Auxilio colegas classe(into the dwarfness)***************/
/**
 * ******************************************************************
 */
package javaapplication1;

import Estrutura.*;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import mapa.AEstrela;
import mapa.Mapa;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapa.No;

public class Window extends JFrame implements KeyListener {

    // ----------------------- TO DO ----------------------------
    //-> tirar oq ainda resta de HARDCODED
    // ----------------------- TO DO ----------------------------
    private Tile_layer MAP;
    public ArrayList<BufferedImage> sprites;
    private final ArrayList<Som> sons;
    private ArrayList<Integer> caminho = new ArrayList();
    private Mapa mapa = null;
    private boolean gameLoop = true;
    //public boolean pronto_para_jogar = false;
    private int round = 1, wave = 1;
    public ArrayList<Desenhavel> desenhaveis = new ArrayList();
    public ArrayList<Desenhavel> torres_desenhaveis = new ArrayList();
    private ArrayList<Terrestre_pesado> lista_terrestres_pesados = new ArrayList();
    private ArrayList<Terrestre_leve> lista_terrestres_leves = new ArrayList();
    private ArrayList<Aereo_pesado> lista_aereos_pesados = new ArrayList();
    private ArrayList<Aereo_leve> lista_aereos_leves = new ArrayList();
    private ArrayList<Inimigo> inimigos = new ArrayList();
    private ArrayList<Torre_terrestre> lista_torres_terrestres = new ArrayList();
    private ArrayList<Torre_aerea> lista_torres_aereas = new ArrayList();
    private static ArrayList<No> construiveis = new ArrayList<No>();

    private int clickX, clickY;
    private No no_torre = null;

    private int qtds[] = new int[4], pula_geracao = 0, segura_wave = 1, qtdsRound7[] = new int[4];
    private boolean setou = true, comecou_round = false, torre_1 = false, torre_2 = false;

    public Window(ArrayList<BufferedImage> sprites, Tile_layer layer, ArrayList<Som> sounds) {
        super("Attack, Lord Willow!");
        this.setResizable(false);
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addKeyListener(this);
        this.MAP = layer;
        this.sprites = sprites;
        this.sons = sounds;
        this.addMouseListener(new MouseAdapter() {// Precisa adicionar o evento na window para q o clique funcione apenas dentro da tela
            @Override
            public void mousePressed(MouseEvent e) {
                clickX = e.getX();
                clickY = e.getY();
                System.out.println("X :" + clickX + ", y: " + clickY);
            }
        });
    }

    public void run() throws InterruptedException, IOException {

        long excess = 0;
        long noDelays = 0;

        final long DESIRED_UPDATE_TIME = 30;
        final long NO_DELAYS_PER_YIELD = 4;

        // Instanciar o mapa da fase
        caminho = Mapa_exec(caminho);

        Base jogador = new Base(710, 25, 10, 0, 500, 0, sprites.get(8), sprites, sons);
        desenhaveis.add(jogador);
        System.out.println(jogador.getGold());

        this.segura_wave = 0;
        this.pula_geracao = 0;
        this.setou = true;
        this.comecou_round = true;

        // Cria double-buffering strategy genérico
        this.createBufferStrategy(2);

        no_torre = construiveis.get(0);
        while (gameLoop) {
            //if (pronto_para_jogar) {
                if (clickX != 0 && clickY != 0) {
                    for (int i = 0; i < construiveis.size(); i++) {
                        no_torre = construiveis.get(i);
                        if (Math.abs(no_torre.getX() - clickX) < 40 && Math.abs(no_torre.getY() - clickY) < 40 && (torre_1 || torre_2)) {
                            mapa.getMapa().get(no_torre.getId()).setBloqueado(true);
                            mapa.getMapa().get(no_torre.getId()).setConstruivel(false);

                            if (torre_1) {
                                Torre_terrestre torre_terrestre = new Torre_terrestre(no_torre.getId(), sprites, sons);
                                if (jogador.getGold() >= torre_terrestre.getPreco()) {
                                    jogador.reduzGold(torre_terrestre.getPreco());
                                    torre_terrestre.setCasasNoAlcance();
                                    torre_terrestre.getCasasNoAlcance();
                                    lista_torres_terrestres.add(torre_terrestre);
                                    sons.get(6).tocaUmaVez();
                                    desenhaveis.add(torre_terrestre);
                                } else {
                                    torre_terrestre = null;
                                    System.out.println("n tem dinheiro pra construir torre 1");
                                }
                            } else if (torre_2) {
                                Torre_aerea torre_aerea = new Torre_aerea(no_torre.getId(), sprites, sons);
                                if (jogador.getGold() >= torre_aerea.getPreco()) {
                                    jogador.reduzGold(torre_aerea.getPreco());
                                    torre_aerea.setCasasNoAlcance();
                                    torre_aerea.getCasasNoAlcance();
                                    lista_torres_aereas.add(torre_aerea);
                                    sons.get(6).tocaUmaVez();
                                    desenhaveis.add(torre_aerea);
                                } else {
                                    torre_aerea = null;
                                    System.out.println("n tem dinheiro pra construir torre 2");
                                }
                            }
                            torre_1 = false;
                            torre_2 = false;
                            break;
                        }
                    }
                    clickX = -1;
                    clickY = -1;
                }


                // Segura a wave por algum tempo para o jogador pensar
                // ----------------------- TO DO ----------------------------
                // -> Descobrir a relação desse numero x segundos
                // ----------------------- TO DO ----------------------------
                if (segura_wave % 100 == 0) {
                    geraWave();
                } else {
                    this.segura_wave++;
                }

                long beforeTime = System.currentTimeMillis();

                while (excess > DESIRED_UPDATE_TIME) {
                    moverInimigos(jogador);
                    limparListas();
                    if (jogador.isMorto()) {
                        this.gameLoop = false;
                    }

                    for (Inimigo inimigo : inimigos) {
                        for (Torre_terrestre torre : lista_torres_terrestres) {
                            if (torre.isNoRange(inimigo)) {
                                torre.atacar(inimigo);
                            }
                        }

                    }
                    for (Inimigo inimigo : inimigos) {
                        for (Torre_aerea torre : lista_torres_aereas) {
                            if (torre.isNoRange(inimigo)) {
                                torre.atacar(inimigo);
                            }
                        }

                    }
                    excess -= DESIRED_UPDATE_TIME;
                }

                // ----------------------- TO DO ----------------------------
                // -> Arrumar animação de morte do inimigo
                // ----------------------- TO DO ----------------------------
                moverInimigos(jogador);
                limparListas();

                // ----------------------- TO DO ----------------------------
                // -> Descobrir pq o replay (ta no key pressed/released) não faz
                // o jogo começar dnv e trava o jframe (provavelmente loop infinito
                // em algum lugar)
                // ----------------------- TO DO ----------------------------
                if (jogador.isMorto()) {
                    sons.get(8).tocaUmaVez();
                    sons.get(7).tocaUmaVez();
                    this.gameLoop = false;
                }

                for (Inimigo inimigo : inimigos) {
                    for (Torre_terrestre torre : lista_torres_terrestres) {
                        if (torre.isNoRange(inimigo)) {
                            torre.atacar(inimigo);
                        }
                    }

                }
                for (Inimigo inimigo : inimigos) {
                    for (Torre_aerea torre : lista_torres_aereas) {
                        if (torre.isNoRange(inimigo)) {
                            torre.atacar(inimigo);
                        }
                    }

                }
                excess -= DESIRED_UPDATE_TIME;
            }

            moverInimigos(jogador);
            limparListas();

            // ----------------------- TO DO ----------------------------
            // -> Descobrir pq o replay (ta no key pressed/released) não faz
            // o jogo começar dnv e trava o jframe (provavelmente loop infinito
            // em algum lugar)
            // ----------------------- TO DO ----------------------------
            if (jogador.isMorto()) {
                sons.get(8).tocaUmaVez();
                sons.get(7).tocaUmaVez();
                this.gameLoop = false;
            }

            for (Inimigo inimigo : inimigos) {
                for (Torre_terrestre torre : lista_torres_terrestres) {
                    if (torre.isNoRange(inimigo)) {
                        torre.atacar(inimigo);
                    }
                }

            }
            for (Inimigo inimigo : inimigos) {
                for (Torre_aerea torre : lista_torres_aereas) {
                    if (torre.isNoRange(inimigo)) {
                        torre.atacar(inimigo);
                    }
                }

            }

                // Caso todos os inimigos estejam mortos, round acaba
                if (comecou_round) {
                    if (lista_aereos_leves.isEmpty()
                            && lista_aereos_pesados.isEmpty()
                            && lista_terrestres_leves.isEmpty()
                            && lista_terrestres_pesados.isEmpty()) {
                        jogador.upgrade();
                        this.setou = true;
                        this.pula_geracao = 0;
                        if (round < 7) {
                            this.round++;
                            System.out.println("round: " + round);
                        }
                        this.wave++;
                        System.out.println("wave: " + wave);
                        this.comecou_round = false;
                        this.segura_wave = 1;
                    }
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

            //} else {
                //repaint();
            //}
        }
    }

    public void moverInimigos(Base jogador) {
        for (Terrestre_leve inimigo : lista_terrestres_leves) {
            if (inimigo.animouMorte()) {
                desenhaveis.remove(inimigo);
                jogador.ganhaXp(inimigo.getXp());
                jogador.ganhaGold(inimigo.getGold());
            } else if (inimigo.chegouNoDestino()) {
                jogador.perdeVida(inimigo.getAtaque());
            } else if (!inimigo.isMorto()) {
                inimigo.andar();
            }
        }

        for (Terrestre_pesado inimigo : lista_terrestres_pesados) {
            if (inimigo.animouMorte()) {
                desenhaveis.remove(inimigo);
                jogador.ganhaXp(inimigo.getXp());
                jogador.ganhaGold(inimigo.getGold());
            } else if (inimigo.chegouNoDestino()) {
                jogador.perdeVida(inimigo.getAtaque());
            } else if (!inimigo.isMorto()) {
                inimigo.andar();
            }
        }

        for (Aereo_leve inimigo : lista_aereos_leves) {
            if (inimigo.animouMorte()) {
                desenhaveis.remove(inimigo);
                jogador.ganhaXp(inimigo.getXp());
                jogador.ganhaGold(inimigo.getGold());
            } else if (inimigo.chegouNoDestino()) {
                jogador.perdeVida(inimigo.getAtaque());
            } else if (!inimigo.isMorto()) {
                inimigo.andar();
            }
        }

        for (Aereo_pesado inimigo : lista_aereos_pesados) {

            if (inimigo.animouMorte()) {
                desenhaveis.remove(inimigo);
                jogador.ganhaXp(inimigo.getXp());
                jogador.ganhaGold(inimigo.getGold());
            } else if (inimigo.chegouNoDestino()) {
                jogador.perdeVida(inimigo.getAtaque());
            } else if (!inimigo.isMorto()) {
                inimigo.andar();
            }

        }

    }

    public void limparListas() {
        ArrayList<Terrestre_pesado> lista_terrestres_pesados_clone = (ArrayList<Terrestre_pesado>) lista_terrestres_pesados.clone();
        ArrayList<Terrestre_leve> lista_terrestres_leves_clone = (ArrayList<Terrestre_leve>) lista_terrestres_leves.clone();
        ArrayList<Aereo_pesado> lista_aereos_pesados_clone = (ArrayList<Aereo_pesado>) lista_aereos_pesados.clone();
        ArrayList<Aereo_leve> lista_aereos_leves_clone = (ArrayList<Aereo_leve>) lista_aereos_leves.clone();

        for (Terrestre_leve inimigo : lista_terrestres_leves_clone) {
            if (inimigo.isMorto() && inimigo.animouMorte()) {
                this.lista_terrestres_leves.remove(inimigo);
                this.inimigos.remove(inimigo);
            }
        }

        for (Terrestre_pesado inimigo : lista_terrestres_pesados_clone) {
            if (inimigo.isMorto() && inimigo.animouMorte()) {
                this.lista_terrestres_pesados.remove(inimigo);
                this.inimigos.remove(inimigo);
            }
        }

        for (Aereo_leve inimigo : lista_aereos_leves_clone) {
            if (inimigo.isMorto() && inimigo.animouMorte()) {
                this.lista_aereos_leves.remove(inimigo);
                this.inimigos.remove(inimigo);
            }
        }

        for (Aereo_pesado inimigo : lista_aereos_pesados_clone) {
            if (inimigo.isMorto() && inimigo.animouMorte()) {
                this.lista_aereos_pesados.remove(inimigo);
                this.inimigos.remove(inimigo);
            }
        }

    }

    public void geraWave() {
        this.comecou_round = true;
        //qtds[0] == terrestre leve
        //qtds[1] == terrestre pesado
        //qtds[2] == aereo leve
        //qtds[3] == areo pesado
        switch (round) {
            case 1:
                if (setou) {
                    this.qtds[0] = 0;
                    this.qtds[1] = 1;
                    this.qtds[2] = 0;
                    this.qtds[3] = 0;
                    this.setou = false;
                }
                break;

            case 2:
                if (setou) {
                    this.qtds[0] = 10;
                    this.qtds[1] = 0;
                    this.qtds[2] = 0;
                    this.qtds[3] = 0;
                    this.setou = false;
                }
                break;

            case 3:
                if (setou) {
                    this.qtds[0] = 7;
                    this.qtds[1] = 2;
                    this.qtds[2] = 0;
                    this.qtds[3] = 0;
                    this.setou = false;
                }
                break;

            case 4:
                if (setou) {
                    this.qtds[0] = 7;
                    this.qtds[1] = 2;
                    this.qtds[2] = 2;
                    this.qtds[3] = 0;
                    this.setou = false;
                }
                break;

            case 5:
                if (setou) {
                    this.qtds[0] = 7;
                    this.qtds[1] = 2;
                    this.qtds[2] = 3;
                    this.qtds[3] = 1;
                    this.setou = false;
                }
                break;

            case 6:
                if (setou) {
                    this.qtds[0] = 10;
                    this.qtds[1] = 3;
                    this.qtds[2] = 5;
                    this.qtds[3] = 2;
                    this.setou = false;
                }
                break;

            case 7:
                if (setou) {
                    this.qtds[0] = 30 + qtdsRound7[0];
                    this.qtds[1] = 12 + qtdsRound7[1];
                    this.qtds[2] = 10 + qtdsRound7[2];
                    this.qtds[3] = 7 + qtdsRound7[3];
                    this.qtdsRound7[0] += 3;
                    this.qtdsRound7[1] += 2;
                    this.qtdsRound7[2] += 3;
                    this.qtdsRound7[3] += 1;

                    this.setou = false;
                }
                break;

        }

        // Instancia esses inimigos
        // Adiciona na sua respectiva lista
        if (pula_geracao % 10 == 0) {
            if (qtds[0] > 0) {
                Terrestre_leve inimigo = new Terrestre_leve(sprites, caminho);
                this.lista_terrestres_leves.add(inimigo);
                this.inimigos.add(inimigo);
                this.desenhaveis.add(inimigo);
                this.qtds[0]--;

            }
            if (qtds[1] > 0) {
                Terrestre_pesado inimigo = new Terrestre_pesado(sprites, caminho);
                this.lista_terrestres_pesados.add(inimigo);
                this.inimigos.add(inimigo);
                this.desenhaveis.add(inimigo);
                this.qtds[1]--;

            }
            if (qtds[2] > 0) {
                Aereo_leve inimigo = new Aereo_leve(sprites, caminho);
                this.lista_aereos_leves.add(inimigo);
                this.inimigos.add(inimigo);
                this.desenhaveis.add(inimigo);
                this.qtds[2]--;

            }
            if (qtds[3] > 0) {
                Aereo_pesado inimigo = new Aereo_pesado(sprites, caminho);
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
        construiveis = mapa.GetConstruiveis();

        return caminho;
    }

    @Override
    public void paint(Graphics g) {
        //if (!pronto_para_jogar) {
            g.drawImage(this.sprites.get(16), 0, 0, null);
        //} else {
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
            g.setFont(new Font("Serif", Font.BOLD, 12));
            g.drawString("" + 90, 480, 744);
            g.drawString("" + 90, 480, 780);
            g.drawString("" + this.wave, 380, 744);
        //}
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
        if (e.getKeyChar() == 'r') {
            System.out.println("apertei r");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == '1') {
            // constroe torre terreste
            torre_1 = true;
            torre_2 = false;
        }
        if (e.getKeyChar() == '2') {
            // constroe torre aerea
            torre_1 = false;
            torre_2 = true;
        }
        if (e.getKeyChar() == '3') {
            System.out.println("apertei 3");
            //pronto_para_jogar = true;
        }

        if (e.getKeyChar() == 'r') {
            if (!gameLoop) {
                this.gameLoop = true;
                this.round = 1;
                desenhaveis.clear();
                torres_desenhaveis.clear();
                lista_terrestres_pesados.clear();
                lista_terrestres_leves.clear();
                lista_aereos_pesados.clear();
                lista_aereos_leves.clear();
                inimigos.clear();
                lista_torres_terrestres.clear();
                lista_torres_aereas.clear();;
                construiveis.clear();
                try {
                    this.run();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
