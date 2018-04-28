package ui;

import mayflower.Actor;
import mayflower.Text;

import javax.swing.*;
import java.awt.*;

/**
 * @author Shivashriganesh Mahato
 */
public class Button extends Text {
    private boolean isDisabled;
    private String text;

    public Button(String text) {
        this.text = text;
        isDisabled = false;
        setText(text);
    }

    @Override
    public void update() {
        if (isClicked() && !isDisabled) {
            JOptionPane.showMessageDialog(null, "START GAME");
        }
    }

    public void setBtnText(String text) {
        this.text = text;
        setText(text);
    }

    public void disable() {
        setColor(Color.WHITE);
        isDisabled = true;
    }

    public void enable() {
        setColor(Color.BLACK);
        isDisabled = false;
    }
}
