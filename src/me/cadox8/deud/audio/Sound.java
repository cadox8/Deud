package me.cadox8.deud.audio;

import me.cadox8.deud.utils.Log;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.BufferedInputStream;

public class Sound {

    public static final Sound ENTITY_WALK_GRASS = new Sound("entity_walk_grass", -15f);

    public static final Sound TOWN_MUSIC = new Sound("town", -35f);

    private Clip clip;

    public Sound(String sound, float value) {
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new BufferedInputStream(getClass().getResourceAsStream("/sounds/" + sound + ".wav"))));
            final FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(value);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void play() {
        try {
            clip.start();
            if (!clip.isRunning()) clip.setFramePosition(0);
        } catch(Exception e) {
            Log.danger(e.getCause());
        }
    }

    public void playLoop() {
        try {
            clip.start();
            if (hasFinished()) clip.start();
        } catch(Exception e) {
            Log.danger(e.getCause());
        }
    }

    public void pause() {
        if(clip.isOpen()){
            clip.stop();
        } else {
            play();
        }
    }

    public void resume() {
        if(clip.isOpen()){
            clip.start();
        } else {
            play();
        }
    }

    public void stop() {
        if(!clip.isOpen()) return;

        clip.stop();
        clip.close();
    }

    public boolean hasFinished() {
        if (clip == null) return false;
        return clip.getFramePosition() == clip.getFrameLength();
    }
}
