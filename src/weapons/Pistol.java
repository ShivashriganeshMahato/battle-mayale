package weapons;

import entities.Player;

public class Pistol extends Weapon {

    public Pistol(Player p) {
        super(6, 8, 1, 10, p, "images/pistol.png");
    }

    @Override
    public String getName() {
        return "Pistol";
    }
}
