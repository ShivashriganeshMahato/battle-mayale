import mayflower.*;
import mayflower.Keyboard;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Mass;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Vector2;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.KeyEvent;


public class Character extends Actor
{
    public Vector2 center;
    private Mass mass;
    private int health;
    public int charX;
    public int charY;
    private guntest weapon;
    public Body charBod;
    private Rectangle rect;
    private BodyFixture charFix;
    private int ammo;
    public int fireSpeed;
    public boolean isAlive;
    public Keyboard keyListener;
    public PointerInfo mousePos;
    public Character(int x, int y) {
        center = new Vector2(x-x/2,y-y/2);
        weapon = new guntest();


        rect = new Rectangle(x,y);
        charFix = new BodyFixture(rect);
        charBod = new Body();
        charBod.addFixture(charFix);
        health = 100;
        isAlive = true;
        fireSpeed = 0;
        ammo = 0;
        mass = new Mass(center,1,0);
        charBod.setMass(mass);
        setPicture("src/Red_Dye_block.jpg");
    }

      //  charBod.setMass(mass);
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
    public guntest retGun()
    {
        return weapon;
    }
    public void update()
    {
        keyListener = getKeyboard();
        mousePos = MouseInfo.getPointerInfo();
        Point b = mousePos.getLocation();
        int gunX = (int)b.getX();
        int gunY = (int)b.getY();
        if(keyListener.isKeyPressed("W"))
        {

            weapon.move(1,"NORTH");
           move(1,"NORTH");
        }
      if(keyListener.isKeyPressed("S"))
       {
           weapon.move(1,"SOUTH");
           move(1,"SOUTH");

       }
        if(keyListener.isKeyPressed("A"))
        {
            weapon.move(1,"WEST");
            move(1,"WEST");

        }
        if(keyListener.isKeyPressed("D"))
        {
            weapon.move(1,"EAST");
            move(1,"EAST");

        }
    }
}






