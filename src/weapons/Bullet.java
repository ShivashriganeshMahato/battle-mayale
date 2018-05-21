package weapons;

import mayflower.Actor;
import util.Vector2;

public class Bullet extends Actor
{
    private Vector2 velocity;
    private Vector2 absPos;
    private boolean isDead;

    public Bullet(double x, double y, double vx, double vy)
    {
        velocity = new Vector2(vx, vy);
        absPos = new Vector2(x, y);
        this.setPicture("images/smallMayrio.png");
        isDead = false;
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
}
