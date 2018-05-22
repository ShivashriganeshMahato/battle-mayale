package weapons;

import entities.Player;
import mayflower.Actor;
import util.Vector2;

public class Bullet extends Actor {
    private Vector2 velocity;
    private Vector2 absPos;
    private Player player;
    private boolean isDead;

    public Bullet(double x, double y, double vx, double vy, Player a) {
        velocity = new Vector2(vx, vy);
        absPos = new Vector2(x, y);
        this.setPicture("images/smallMayrio.png");
        isDead = false;
        player = a;
    }

    @Override
    public void update() {
        absPos.add(velocity);
    }

    public void setX(double x) {
        this.setPosition(x, this.getY());
    }

    public void setY(double y) {
        this.setPosition(this.getX(), y);
    }

    public void relMove(double dx, double dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }

    public Vector2 getAbsPos() {
        return absPos;
    }

    public void kill() {
        isDead = true;
    }

    public boolean isDead() {
        return isDead;
    }

    public Player getPlayer() {
        return player;
    }
}
