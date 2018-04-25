package weapons;

import java.util.Timer;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Canvas;

public abstract class Weapon //extends Canvas implements MouseListener, Runnable
{
    int bulletSpeed;
    int magSize;
    int bullets;
    int bulletsLeft;

    public Weapon(int bs, int ms, int b)
    {
        bulletSpeed = bs;
        magSize = ms;
        bullets = b;
        bulletsLeft = ms;
        //this.addMouseListener(this);
        //new Thread(this).start();
    }

    public void update()
    {
        //if(this.mouseClicked(KeyEvent.BUTTON1_MASK))
        {
            //this.shoot();
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
}
