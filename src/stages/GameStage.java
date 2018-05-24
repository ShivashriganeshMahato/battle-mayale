package stages;

import entities.PickupGun;
import entities.Player;
import entities.Storm;
import entities.Tree;
import game.Game;
import mayflower.Stage;
import mayflower.Text;
import weapons.Bullet;

import java.awt.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

/**
 * @author Shivashriganesh Mahato
 */
public class GameStage extends Stage {
    private int userID;
    private Game game;
    private Player user;
    private int bulletCount;
    private Storm[] storm;
    private int stormCounter;

    private Text gunLabel;
    private Text ammoLabel;

    public GameStage(Game game, int userID) {
        this.game = game;
        this.userID = userID;

        setBackgroundColor(new Color(19, 54, 84));
        addActor(game.getMap(), 4000, 3000);

        for (Player player : game.getPlayers()) {
            if (player.getId() != userID) {
                player.setCanMove(false);
            } else {
                user = player;
            }
            addActor(player, player.getX(), player.getY());
            addActor(player.getTag(), player.getX(), player.getY() - 10);
            addActor(player.getWeapon(), player.getX(), player.getY());
            addActor(player.getHealthTag(), player.getX(), player.getY() - 10);
        }
        addActor(user, 5, 5);

        for (Tree tree : game.getTrees()) {
            addActor(tree, (int) tree.getAbsX(), (int) tree.getAbsY());
        }

        for (PickupGun gun : game.getGuns()) {
            addActor(gun, (int) gun.getAbsX(), (int) gun.getAbsY());
        }

        bulletCount = 0;

        gunLabel = new Text("", Color.WHITE);
        ammoLabel = new Text("", Color.WHITE);
        addActor(gunLabel, 20, 20);
        addActor(ammoLabel, 20, 50);

        storm = new Storm[] {
                new Storm(4000, -4000, 0, 2, game.getPlayers()),
                new Storm(-4000, 3000, 8/3, 0, game.getPlayers()),
                new Storm(12000, 3000, -8/3, 0, game.getPlayers()),
                new Storm(4000, 10000, 0, -2, game.getPlayers())
        };
        for (Storm _storm : storm) {
            addActor(_storm, (int) _storm.getAX(), (int) _storm.getAY());
        }
        stormCounter = 0;
    }

    @Override
    public void update() {
        stormCounter++;
        if (stormCounter % 1000 == 0) {
            if ((stormCounter / 1000) % 5 == 1) {
                for (Storm _storm : storm) {
                    _storm.getVelocity().scale(0.75);
                    _storm.start();
                }
            } else {
                for (Storm _storm : storm)
                    _storm.end();
            }
        }
        if (!user.isStillAlive()) {
            game.sendCommand("removePlayer" + " " + userID);
        }
        if (user.didJustMove())
            game.sendCommand("move " + user.getAbsX() + " " + user.getAbsY());
        double dAx = -(user.getAbsX() - 400);
        double dAy = -(user.getAbsY() - 300);
        if (bulletCount < game.getBullets().size()) {
            Bullet newBullet = game.getBullets().get(game.getBullets().size() - 1);
            addActor(newBullet, (int) (newBullet.getAbsPos().getX() + dAx), (int) (newBullet.getAbsPos().getY() + dAy));
        }
        if (user.getWeapon().getMsgToSend() != null) {
            game.sendCommand(user.getWeapon().getMsgToSend());
            user.getWeapon().clearMsg();
        }
        try {
            for (Player player : game.getPlayers()) {
                double ax = player.getAbsX();
                double ay = player.getAbsY();
                player.setPosition(ax + dAx, ay + dAy);
                player.getTag().setPosition(ax + dAx - 25, ay + dAy - 75);
                player.getHealthTag().setPosition(ax + dAx - 30, ay + dAy - 95);
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
            for (PickupGun gun : game.getGuns()) {
                double ax = gun.getAbsX();
                double ay = gun.getAbsY();
                gun.setPosition(ax + dAx, ay + dAy);
            }
            for (Storm _storm : storm) {
                double ax = _storm.getAX();
                double ay = _storm.getAY();
                _storm.setPosition(ax + dAx, ay + dAy);
            }
            game.getMap().setPosition(game.getMap().getAX() + dAx, game.getMap().getAY() + dAy);
        } catch (ConcurrentModificationException e) {
            System.out.println("Java is bad");
        }

        gunLabel.setText("Gun: " + user.getWeapon().getInfo());
        ammoLabel.setText("Ammo: " + user.getWeapon().getAmmoInfo());
    }

    public int getUserID() {
        return userID;
    }
}
