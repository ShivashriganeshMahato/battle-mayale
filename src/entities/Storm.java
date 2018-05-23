package entities;

import mayflower.Actor;
import util.Vector2;

/**
 * @author Shivashriganesh Mahato
 */
public class Storm extends Actor {
    private boolean flag;
    private Vector2 absPos;
    private Vector2 velocity;

    public Storm(double x, double y, double vx, double vy) {
        setPicture("images/storm.png");
        absPos = new Vector2(x, y);
        velocity = new Vector2(vx, vy);
        flag = false;
    }

    @Override
    public void update() {
        if (flag)
            absPos.add(velocity);
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
