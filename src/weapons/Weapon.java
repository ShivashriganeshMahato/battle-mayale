package weapons;

import java.util.Timer;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Canvas;

public abstract class Weapon extends Canvas implements MouseListener, Runnable
{
    int bulletSpeed;
    int magSize;
    int bullets;
    int bulletsLeft;
    int bulletDamage;
    private boolean mouse;

    public Weapon(int bs, int ms, int b, int bd)
    {
        bulletSpeed = bs;
        magSize = ms;
        bullets = b;
        bulletsLeft = ms;
        bulletDamage = bd;
        mouse = false;
        this.addMouseListener(this);
        new Thread(this).start();
    }

    public void update()
    {
        if(mouse)
        {
            this.shoot();
        }

    }

    public void shoot()
    {
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

    public void mouseClicked(MouseEvent e)
    {

    }

    public void mouseEntered(MouseEvent e)
    {

    }

    public void mouseExited(MouseEvent e)
    {

    }

    public void run()
    {
        try {
            while (true)
            {
                Thread.currentThread().sleep(5);
                //repaint();
            }
        } catch (Exception e)
        {
        }
    }
}
