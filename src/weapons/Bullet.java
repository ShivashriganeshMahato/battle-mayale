package weapons;

import mayflower.Actor;
import util.Vector2;

public class Bullet extends Actor
{
    private Vector2 velocity;

    public Bullet(int bs)
    {
        velocity = new Vector2(bs, bs);
        this.setPicture("src/smallMayrio.png");
    }

    @Override
    public void update() {
        this.setPosition(getX() + velocity.getX(), getY() + velocity.getY());
    }
}
