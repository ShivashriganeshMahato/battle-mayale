package stages;

import game.Game;
import mayflower.Stage;
import player.Player;

import java.awt.*;

/**
 * @author Shivashriganesh Mahato
 */
public class GameStage extends Stage {
    private int userID;
    private Game game;

    public GameStage(Game game, int userID) {
        this.game = game;
        this.userID = userID;
        setBackgroundColor(Color.RED);

        for (Player player : game.getPlayers()) {
            if (player.getId() != userID)
                player.setCanMove(false);
            addActor(player, player.getX(), player.getY());
        }
    }

    @Override
    public void update() {

    }
}
