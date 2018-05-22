package weapons;

import entities.Player;

public class Rifle extends Weapon
{

    public Rifle(Player p)
    {
        super(6, 20, 1, 8,p);
    }

    @Override
    public String getName() {
        return "Rifle";
    }
}
