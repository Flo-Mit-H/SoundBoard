package de.flomith.soundboard.util;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("unused")
public class Button extends JButton {

    private Color hoverBackgroundColor;
    private Color pressedBackgroundColor;

    public Button() {
        this(null);
    }

    public Button(String text) {
        super(text);
        super.setContentAreaFilled(false);
        setBounds(0, 0, 150, 150);
        setBackground(Color.DARK_GRAY.brighter());
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setBorder(null);
        setHoverBackgroundColor(Color.DARK_GRAY.brighter().brighter());
    }

    @Override
    protected void paintComponent(Graphics g) {

        if(getModel().isPressed())
            g.setColor(pressedBackgroundColor);
        else if(getModel().isRollover())
            g.setColor(hoverBackgroundColor);
        else
            g.setColor(getBackground());

        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    @Override
    public void setContentAreaFilled(boolean b) {

    }

    public Color getHoverBackgroundColor() {
        return hoverBackgroundColor;
    }

    public void setHoverBackgroundColor(Color hoverBackgroundColor) {
        this.hoverBackgroundColor = hoverBackgroundColor;
    }

    public Color getPressedBackgroundColor() {
        return pressedBackgroundColor;
    }

    public void setPressedBackgroundColor(Color pressedBackgroundColor) {
        this.pressedBackgroundColor = pressedBackgroundColor;
    }

}
