package de.flomith.soundboard;

import de.flomith.soundboard.util.Data;
import de.flomith.soundboard.util.Button;
import de.flomith.soundboard.util.ToggleButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SoundBoard {

    private static JFrame jf;
    private static JPanel panel;

    public static void main(String[] args) {
        initWindow();
        updateWindow();
    }

    private static void initWindow() {
        jf = new JFrame("SoundBoard by Flo Mit H and deppelopfer");
        jf.setSize(Data.screenWidth, Data.screenHeight);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLayout(null);
        jf.setLocation(Data.posX, Data.posY);
        jf.setAutoRequestFocus(true);

        panel = new JPanel();
        panel.setSize(Data.screenWidth, Data.screenHeight);
        panel.setLocation(0, 0);
        panel.setLayout(null);
        panel.setBackground(Color.DARK_GRAY);
        jf.add(panel);

        Button addButton = new Button("+");
        Font font = new Font("Roboto", Font.BOLD, 27);
        addButton.setFont(font);
        addButton.setBounds(15, 15, 30, 30);
        addButton.setForeground(Color.GREEN);
        addButton.setPressedBackgroundColor(Color.LIGHT_GRAY.darker());
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

        jf.setVisible(true);
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private static void updateWindow() {
        while (true) {
            Data.screenWidth = jf.getWidth();
            Data.screenHeight = jf.getHeight();
            Data.posX = jf.getLocation().x;
            Data.posY = jf.getLocation().y;
            panel.setSize(Data.screenWidth, Data.screenHeight);
        }
    }

}
