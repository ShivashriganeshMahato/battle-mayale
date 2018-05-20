package util;

/**
 * @author Shivashriganesh Mahato
 */
public class Vector2 {
    private double x;
    private double y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getMag() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
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

    public void set(double x, double y) {
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
