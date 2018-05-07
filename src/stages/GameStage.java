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
    private Player user;

    public GameStage(Game game, int userID) {
        this.game = game;
        this.userID = userID;

        setBackgroundColor(Color.RED);

        for (Player player : game.getPlayers()) {
            if (player.getId() != userID)
                player.setCanMove(false);
            else
                user = player;
            addActor(player, player.getX(), player.getY());
        }
    }

    @Override
    public void update() {
        if (user.didJustMove())
            game.sendCommand("move " + user.getX() + " " + user.getY());
        for (Player player : game.getPlayers()) {
            player.move(-(user.getX() - 400), -(user.getY() - 300));
        }
    }
}
