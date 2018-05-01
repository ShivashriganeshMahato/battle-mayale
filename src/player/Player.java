package player;

import util.Vector2;

/**
 * @author Shivashriganesh Mahato
 */
public class Player {
    private Vector2 position;
    private String name;
    private int id;

    public Player(String name, int id) {
        this.name = name;
        this.id = id;
        position = new Vector2(20, 20);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getX() {
        return position.getX();
    }

    public double getY() {
        return position.getY();
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setX(double x) {
        position.setX(x);
    }

    public void setY(double y) {
        position.setY(y);
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
}
