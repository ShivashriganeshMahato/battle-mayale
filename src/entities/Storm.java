package entities;

import mayflower.Actor;
import util.Vector2;

import java.util.List;

/**
 * @author Shivashriganesh Mahato
 */
public class Storm extends Actor {
    private boolean flag;
    private Vector2 absPos;
    private Vector2 velocity;
    private List<Player> players;

    public Storm(double x, double y, double vx, double vy, List<Player> players) {
        setPicture("images/storm.png");
        absPos = new Vector2(x, y);
        velocity = new Vector2(vx, vy);
        flag = false;
        this.players = players;
    }

    @Override
    public void update() {
        if (flag)
            absPos.add(velocity);
        for (Player player : players)
            if (player.isTouching(this))
                player.hurt(1);
    }

    public Vector2 getAbsPos() {
        return absPos;
    }

    public double getAX() {
        return absPos.getX();
    }

    public double getAY() {
        return absPos.getY();
    }

    public void start() {
        flag = true;
    }

    public void end() {
        flag = false;
    }
}
