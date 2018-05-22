package stages;

import mayflower.Stage;
import mayflower.Text;

import java.awt.*;

/**
 * @author Shivashriganesh Mahato
 */
public class GameOverStage extends Stage {
    public GameOverStage(int place) {
        setBackgroundColor(Color.RED);
        Text text = new Text("GAME OVER", Color.WHITE);
        Text text2 = new Text("You placed #" + place, Color.WHITE);
        addActor(text, 400 - text.getWidth() / 2, 285 - text.getHeight() / 2);
        addActor(text2, 400 - text2.getWidth() / 2, 315 - text2.getHeight() / 2);
    }

    @Override
    public void update() {

    }
}