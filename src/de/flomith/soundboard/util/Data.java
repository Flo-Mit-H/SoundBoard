package de.flomith.soundboard.util;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Data {

    public static int screenWidth = 800, screenHeight = 600;
    public static int posX = (Toolkit.getDefaultToolkit().getScreenSize().width - screenWidth) / 2
            , posY = (Toolkit.getDefaultToolkit().getScreenSize().height - screenHeight) / 2;
    public static List<Button> sounds = new ArrayList<>();

}
