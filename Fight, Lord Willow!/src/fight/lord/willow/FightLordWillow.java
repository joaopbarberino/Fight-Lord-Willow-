/*********************************************************************/
/** Centro Universitario Senac ***************************************/
/** Tecnologia em Jogos Digitais - 1o semestre de 2019 ***************/
/** Professor: <Bruno Sanches> ***************************************/
/** Projeto Integrador III - Projeto Final ***************************/
/** Arquivo: <Fight, Lord Willow!> ***********************************/
/*********************************************************************/
/**Nome 1: <Pedro Henrique Lacerda Aredes> ***************************/
/**Nome 2: <Joao Pedro Barberino> ************************************/
/**Nome 3: <Emerson Marques Ferreira> ********************************/
/**Data de entrega: <13/06/2019> **/
/*********************************************************************/
//********************************************************************/
//************************Referencias*********************************/
//*******************Auxilio colegas classe***************************/
//****Into The Dwarfness: github.com/waffliors/Into-The-Dwarfness*****/
/*********************************************************************/

package fight.lord.willow;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
/**
 *
 * @author joao.pbsilva20
 */
public class FightLordWillow  {    
    public static void main(String[] args) throws IOException, InterruptedException {
        Tile_layer layer = Tile_layer.FromFile("mapa.txt");
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
        //sprites[9] = ataque da torre
        //sprites[10] = Torre 1
        //sprites[11] = base 2
        //sprites[12] = base 3
        //sprites[13] = torre 2
        //sprites[14] = torre 1 atacando
        //sprites[15] = torre 2 atacando
        //sprites[16] = Background
        
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
        sprites.add(ImageIO.read(new File("background.png")));

        //som[0] baseDMG
        //som[1] mageATK
        //som[2] minotaurDeath
        //som[3] dragonDeath
        //som[4] impDeath
        //som[5] batDeath
        //som[6] magic
        //som[7] gameOver
        //som[8] destructionCastle
        //som[9] hammering
        //som[10] ambientSound
        
        sonsList.add(new Som("sons/sfx/baseDMG.wav"));
        sonsList.add(new Som("sons/sfx/mageATK.wav"));
        sonsList.add(new Som("sons/sfx/minotaurDeath.wav"));
        sonsList.add(new Som("sons/sfx/dragonDeath.wav"));
        sonsList.add(new Som("sons/sfx/impDeath.wav"));
        sonsList.add(new Som("sons/sfx/batDeath.wav"));
        sonsList.add(new Som("sons/sfx/magic.wav"));
        sonsList.add(new Som("sons/sfx/gameOver.wav"));
        sonsList.add(new Som("sons/sfx/destructionCastle.wav"));
        sonsList.add(new Som("sons/sfx/hammering.wav"));
        sonsList.add(new Som("sons/music/ambientSound.wav"));

        Window screen = new Window(sprites, layer, sonsList);
        screen.run();
    }


}
