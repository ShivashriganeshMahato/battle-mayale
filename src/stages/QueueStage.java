package stages;

import mayflower.Stage;
import mayflower.Text;

import java.awt.*;

/**
 * @author Shivashriganesh Mahato
 */
public class QueueStage extends Stage {
    public QueueStage() {
        setBackgroundColor(Color.WHITE);
        Text text = new Text("WAITING FOR GAME", Color.BLACK);
        addActor(text, 400 - text.getWidth() / 2, 300 - text.getHeight() / 2);
    }

    @Override
    public void update() {

    }
}
