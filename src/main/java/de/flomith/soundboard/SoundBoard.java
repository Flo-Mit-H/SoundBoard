package de.flomith.soundboard;

import de.flomith.soundboard.util.*;
import de.flomith.soundboard.util.Button;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SoundBoard {

    private static JFrame jf;
    public static JPanel panel, soundPanel;
    public static JScrollPane scrollPane;
    public static int rowCount = 0, soundsCount;
    public static List<Sound> sounds = new ArrayList<>();

    public static void main(String[] args) {
        initWindow();
        updateWindow();
        FileSaver.load();
    }

    private static void initWindow() {
        jf = new JFrame("SoundBoard by Flo Mit H and deppelopfer");
        jf.setSize(Data.screenWidth, Data.screenHeight);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLayout(null);
        jf.setLocation(Data.posX, Data.posY);
        jf.setAutoRequestFocus(true);
        jf.setResizable(false);

        panel = new JPanel();
        panel.setBounds(0, 0, Data.screenWidth, Data.screenHeight);
        panel.setLayout(null);
        panel.setBackground(Color.DARK_GRAY);
        jf.getContentPane().add(panel);

        Button addButton = new Button("+");
        Font font = new Font("Roboto", Font.BOLD, 27);
        addButton.setFont(font);
        addButton.setBounds(15, 15, 30, 30);
        addButton.setForeground(Color.GREEN);
        addButton.setPressedBackgroundColor(Color.LIGHT_GRAY.darker());
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                openSoundAddMenu();
            }
        });
        addButton.setVisible(true);
        panel.add(addButton);

        ToggleButton alwaysOnTopButton = new ToggleButton();
        ImageIcon aat = new ImageIcon("rsc/aat.png");
        aat.setDescription("Always on Top");
        alwaysOnTopButton.setDisabledIcon(aat);
        alwaysOnTopButton.setIcon(aat);
        alwaysOnTopButton.setBounds(50, 15, aat.getIconWidth(), aat.getIconHeight());
        alwaysOnTopButton.setBackgroundColor(Color.WHITE);
        alwaysOnTopButton.setHoveredBackgroundColor(Color.WHITE);
        alwaysOnTopButton.setPressedBackgroundColor(Color.CYAN);
        alwaysOnTopButton.setTriggeredBackgroundColor(Color.CYAN);
        alwaysOnTopButton.setTriggeredButton(false);
        alwaysOnTopButton.setVisible(true);
        alwaysOnTopButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                jf.setAlwaysOnTop(alwaysOnTopButton.isTriggered());
            }
        });
        panel.add(alwaysOnTopButton);

        soundPanel = new JPanel();
        soundPanel.setLayout(null);
        soundPanel.setBackground(Color.DARK_GRAY);

        scrollPane = new JScrollPane(soundPanel);

        soundPanel.setBounds(0, 0, soundPanel.getParent().getWidth(), soundPanel.getParent().getHeight());
        soundPanel.setPreferredSize(new Dimension(soundPanel.getParent().getWidth(), soundPanel.getParent().getHeight()));

        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        jf.getContentPane().add(scrollPane);

        updateWindow();

        jf.setVisible(true);
    }

    public static void updateWindow() {

        panel.revalidate();
        panel.repaint();
        soundPanel.revalidate();
        soundPanel.repaint();
        jf.remove(scrollPane);
        scrollPane = new JScrollPane(soundPanel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setWheelScrollingEnabled(true);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(0, 50, Data.screenWidth - 50, Data.screenHeight - 100);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        jf.getContentPane().add(scrollPane);

    }

    public static void addSound(String path, String name) {
        Sound s = new Sound(name, path);
        sounds.add(s);
        s.getButton().setSize(250, 100);
        if(soundsCount%2==0) {
            //Ungerade
            if(soundsCount == 0)
                s.getButton().setLocation(100, 50);
            else {
                s.getButton().setLocation(100, (((rowCount) * 100) + 50));
                s.getButton().setLocation(s.getButton().getX(), s.getButton().getY() + 50);
                s.getButton().setLocation(s.getButton().getX(), (sounds.get(soundsCount - 1).getButton().getY() + sounds.get(soundsCount - 1).getButton().getHeight()) + 50);
            }
        }else {
            //Gerade
            rowCount++;
            System.out.println(sounds.get(soundsCount-1).getButton().getY());
            s.getButton().setLocation(Data.screenWidth - (s.getButton().getWidth() + 100), sounds.get(soundsCount-1).getButton().getY());
        }
        soundsCount++;
        try {
            FileSaver.save(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        soundPanel.add(s.getButton());
        int panelSize = soundsCount * 100 + soundsCount * 50;
        soundPanel.setSize(soundPanel.getWidth(), panelSize + 100);
        soundPanel.setPreferredSize(new Dimension(soundPanel.getWidth(), panelSize + 100));
        updateWindow();

        new Timer().schedule(new TimerTask() {@Override public void run() {soundPanel.revalidate();soundPanel.repaint();}}, 1);

    }

    public static void openSoundAddMenu() {

        boolean aat = jf.isAlwaysOnTop();
        jf.setAlwaysOnTop(false);
        String name = JOptionPane.showInputDialog("Name des Sounds:");
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("AUDIO FILES", "wav");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);
        if(result == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getPath();
            if(name == null || name.equals("") || name.equals(" "))
                name = fileChooser.getSelectedFile().getName();
            addSound(path, name);
        }
        jf.setAlwaysOnTop(aat);

    }

}
