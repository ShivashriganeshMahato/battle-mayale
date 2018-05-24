package weapons;

import entities.Player;

public class LMG extends Weapon {
    public LMG(Player p) {
        super(6, 40, 1, 6, p, "images/lmg.png");
    }

    @Override
    public String getName() {
        return "LMG";
    }

    @Override
    public void resetPicture() {
        setPicture("images/lmg.png");
    }
}
