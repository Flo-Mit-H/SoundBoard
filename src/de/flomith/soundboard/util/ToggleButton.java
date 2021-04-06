package de.flomith.soundboard.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("unused")
public class ToggleButton extends JToggleButton {

    private boolean triggered;
    private Color hoveredBackgroundColor = Color.DARK_GRAY.brighter();
    private Color pressedBackgroundColor = Color.LIGHT_GRAY.darker();
    private Color triggeredBackgroundColor = Color.CYAN;

    public ToggleButton() {
        this(null);
    }

    public ToggleButton(String text) {
        this(text, false);
    }

    public ToggleButton(String text, boolean triggered) {
        super(text);
        this.triggered = triggered;
        super.setContentAreaFilled(false);
        setBounds(0, 0, 150, 150);
        setBackground(Color.DARK_GRAY.brighter());
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setBorder(null);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                setTriggeredButton(!triggered);
                System.out.println(triggered);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {

        if(getModel().isPressed())
            g.setColor(pressedBackgroundColor);
        else if(getModel().isRollover())
            g.setColor(hoveredBackgroundColor);
        else
            g.setColor(getBackground());

        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    @Override
    public void setContentAreaFilled(boolean b) {

    }

    public Color getPressedBackgroundColor() {
        return pressedBackgroundColor;
    }

    public void setPressedBackgroundColor(Color pressedBackgroundColor) {
        this.pressedBackgroundColor = pressedBackgroundColor;
    }

    public Color getHoveredBackgroundColor() {
        return hoveredBackgroundColor;
    }

    public void setHoveredBackgroundColor(Color hoveredBackgroundColor) {
        this.hoveredBackgroundColor = hoveredBackgroundColor;
    }

    public Color getTriggeredBackgroundColor() {
        return triggeredBackgroundColor;
    }

    public void setTriggeredBackgroundColor(Color triggeredBackgroundColor) {
        this.triggeredBackgroundColor = triggeredBackgroundColor;
    }

    public boolean isTriggered() {
        return triggered;
    }

    public void setTriggeredButton(boolean triggered) {
        this.triggered = triggered;
        Color bg = triggered ? triggeredBackgroundColor : getBackground();
        setBackground(bg);
    }

}
