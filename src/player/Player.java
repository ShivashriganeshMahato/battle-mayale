package player;

import mayflower.Actor;
import mayflower.Keyboard;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Mass;
import org.dyn4j.geometry.Rectangle;
import util.Vector2;

import java.awt.*;

/**
 * @author Shivashriganesh Mahato
 */
public class Player extends Actor
{
    private Vector2 position;
    private String name;
    private int id;
    public org.dyn4j.geometry.Vector2 center;
    private Mass mass;
    private int health;
    public int charX;
    public int charY;

    public Body charBod;
    private Rectangle rect;
    private BodyFixture charFix;
    private int ammo;
    public int fireSpeed;
    public boolean isAlive;
    public Keyboard keyListener;
    public PointerInfo mousePos;
    public Player(String name, int id) {
        this.name = name;
        this.id = id;
        //center = new org.dyn4j.geometry.Vector2(x-x/2,y-y/2);




        //charFix = new BodyFixture(rect);
       //// charBod = new Body();
       // charBod.addFixture(charFix);
        health = 100;
        isAlive = true;
        fireSpeed = 0;
        ammo = 0;
        //mass = new Mass(center,1,0);
        //charBod.setMass(mass);
        setPicture("src/RAWR.jpg");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return (int)position.getX();
    }

    public int getY() {
        return (int)position.getY();
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setX(double x) {
        position.setX(x);
    }

    public void setY(double y) {
        position.setY(y);
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

            //weapon.move(1,"NORTH");
            move(1,"NORTH");
        }
        if(keyListener.isKeyPressed("S"))
        {
           // weapon.move(1,"SOUTH");
            move(1,"SOUTH");

        }
        if(keyListener.isKeyPressed("A"))
        {
            //weapon.move(1,"WEST");
            move(1,"WEST");

        }
        if(keyListener.isKeyPressed("D"))
        {
            //weapon.move(1,"EAST");
            move(1,"EAST");

        }
    }
}
