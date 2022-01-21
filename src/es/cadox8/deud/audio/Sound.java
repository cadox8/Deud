package es.cadox8.deud.audio;

import lombok.Setter;
import es.cadox8.deud.api.GameAPI;
import es.cadox8.deud.utils.Log;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.BufferedInputStream;
import java.util.Objects;

public class Sound {

    private Clip clip;

    @Setter private static GameAPI gameAPI;

    private final float volume;

    public Sound(String sound, float value) {
        this.volume = value;
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new BufferedInputStream(Objects.requireNonNull(Sound.class.getResourceAsStream("/sounds/" + sound + ".wav")))));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void play() {
        try {
            this.setVolume();
            clip.start();
            if (!clip.isRunning()) {
                this.setVolume();
                clip.setFramePosition(0);
            }
        } catch(Exception e) {
            Log.danger(e.getMessage());
        }
    }

    public void playLoop() {
        try {
            this.setVolume();
            clip.start();
            if (this.hasFinished()) {
                this.setVolume();
                clip.start();
            }
        } catch(Exception e) {
            Log.danger(e.getMessage());
        }
    }

    public void pause() {
        if(clip.isOpen()){
            clip.stop();
        } else {
            this.play();
        }
    }

    public void resume() {
        if(clip.isOpen()){
            this.setVolume();
            clip.start();
        } else {
            this.play();
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

    public void setVolume() {
        final FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(volume - (volume * (1 - gameAPI.getConfig().getMasterVolume())));
    }
}
