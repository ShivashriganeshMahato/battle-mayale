package weapons;

import entities.Player;

public class Shotgun extends Weapon {

    public Shotgun(Player p) {
        super(8, 2, 5, 10, p, "images/shotgun.png");
    }

    @Override
    public String getName() {
        return "Shotgun";
    }
}
