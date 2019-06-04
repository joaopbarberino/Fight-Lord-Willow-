/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author joao.pbsilva20
 */
public class Aereo_pesado extends Inimigo {

    private BufferedImage img;

    public Aereo_pesado() {
        // Valores de vida, ataque, defesa, velocidade de movimento, gold, xp 
        // e tipo, respectivamente
        //coloquei null no ultimo parametro que seria o da img só pra parar de dar erro.
        super(10, 2, 5, 1, 10, 20, "aereo");

        try {
            img = ImageIO.read(new File("/pics/minitaur.png"));

        } catch (IOException e) {
            System.out.println("EXCEPTION IN LOAD IMAGE:" + e.toString());
        }
    }   
   
}


    
//    public void render(Graphics g) {
//        //caso minotauro ande, carrega sprite dele andando, caso contrario, dele parado
//        if (andar()) {
//            for (BufferedImage minotaurs_anda1 : minotaurs_anda) {
//                g.drawImage(minotaurs_anda1, 150, 150, null);
//            }
//            //g.drawImage(sprites[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
//        } else {
//            for (BufferedImage minotaurs_anda1 : minotaurs_anda) {
//                g.drawImage(minotaurs_anda1, 150, 150, null);
//            }
//            //g.drawImage(Entity.ENEMY_FEEDBACK, this.getX() - Camera.x, this.getY() - Camera.y, null);
//        }
//        //g.setColor(Color.blue);
//        //g.fillRect(this.getX() + maskX - Camera.x,this.getY() + maskY - Camera.y, maskW,maskH);
//    }
//
//}
