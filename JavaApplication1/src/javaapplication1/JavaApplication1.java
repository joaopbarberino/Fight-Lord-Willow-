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
        sprites.add(ImageIO.read(new File("minitaur.png")));
        
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
