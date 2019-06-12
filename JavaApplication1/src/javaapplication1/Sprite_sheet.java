//***********************************************************/
//********************Referencias****************************/
//********https://www.youtube.com/watch?v=91repoElLZU&t=498s*/
//********https://www.youtube.com/watch?v=rWzINXeC0lY *******/
//***********************************************************/
package javaapplication1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author pedro.hlaredes
 */
public class Sprite_sheet {

    private BufferedImage sprite_sheet;

    public Sprite_sheet(String path) {
        try {
            sprite_sheet = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  BufferedImage getSprite(int x, int y, int width, int height) {
        return sprite_sheet.getSubimage(x, y, width, height);
    }
}
