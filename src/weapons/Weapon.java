package weapons;

import mayflower.Actor;
import mayflower.Mouse;

import java.awt.*;
import java.util.Timer;
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
    private Point p;

    public Weapon(int bs, int ms, int b, int bd)
    {
        bulletSpeed = bs;
        magSize = ms;
        bullets = b;
        bulletsLeft = ms;
        bulletDamage = bd;
        mouse = false;
        setPicture("mayrio.png");
    }

    public void update()
    {
        mouseListener = getMouse();
        p = MouseInfo.getPointerInfo().getLocation();
        if(mouseListener.isButtonPressed())
        {
            this.shoot(p);
        }

    }

    public void shoot(Point poi)
    {
        int x = poi.x;
        int y = poi.y;
        if(bulletsLeft != 0)
        {
            //spawn # of bullets as stated by the bullets variable and subtract 1 from bulletsLeft

            bulletsLeft--;
        }
        else
        {
            this.reload();
        }
    }

    public boolean reload()
    {
        Timer timer = new Timer();
        while(!(timer.equals(3000)))
        {
        }
        bulletsLeft = magSize;
        return true;
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
