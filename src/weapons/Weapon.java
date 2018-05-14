package weapons;

import mayflower.Actor;
import mayflower.Mouse;
import mayflower.Timer;
import util.Vector2;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class Weapon extends Actor
{
    int bulletSpeed;
    int magSize;
    int bullets;
    int bulletsLeft;
    int bulletDamage;
    private boolean mouse;
    private Mouse mouseListener;
    private Vector2 mousePos;
    private Timer timer;
    private String msgToSend;

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
        msgToSend = null;
    }

    public void update()
    {
        mouseListener = getMouse();
        mousePos = new Vector2(mouseListener.getX(), mouseListener.getY());
        if(mouseListener.isButtonPressed())
        {
            System.out.println(mousePos);
            this.shoot(mousePos);
        }
        if (bulletsLeft == 0 && timer.getTimePassed() >= 3000) {
            bulletsLeft = magSize;
        }
    }

    public void shoot(Vector2 poi)
    {
        int x = poi.getX();
        int y = poi.getY();
        if(bulletsLeft != 0)
        {
            //spawn # of bullets as stated by the bullets variable and subtract 1 from bulletsLeft
            msgToSend = "shoot " + getX() + " " + getY();
            bulletsLeft--;
            if (bulletsLeft == 0)
                timer.reset();
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

    public String getMsgToSend() {
        return msgToSend;
    }

    public void clearMsg() {
        msgToSend = null;
    }
}
