package weapons;

import mayflower.Actor;
import util.Vector2;

public class Bullet extends Actor
{
    private Vector2 velocity;
    private Vector2 absPos;

    public Bullet(int bs, int x, int y)
    {
        velocity = new Vector2(bs, bs);
        absPos = new Vector2(x, y);
        this.setPicture("src/smallMayrio.png");
    }

    @Override
    public void update() {
        this.setPosition(getX() + velocity.getX(), getY() + velocity.getY());
        absPos.add(velocity);
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
