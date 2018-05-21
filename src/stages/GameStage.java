package stages;

import entities.Tree;
import game.Game;
import game.map.Cell;
import mayflower.Actor;
import mayflower.Stage;
import entities.Player;
import mayflower.Text;
import weapons.Bullet;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @author Shivashriganesh Mahato
 */
public class GameStage extends Stage {
    private int userID;
    private Game game;
    private Player user;
    private int bulletCount;

    private Text gunLabel;
    private Text ammoLabel;

    public GameStage(Game game, int userID) {
        this.game = game;
        this.userID = userID;

        setBackgroundColor(Color.GREEN);
        addActor(game.getMap(), 0, 0);

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

        for (Tree tree : game.getTrees()) {
            addActor(tree, (int) tree.getAbsX(), (int) tree.getAbsY());
        }

        bulletCount = 0;

        gunLabel = new Text("");
        ammoLabel = new Text("");
        addActor(gunLabel, 20, 20);
        addActor(ammoLabel, 20, 50);
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
            List<Bullet> toRemove = new ArrayList<>();
            for (Bullet bullet : game.getBullets()) {
                double ax = bullet.getAbsPos().getX();
                double ay = bullet.getAbsPos().getY();
                bullet.setPosition(ax + dAx, ay + dAy);
                if (bullet.isDead()) {
                    removeActor(bullet);
                    toRemove.add(bullet);
                }
            }
            game.removeBullets(toRemove);
            for (Tree tree : game.getTrees()) {
                double ax = tree.getAbsX();
                double ay = tree.getAbsY();
                tree.setPosition(ax + dAx, ay + dAy);
            }
            game.getMap().setPosition(game.getMap().getAX() + dAx, game.getMap().getAY() + dAy);
        } catch (ConcurrentModificationException e) {
            System.out.println("Java is bad");
        }

        gunLabel.setText("Gun: " + user.getWeapon().getInfo());
        ammoLabel.setText("Ammo: " + user.getWeapon().getAmmoInfo());
    }
}
