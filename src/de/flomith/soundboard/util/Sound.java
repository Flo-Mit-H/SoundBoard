package de.flomith.soundboard.util;

public class Sound {

    private String name, path;
    private Button button;

    public Sound(String name, String path) {
        this.name = name;
        this.path = path;
        this.button = new Button(name);
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
