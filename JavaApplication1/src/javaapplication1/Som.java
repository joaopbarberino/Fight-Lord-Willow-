/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JApplet;

/**
 *
 * @author Emerson Marques
 */
public class Som extends JApplet {   

    private URL soundPathUrl;
    private java.applet.AudioClip song;

    public Som(String path) throws MalformedURLException {
        soundPathUrl = new File(path).toURI().toURL();
        song = java.applet.Applet.newAudioClip(soundPathUrl);
    }
    
    public void tocaLoop() {
        song.loop();
    }
    /**
     * Srop the song
     */
    public void pararSom() {
        song.stop();
    }
    /**
     * Play the song just one time
     */
    public void tocaUmaVez() {
        song.play();
    }
}

