package weapons;

import mayflower.Actor;

public class Bullet extends Actor
{
    int speed;

    public Bullet(int bs)
    {
        speed = bs;
        this.setPicture("src/smallMayrio.png");
    }

    @Override
    public void update() {

    }
}
