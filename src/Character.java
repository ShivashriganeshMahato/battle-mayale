import mayflower.*;
import mayflower.Keyboard;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Mass;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Vector2;

import java.awt.event.KeyEvent;


public class Character
{
  //  public Vector2 center;
    //private Mass mass;

    private int health;
    public int charX;
    public int charY;
    private Object weapon;
    public Body charBod;
    private Rectangle rect;
    private BodyFixture charFix;
    private int ammo;
    public int fireSpeed;
    public boolean isAlive;
    public Character(int x, int y)
    {
        //center = new Vector2(x-x/2,y-y/2);
        rect = new Rectangle(x,y);
        charFix = new BodyFixture(rect);
        charBod = new Body();
        charBod.addFixture(charFix);
        health = 100;
        isAlive = true;
        fireSpeed = 0;
        ammo = 0;
        weapon = null;
        //mass = new Mass(center,10,10)
        charBod.setMass(MassType.NORMAL);
      //  charBod.setMass(mass);

    }
    public void setFireSpeed(int s)
    {
        fireSpeed = s;
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

        return false;
    }
    public void move()
    {

    }
    public void update()
    {
        //Keyboard kb = getKeyboard();
    }

    public char keyPressed(KeyEvent e)
    {
        return e.getKeyChar();
    }
    public boolean keyStillPressed(KeyEvent e, char ogKey)
    {
        return e.getKeyChar() == ogKey;
    }
    public boolean keyReleased(KeyEvent e, char ogKey)
    {
        return e.getKeyChar() !=ogKey;
    }




}
