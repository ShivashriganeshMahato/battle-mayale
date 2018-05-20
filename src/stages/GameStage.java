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
import java.util.ConcurrentModificationException;

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
        if (user.didJustMove())
            game.sendCommand("move " + user.getAbsX() + " " + user.getAbsY());
        double dAx = -(user.getAbsX() - 400);
        double dAy = -(user.getAbsY() - 300);
        if (bulletCount < game.getBullets().size()) {
            Bullet newBullet = game.getBullets().get(game.getBullets().size() - 1);
            addActor(newBullet, (int) (newBullet.getAbsPos().getX() + dAx), (int) (newBullet.getAbsPos().getY() + dAy));
        }
        if (user.getWeapon().getMsgToSend() != null) {
            System.out.println(user.getWeapon().getMsgToSend());
            game.sendCommand(user.getWeapon().getMsgToSend());
            user.getWeapon().clearMsg();
        }
        try {
            for (Player player : game.getPlayers()) {
                double ax = player.getAbsX();
                double ay = player.getAbsY();
                player.setPosition(ax + dAx, ay + dAy);
                player.getTag().setPosition(ax + dAx - 30, ay + dAy - 65);
                player.getWeapon().setPosition(ax + dAx, ay + dAy);
            }
            for (Bullet bullet : game.getBullets()) {
                double ax = bullet.getAbsPos().getX();
                double ay = bullet.getAbsPos().getY();
                bullet.setPosition(ax + dAx, ay + dAy);
            }
            map.setPosition(dAx, dAy);
        } catch (ConcurrentModificationException e) {
            System.out.println("Java is bad");
        }
    }
}
