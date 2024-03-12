package main;

import java.awt.*;

public class Solid extends Material {
    private int weight;
    private int friction;
    private int density;

    public Solid(Color color, int x, int y, int weight, Material[][] grid) {
        super(color, x, y, grid);
        this.weight = weight;
    }
    public int getWeight() {
        return weight;
    }

    public int getFriction() {
        return friction;
    }

    public void move() {
        Material bottomLeft = getGrid()[getY() + 1][getX() - 1];
        Material bottom = getGrid()[getY() + 1][getX()];
        Material bottomRight = getGrid()[getY() + 1][getX() + 1];

        int random = (int) (Math.random() * 3);

        if (!(bottomLeft instanceof Solid) && !(bottom instanceof Solid) && !(bottomRight instanceof Solid)) {
            setX(random < 0.75 ? getX() - 1 : random > 1.25 ? getX() + 1 : getX());
        }

        if (bottom instanceof Solid) {
            if (!(bottomLeft instanceof Solid) && !(bottomRight instanceof Solid)) {
                setX((int) (Math.random() * 2) < 1 ? getX() - 1 : getX() + 1);
            }
        }

        setY(getY() - 1);
    }
}
