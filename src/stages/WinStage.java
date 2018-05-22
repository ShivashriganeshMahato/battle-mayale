package stages;

import mayflower.Stage;
import mayflower.Text;

import java.awt.*;

/**
 * @author Shivashriganesh Mahato
 */
public class WinStage extends Stage {
    public WinStage() {
        setBackgroundColor(new Color(30, 96, 150));
        Text text = new Text("#1  Victory Royale!", Color.WHITE);
        addActor(text, 400 - text.getWidth() / 2, 300 - text.getHeight() / 2);
    }

    @Override
    public void update() {

    }
}
