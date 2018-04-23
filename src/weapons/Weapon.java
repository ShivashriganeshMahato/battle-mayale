package weapons;

import java.util.Timer;

public abstract class Weapon
{
    int bulletSpeed;

    {
        bulletSpeed = 5;
    }

    public abstract void shoot();

    public boolean reload(int reloadSpeed)
    {
        Timer timer = new Timer();
        while(!(timer.equals(3000)))
        {
        }
        return true;
    }
}
