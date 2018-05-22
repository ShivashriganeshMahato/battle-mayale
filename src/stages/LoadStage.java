package stages;

import client.ClientInterface;
import client.ClientManager;
import game.Game;
import mayflower.Stage;
import mayflower.Text;

import java.awt.*;

/**
 * @author Shivashriganesh Mahato
 */
public class LoadStage extends Stage {
    private ClientInterface ci;
    private Game game;
    private int userID;
    private int counter;

    public LoadStage(Game game, int userID, ClientInterface ci) {
        this.ci = ci;
        this.game = game;
        this.userID = userID;
        counter = 0;

        setBackgroundColor(Color.pink);
        Text text = new Text("PLEASE WAIT FOR MAP TO LOAD...", Color.BLACK);
        addActor(text, 400 - text.getWidth() / 2, 300 - text.getHeight() / 2);
    }

    @Override
    public void update() {
    }

    public void goToPlay() {
        GameStage gStage = new GameStage(game, userID);
        ci.setStage(gStage);
    }
}
