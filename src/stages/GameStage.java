package stages;

import game.Game;
import mayflower.Stage;
import mayflower.Text;
import player.Player;
import util.Vector2;

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
            if (player.getId() != userID) {
                player.setCanMove(false);
            } else {
                user = player;
            }
            addActor(player, player.getX(), player.getY());
            addActor(player.getTag(), player.getX(), player.getY() - 10);
        }
        addActor(user, 5, 5);
    }

    @Override
    public void update() {
        if (user.didJustMove())
            game.sendCommand("move " + user.getAbsX() + " " + user.getAbsY());
        int dx = -(user.getX() - 400);
        int dy = -(user.getY() - 300);
        for (Player player : game.getPlayers()) {
            player.move(dx, dy);
            player.getTag().setPosition(player.getX() - 30, player.getY() - 65);
        }
        System.out.println(user.getAbsPos());
    }
}
