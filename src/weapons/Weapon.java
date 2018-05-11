package weapons;

import mayflower.Actor;
import mayflower.Mouse;
import mayflower.Timer;
import util.Vector2;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Weapon extends Actor
{
    int bulletSpeed;
    int magSize;
    int bullets;
    int bulletsLeft;
    int bulletDamage;
    private boolean mouse;
    private Mouse mouseListener;
    private PointerInfo mousePos;
    private Vector2 p;
    private Timer timer;

    public Weapon(int bs, int ms, int b, int bd)
    {
        bulletSpeed = bs;
        magSize = ms;
        bullets = b;
        bulletsLeft = ms;
        bulletDamage = bd;
        mouse = false;
        timer = new Timer();
        setPicture("src/mayrio.png");
    }

    public void update()
    {
        System.out.println(bulletsLeft);
        mouseListener = getMouse();
        p = new Vector2(mouseListener.getX(), mouseListener.getY());
        if(mouseListener.isButtonPressed())
        {
            System.out.println(p);
            this.shoot(p);
        }
        if (bulletsLeft == 0) {
            if (timer.getTimePassed() >= 3000)
                bulletsLeft = magSize;
            else
                timer.reset();
        }
    }

    public void shoot(Vector2 poi)
    {
        int x = poi.getX();
        int y = poi.getY();
        if(bulletsLeft != 0)
        {
            //spawn # of bullets as stated by the bullets variable and subtract 1 from bulletsLeft
            bulletsLeft--;
        }
    }

    public void mousePressed(MouseEvent e)
    {
        if (e.getButton() == KeyEvent.BUTTON1_MASK)
        {
            mouse = true;
        }
    }

    public void mouseReleased(MouseEvent e)
    {
        if(e.getButton() == KeyEvent.BUTTON1_MASK)
        {
            mouse = false;
        }
    }

}
