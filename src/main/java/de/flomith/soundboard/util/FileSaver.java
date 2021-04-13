package de.flomith.soundboard.util;

import de.flomith.soundboard.SoundBoard;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileSaver {

    public static final File saves = new File("rsc//saves");

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void save(Sound sound) throws IOException {

        File file = new File("rsc//saves", sound.getName() + ".txt");
        if(!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
            file.getParentFile().mkdir();
        }

        if(!file.exists())
            file.createNewFile();

        FileWriter writer = new FileWriter(file);
        writer.write(sound.getPath());
        writer.flush();
        writer.close();

    }

    @SuppressWarnings("ConstantConditions")
    public static void load() {

        for(File fileEntry : saves.listFiles()) {
            String name = fileEntry.getName().substring(0, fileEntry.getName().length()-4);
            String path = "";
            try {
                Scanner reader = new Scanner(fileEntry);
                while (reader.hasNextLine())  {
                    path = reader.nextLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            SoundBoard.addSound(path, name);
        }

    }

}
