package player;

import mayflower.Actor;
import mayflower.Keyboard;
import mayflower.Text;
import util.Vector2;
import weapons.Pistol;
import weapons.SMG;
import weapons.Weapon;

import java.awt.*;
import java.util.ConcurrentModificationException;

/**
 * @author Shivashriganesh Mahato
 */
public class Player extends Actor {
    private String name;
    private int id;
    private int health;
    private int ammo;
    private int fireSpeed;
    private boolean isAlive;
    private Keyboard keyListener;
    private PointerInfo mousePos;
    private Text tag;
    private boolean canMove;
    private boolean didJustMove;
    private Vector2 absPos;
    private Weapon weapon;

    public Player(String name, int id, int x, int y, boolean canMove) {
        this.name = name;
        this.id = id;
        this.canMove = canMove;
        didJustMove = false;
        health = 100;
        isAlive = true;
        fireSpeed = 0;
        ammo = 0;
        setPicture("src/RAWR.jpg");
        setPosition(x, y);
        tag = new Text(name, Color.WHITE);
        absPos = new Vector2(x, y);
        weapon = new Pistol();
    }

    public Player(String name, int id, int x, int y) {
        this(name, id, x, y, true);
    }

    public Player(String name, int id) {
        this(name, id, 0, 0, true);
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

    public void update() {
        if (canMove) {
            didJustMove = false;
            try {
                keyListener = getKeyboard();
                mousePos = MouseInfo.getPointerInfo();
                Point b = mousePos.getLocation();
                int gunX = (int) b.getX();
                int gunY = (int) b.getY();
                if (keyListener.isKeyPressed("W")) {
                    //weapon.move(1,"NORTH");
                    move(1, "NORTH");
                    setAbsPos(getAbsX(), getAbsY() - 1);
                    didJustMove = true;
                }
                if (keyListener.isKeyPressed("S")) {
                    // weapon.move(1,"SOUTH");
                    move(1, "SOUTH");
                    setAbsPos(getAbsX(), getAbsY() + 1);
                    didJustMove = true;
                }
                if (keyListener.isKeyPressed("A")) {
                    //weapon.move(1,"WEST");
                    move(1, "WEST");
                    setAbsPos(getAbsX() - 1, getAbsY());
                    didJustMove = true;
                }
                if (keyListener.isKeyPressed("D")) {
                    //weapon.move(1,"EAST");
                    move(1, "EAST");
                    setAbsPos(getAbsX() + 1, getAbsY());
                    didJustMove = true;
                }
            } catch (ConcurrentModificationException e) {
                System.out.println("Mayflower is bad");
            }
        }
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public boolean didJustMove() {
        return didJustMove;
    }

    public Text getTag() {
        return tag;
    }

    public int getAbsX() {
        return absPos.getX();
    }

    public int getAbsY() {
        return absPos.getY();
    }

    public Vector2 getAbsPos() {
        return absPos;
    }

    public void setAbsPos(int x, int y) {
        absPos.set(x, y);
        weapon.setAbsPos(x, y);
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
