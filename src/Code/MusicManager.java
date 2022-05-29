package Code;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import static Code.TestApp.song;

public class MusicManager {
    private URL url;
    private Clip clip;

    private final String soundsPath = "src/Utils/Sounds/";

    public MusicManager(String fileName) {
        try {
            this.url = new File(soundsPath + fileName).toURI().toURL();
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            this.clip = AudioSystem.getClip();
            this.clip.open(audioIn);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play(){
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            this.clip = AudioSystem.getClip();
            this.clip.open(audioIn);
            this.clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public float getVolume() {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        return (float) Math.pow(10f, gainControl.getValue() / 20f);
    }

    public void setVolume(float volume) {
        if (volume < 0f || volume > 1f)
            throw new IllegalArgumentException("Volume not valid: " + volume);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
    }

    public Clip getClip() {
        return clip;
    }

    public void increaseVolumeGradually(){
        Thread1 t1 = new Thread1();
        t1.start();
    }

    public void decreaseVolumeGradually(){
        Thread2 t2 = new Thread2();
        t2.start();
    }


    public void playFrom0() {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            this.clip = AudioSystem.getClip();
            this.clip.open(audioIn);
            this.setVolume(0.01F);
            this.clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}

class Thread1 extends Thread{
    @Override
    public void run() {
        if(song.getClip().isActive()) {
            while (song.getVolume() < 0.1F) {
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                song.setVolume(song.getVolume() * 2);
            }
        }
        else{
            song.playFrom0();
            song.increaseVolumeGradually();
        }
    }
}

class Thread2 extends Thread{
    @Override
    public void run() {
        while(song.getVolume() > 0.05F){
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            song.setVolume(song.getVolume()/2);
        }
    }
}