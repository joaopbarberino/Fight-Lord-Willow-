/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author joao.pbsilva20
 */
public class JavaApplication1  {    
    //public static ArrayList<Terrestre_leve> lista_terrestres_leves = new ArrayList();
    public static void main(String[] args) throws IOException, InterruptedException {
        Tile_layer layer = Tile_layer.From_file("mapa.txt");
        ArrayList<BufferedImage> sprites = new ArrayList();
        //sprites[0] = minotauro andando
        //sprites[1] = minotauro morrendo
        //sprites[2] = morcego andando
        //sprites[3] = morcego morrendo
        //sprites[4] = IMP andando
        //sprites[5] = IMP morrendo
        //sprites[6] = dragão andando
        //sprites[7] = dragão morrendo
        
        sprites.add(ImageIO.read(new File("minitaur.png")));
        sprites.add(ImageIO.read(new File("Minotauro-morrendo.png")));
        sprites.add(ImageIO.read(new File("Morcego-voando.png")));
        sprites.add(ImageIO.read(new File("Morcego-morrendo.png")));
        sprites.add(ImageIO.read(new File("IMP-correndo.png")));
        sprites.add(ImageIO.read(new File("IMP-morrendo.png")));        
        
        
        Window screen = new Window(sprites, layer);
        screen.run();
    }

    public static int[] geraWave(int round, int qtds[]) {
        int qtd_inimigos_terrestres_leves = 0;
        int qtd_inimigos_terrestres_pesados = 0;
        int qtd_inimigos_aereos_leves = 0;
        int qtd_inimigos_aereos_pesados = 0;

        switch (round) {
            case 1:
                qtd_inimigos_terrestres_pesados = 10;
                qtds[0] = qtd_inimigos_terrestres_pesados;

                break;
        }

        return qtds;

    }
}
