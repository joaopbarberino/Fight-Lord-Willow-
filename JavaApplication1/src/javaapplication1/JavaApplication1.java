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
    public static void main(String[] args) throws IOException, InterruptedException {
        Tile_layer layer = Tile_layer.From_file("mapa.txt");
        ArrayList<BufferedImage> sprites = new ArrayList();
        ArrayList<Som> sonsList = new ArrayList();
        //sprites[0] = minotauro andando
        //sprites[1] = minotauro morrendo
        //sprites[2] = morcego andando
        //sprites[3] = morcego morrendo
        //sprites[4] = IMP andando
        //sprites[5] = IMP morrendo
        //sprites[6] = dragão andando
        //sprites[7] = dragão morrendo
        //sprites[8] = base
        //sprites[9] = ataque da torre (provisorio)
        //sprites[10] = Torre 1
        //sprites[11] = base 2
        //sprites[12] = base 3
        //sprites[13] = torre 2
        //sprites[14] = torre 1 atacando
        //sprites[15] = torre 2 atacando
        
        sprites.add(ImageIO.read(new File("minitaur.png")));
        sprites.add(ImageIO.read(new File("Minotauro-morrendo.png")));
        sprites.add(ImageIO.read(new File("Morcego-voando.png")));
        sprites.add(ImageIO.read(new File("Morcego-morrendo.png")));
        sprites.add(ImageIO.read(new File("IMP-correndo.png")));
        sprites.add(ImageIO.read(new File("IMP-morrendo.png")));        
        sprites.add(ImageIO.read(new File("Dragao-voando.png")));        
        sprites.add(ImageIO.read(new File("Dragao-morrendo.png")));
        sprites.add(ImageIO.read(new File("base1.png")));    
        sprites.add(ImageIO.read(new File("torre-ataque.png")));
        sprites.add(ImageIO.read(new File("torre-1-idle.png")));
        sprites.add(ImageIO.read(new File("base2.png")));  
        sprites.add(ImageIO.read(new File("base3.png")));
        sprites.add(ImageIO.read(new File("torre-2-idle.png")));
        sprites.add(ImageIO.read(new File("torre-1-atacando.png")));
        sprites.add(ImageIO.read(new File("torre-2-atacando.png")));

        //som[0] baseDMG
        //som[1] mageATK
        //som[2] minotaurDeath
        //som[3] dragonDeath
        //som[4] impDeath
        //som[5] batDeath
        //som[6] magic
        //som[7] gameOver
        //som[8] destructionCastle
        
        sonsList.add(new Som("sons/sfx/baseDMG.wav"));
        sonsList.add(new Som("sons/sfx/mageATK.wav"));
        sonsList.add(new Som("sons/sfx/minotaurDeath.wav"));
        sonsList.add(new Som("sons/sfx/dragonDeath.wav"));
        sonsList.add(new Som("sons/sfx/impDeath.wav"));
        sonsList.add(new Som("sons/sfx/batDeath.wav"));
        sonsList.add(new Som("sons/sfx/magic.wav"));
        sonsList.add(new Som("sons/sfx/gameOver.wav"));
        sonsList.add(new Som("sons/sfx/destructionCastle.wav"));

        Window screen = new Window(sprites, layer, sonsList);
        screen.run();
    }


}
