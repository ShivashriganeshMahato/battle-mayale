package ui;

import mayflower.Text;

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

    public boolean isDisabled() {
        return isDisabled;
    }
}
