package entities;

import mayflower.Actor;
import util.Vector2;

import java.util.List;

/**
 * @author Shivashriganesh Mahato
 */
public class Tree extends Actor {
    private List<Player> players;
    private Vector2 absPos;

    public Tree(double x, double y, List<Player> players) {
        absPos = new Vector2(x, y);
        setPicture("images/tree.png");
        this.players = players;
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
    }

    public double getAbsX() {
        return absPos.getX();
    }

    public double getAbsY() {
        return absPos.getY();
    }
}
