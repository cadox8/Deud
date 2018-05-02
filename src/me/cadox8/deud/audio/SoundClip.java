package me.cadox8.deud.audio;

import me.cadox8.deud.utils.Log;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SoundClip {

    private final String PATH = "/sounds/";
    private Clip clip = null;
    private FloatControl gainControl;

    private Sound sound;

    public SoundClip(Sound sound) {
        this.sound = sound;
        if (sound == Sound.NONE) return;

        try {
            InputStream audioSrc = SoundClip.class.getResourceAsStream(PATH + sound.getName() + ".wav");
            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream ais = AudioSystem.getAudioInputStream(bufferedIn);
            AudioFormat baseFormat = ais.getFormat();
            AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
            AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);

            clip = AudioSystem.getClip();
            clip.open(dais);

            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            Log.log(Log.LogType.DANGER, "Sound Error");
        }
    }

    public void play() {
        if (clip == null) return;

        stop();
        clip.setFramePosition(0);
        setVolume(sound.getVolume());
        while (isRunning()) clip.start();
    }

    private void stop() {
        if (isRunning()) clip.stop();
    }

    public void close() {
        stop();
        clip.drain();
        clip.close();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        play();
    }

    public void setVolume(float volume) {
        gainControl.setValue(volume);
    }
    public boolean isRunning() {
        return clip.isRunning();
    }
}
