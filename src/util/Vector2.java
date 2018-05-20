package util;

/**
 * @author Shivashriganesh Mahato
 */
public class Vector2 {
    private int x;
    private int y;

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getMag() {
        return (int) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Vector2 add(Vector2 vector) {
        x += vector.x;
        y += vector.y;
        return this;
    }

    public Vector2 sub(Vector2 vector) {
        x -= vector.x;
        y -= vector.y;
        return this;
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double dot(Vector2 vector) {
        return x * vector.x + y * vector.y;
    }

    @Override
    public String toString() {
        return "X " + getX() + " Y " + getY();
    }
}
