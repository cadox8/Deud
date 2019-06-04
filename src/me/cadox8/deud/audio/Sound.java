package me.cadox8.deud.audio;

import me.cadox8.deud.utils.Log;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class Sound {

    private final String PATH = "/sounds/";
    private File file;
    private float volume;
    private Clip clip;

    public Sound(String sound, float volume) {
        try {
            this.file = new File(getClass().getResource(PATH + sound + ".wav").toURI());
            this.volume = volume;
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void playSound() {
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            final FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume);
            clip.start();
        } catch(Exception exc) {
            Log.log(Log.LogType.DANGER, exc.toString());
        }

    }

    public void pauseSound() {
        if(clip.isOpen()){
            clip.stop();
        } else {
            playSound();
        }
    }

    public void resumeSound() {
        if(clip.isOpen()){
            clip.start();
        } else {
            playSound();
        }
    }

    public void stopSound() {
        if(!clip.isOpen()) return;

        clip.stop();
        clip.close();
    }

    public boolean isSoundFinished() {
        return clip.getFramePosition() == clip.getFrameLength();
    }
}
