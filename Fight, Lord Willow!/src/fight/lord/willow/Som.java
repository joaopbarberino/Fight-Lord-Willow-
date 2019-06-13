//********************************************************************/
//*********************** Referencias*********************************/
//***********Auxilio colegas classe(into the dwarfness)***************/
/*********************************************************************/
package fight.lord.willow;;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JApplet;
/**
 *
 * @author Emerson Marques
 */
public class Som extends JApplet {   

    private URL sound_path_url;
    private java.applet.AudioClip song;

    public Som(String path) throws MalformedURLException {
        sound_path_url = new File(path).toURI().toURL();
        song = java.applet.Applet.newAudioClip(sound_path_url);
    }
    
    public void tocaLoop() {
        song.loop();
    }

    public void pararSom() {
        song.stop();
    }
    
    public void tocaUmaVez() {
        song.play();
    }
}
