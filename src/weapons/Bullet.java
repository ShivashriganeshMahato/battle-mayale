package weapons;

import mayflower.Actor;
import util.Vector2;

public class Bullet extends Actor
{
    private Vector2 velocity;
    private Vector2 absPos;

    public Bullet(double x, double y, double vx, double vy)
    {
        velocity = new Vector2(vx, vy);
        absPos = new Vector2(x, y);
        this.setPicture("src/smallMayrio.png");
    }

    @Override
    public void update() {
//        this.setPosition(getX() + velocity.getX(), getY() + velocity.getY());
        absPos.add(velocity);
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

    public Vector2 getAbsPos() {
        return absPos;
    }

}
