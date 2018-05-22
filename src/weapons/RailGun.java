package weapons;

import entities.Player;

public class RailGun extends Weapon {
    public RailGun(Player p) {
        super(10, 1, 1, 80, p);
    }

    @Override
    public String getName() {
        return "Railgun";
    }
}
