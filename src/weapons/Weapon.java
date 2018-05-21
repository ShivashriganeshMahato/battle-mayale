package weapons;

import mayflower.Actor;
import mayflower.Mouse;
import mayflower.Timer;
import util.Vector2;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class Weapon extends Actor
{
    private int bulletSpeed;
    private int magSize;
    private int bullets;
    private int bulletsLeft;
    private int bulletDamage;
    private boolean mouse;
    private Mouse mouseListener;
    private Vector2 mousePos;
    private Timer timer;
    private String msgToSend;
    private Vector2 absPos;

    public Weapon(int bs, int ms, int b, int bd)
    {
        bulletSpeed = bs;
        magSize = ms;
        bullets = b;
        bulletsLeft = ms;
        bulletDamage = bd;
        mouse = false;
        timer = new Timer();
        setPicture("images/mayrio.png");
        msgToSend = null;
        absPos = new Vector2(0, 0);
    }

    public void update()
    {
        mouseListener = getMouse();
        mousePos = new Vector2(mouseListener.getX(), mouseListener.getY());
        if(mouseListener.isButtonPressed())
        {
            this.shoot(mousePos);
        }
        if (bulletsLeft == 0 && timer.getTimePassed() >= 3000) {
            bulletsLeft = magSize;
        }
    }

    public void shoot(Vector2 poi)
    {
        Vector2 vel = getVel(new Vector2(400, 300), poi);
        double vx = vel.getX();
        double vy = vel.getY();
        if(bulletsLeft != 0)
        {
            //spawn # of bullets as stated by the bullets variable and subtract 1 from bulletsLeft
            msgToSend = "shoot " + absPos.getX() + " " + absPos.getY() + " " + vx + " " + vy;
            bulletsLeft--;
            if (bulletsLeft == 0)
                timer.reset();
        }
    }

    public Vector2 getVel(Vector2 from, Vector2 to) {
        Vector2 vel = new Vector2(0, 0);
        Vector2 disp = to.sub(from);
        vel.setX(bulletSpeed * (disp.getX() / disp.getMag()));
        vel.setY(bulletSpeed * (disp.getY() / disp.getMag()));
        return vel;
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

    public void setAbsPos(double x, double y) {
        absPos.set(x, y);
    }

    public Vector2 getAbsPos() {
        return absPos;
    }

    public abstract String getName();

    public String getInfo() {
        return getName() + "   Damage: " + bulletDamage;
    }

    public String getAmmoInfo() {
        StringBuilder builder = new StringBuilder();

        builder.append(bulletsLeft);
        builder.append("/");
        builder.append(magSize);
        if (bulletsLeft == 0) {
            builder.append(" ").append(3 - timer.getTimePassed() / 1000);
        }

        return builder.toString();
    }
}
