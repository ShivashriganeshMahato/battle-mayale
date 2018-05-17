package stages;

import game.Game;
import game.Map;
import mayflower.Actor;
import mayflower.Picture;
import mayflower.Stage;
import mayflower.Text;
import player.Player;
import util.Vector2;
import weapons.Bullet;
import weapons.SMG;

import java.awt.*;

/**
 * @author Shivashriganesh Mahato
 */
public class GameStage extends Stage {
    private int userID;
    private Game game;
    private Player user;
    private Map map;
    private int bulletCount;

    public GameStage(Game game, int userID) {
        this.game = game;
        this.userID = userID;

        setBackgroundColor(Color.GREEN);

        for (Player player : game.getPlayers()) {
            if (player.getId() != userID) {
                player.setCanMove(false);
            } else {
                user = player;
            }
            addActor(player, player.getX(), player.getY());
            addActor(player.getTag(), player.getX(), player.getY() - 10);
            addActor(player.getWeapon(), player.getX(), player.getY());
        }
        addActor(user, 5, 5);

        map = new Map();
        addActor(map, 0, 0);

        bulletCount = 0;
    }

    @Override
    public void update() {
//        if (bulletCount < game.getBullets().size()) {
//            Bullet newBullet = game.getBullets().remove(game.getBullets().size() - 1);
//            addActor(newBullet, newBullet.getX(), newBullet.getY());
//        }
        if (user.didJustMove())
            game.sendCommand("move " + user.getAbsX() + " " + user.getAbsY());
        if (user.getWeapon().getMsgToSend() != null) {
            System.out.println(user.getWeapon().getMsgToSend());
            game.sendCommand(user.getWeapon().getMsgToSend());
            user.getWeapon().clearMsg();
        }
        int dx = -(user.getX() - 400);
        int dy = -(user.getY() - 300);
        for (Player player : game.getPlayers()) {
            player.move(dx, dy);
            player.getTag().setPosition(player.getX() - 30, player.getY() - 65);
            player.getWeapon().setPosition(player.getX(), player.getY());
        }
//        for (Bullet bullet : game.getBullets()) {
//            bullet.move(dx, dy);
//        }
//        map.move(dx, dy);
    }
}
