package game;

import mayflower.Actor;
import util.Vector2;

/**
 * @author Shivashriganesh Mahato
 */
public class Map extends Actor {
    private Vector2 absPos;

    public Map(double x, double y, double w, double h) {
        setPicture("images/gudmap_small.jpg");
        absPos = new Vector2(x, y);
    }

    @Override
    public void update() {

    }

    public double getAX() {
        return absPos.getX();
    }

    public double getAY() {
        return absPos.getY();
    }

    public void setX(double x) {
        this.setPosition(x, this.getY());
    }

    public void setY(double y) {
        this.setPosition(this.getX(), y);
    }

    public void move(double dx, double dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }
}
