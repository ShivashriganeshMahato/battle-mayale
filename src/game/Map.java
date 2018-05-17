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

    public void setX(int x) {
        this.setPosition(x, this.getY());
    }

    public void setY(int y) {
        this.setPosition(this.getX(), y);
    }

    public void move(int dx, int dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }
}
