/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darkinsrevenge;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Jam
 */
public class MusicThread extends Thread {
    private static MusicThread instance = null;
    private static String musicPlaying;
    static Clip c;
     AudioInputStream s = null;
    
    public static MusicThread getInstance() {
        if (instance == null)
            instance = new MusicThread();
        
        return instance;
    }
    
    private MusicThread() {
        musicPlaying = "";
    }
    
    public void startMusic(String fileName) {
        this.musicPlaying = fileName;
    }
    
    public void stopMusic() {
        c.stop();
    }
    
    public void play() throws LineUnavailableException {
        try {
            s = AudioSystem.getAudioInputStream(new File(this.musicPlaying).getAbsoluteFile());
            c = AudioSystem.getClip();
            c.open(s);
            c.start();
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(MusicThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MusicThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public synchronized void start() {
        super.start(); 
        
            try {
                s = AudioSystem.getAudioInputStream(new File(this.musicPlaying).getAbsoluteFile());
                c = AudioSystem.getClip();
                c.open(s);
                c.start();
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(MainMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MainMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (LineUnavailableException ex) {
                Logger.getLogger(MainMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    s.close();
                } catch (IOException ex) {
                    Logger.getLogger(MainMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
          
    }

    
}
