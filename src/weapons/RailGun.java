package weapons;

import entities.Player;

public class RailGun extends Weapon {
    public RailGun(Player p) {
        super(10, 1, 1, 80, p, "images/railgun.png");
    }

    @Override
    public String getName() {
        return "Railgun";
    }
}
