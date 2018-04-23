
import org.dyn4j.geometry.AABB;

import java.util.*;
import java.*;


public class Character
{

    private int health;
    private Object weapon;
    public AABB hitbox;
    private int ammo;
    public int fireSpeed
    public boolean isAlive;
    public Character()
    {
        health = 100;
        isAlive = true;
        fireSpeed = 0;
        ammo = 0;
        weapon = null;
        hitbox = new AABB(0,0,50,50)

    }

    public boolean isDead()
    {
        if(health <= 0)
        {
            isAlive = false;
        }
        return isAlive;
    }

    public boolean isTouching(Object obj)
    {

    }
    public void move()
    {

    }

}
