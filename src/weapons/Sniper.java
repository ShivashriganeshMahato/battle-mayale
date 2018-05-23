package weapons;

import entities.Player;

public class Sniper extends Weapon {

    public Sniper(Player p) {
        super(8, 5, 1, 20, p, "images/sniper.png");
    }

    @Override
    public String getName() {
        return "Sniper";
    }
}
