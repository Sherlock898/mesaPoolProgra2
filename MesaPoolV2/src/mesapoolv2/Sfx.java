package mesapoolv2;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Import, stores and play sounds
 * @author Si2
 */
public class Sfx {
    private Clip clip;
    
    private void setFile(String soundFileName){
        try{
            File file = new File(soundFileName);
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * Load and prepares a wav sound for playing it
     * @param soundName The name of the sound file with no extention
     */
    public Sfx(String soundName){
        setFile(".//sfx//" + soundName + ".wav");
    }
    
    /**
     * Plays the sound. Does nothing if the sound was already playing and hasn't reach a certain timestamp
     */
    public void play(){
        if(clip.isActive() && clip.getFramePosition() < clip.getFrameLength()/5 || !GameManager.start){
            return;
        }
        clip.setFramePosition(0);
        clip.start();
    }
}
