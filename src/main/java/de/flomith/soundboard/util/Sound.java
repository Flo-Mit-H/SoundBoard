package de.flomith.soundboard.util;

import de.flomith.soundboard.SoundBoard;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

@SuppressWarnings("unused")
public class Sound {

    private String name, path;
    private final Button button;

    public Sound(String name, String path) {
        this.name = name;
        this.path = path;
        this.button = new Button(name);
        this.button.setBounds(100, (SoundBoard.soundsCount * 100 + 10), (Data.screenWidth - 200), 200);
        this.button.setBounds(10, 10, 100, 100);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                play();
            }
        });
    }

    public void play() {
        new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
                clip.open(inputStream);
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public Button getButton() {
        return button;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
