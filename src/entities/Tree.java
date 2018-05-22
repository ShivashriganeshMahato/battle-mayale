package entities;

import mayflower.Actor;
import util.Vector2;
import weapons.Bullet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shivashriganesh Mahato
 */
public class Tree extends Actor {
    private List<Player> players;
    private List<Bullet> bullets;
    private Vector2 absPos;

    public Tree(double x, double y, List<Player> players, List<Bullet> bullets) {
        absPos = new Vector2(x, y);
        setPicture("images/tree.png");
        this.players = players;
        this.bullets = bullets;
    }

    @Override
    public void update() {
        for (Player player : players) {
            if (player.getAbsX() + 100 >= getAbsX() &&
                    player.getAbsX() <= getAbsX() + 100 &&
                    player.getAbsY() + 100 >= getAbsY() &&
                    player.getAbsY() <= getAbsY() + 135) {
                if (player.getAbsX() + 100 < getAbsX() + 50)
                    player.setStopped(Player.Direction.RIGHT, true);
                else
                    player.setStopped(Player.Direction.LEFT, true);
                if (player.getAbsY() + 100 < getAbsY() + 50)
                    player.setStopped(Player.Direction.DOWN, true);
                else
                    player.setStopped(Player.Direction.UP, true);
            }
        }
        List<Bullet> toRemove = new ArrayList<>();
        for (Bullet bullet : bullets) {
            if (bullet.getAbsPos().getX() + 8 >= getAbsX() - 40 &&
                    bullet.getAbsPos().getX() - 8 <= getAbsX() + 40 &&
                    bullet.getAbsPos().getY() + 16 >= getAbsY() &&
                    bullet.getAbsPos().getY() <= getAbsY() + 135) {
                bullet.kill();
            }
        }
        bullets.removeAll(toRemove);
    }

    public double getAbsX() {
        return absPos.getX();
    }

    public double getAbsY() {
        return absPos.getY();
    }
}
