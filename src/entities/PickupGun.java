package entities;

import mayflower.Actor;
import util.Vector2;

import java.util.List;

/**
 * @author Shivashriganesh Mahato
 */
public class PickupGun extends Actor {
    private List<Player> players;
    private String type;
    private Vector2 absPos;

    public PickupGun(double x, double y, String type, List<Player> players) {
        this.type = type;
        this.players = players;
        absPos = new Vector2(x, y);
        setPicture("images/smallMayrio.png");
    }

    @Override
    public void update() {
        for (Player player : players) {
            if (isTouching(player)) {
                System.out.println(type);
                setType(player.pickUp(type));
            }
        }
    }

    public Vector2 getAbsPos() {
        return absPos;
    }

    public double getAbsX() {
        return absPos.getX();
    }

    public double getAbsY() {
        return absPos.getY();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
