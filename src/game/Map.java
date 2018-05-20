package game;

import mayflower.Actor;

/**
 * @author Shivashriganesh Mahato
 */
public class Map extends Actor {
    public Map() {
        setPicture("src/download.jpg");
    }

    @Override
    public void update() {

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
