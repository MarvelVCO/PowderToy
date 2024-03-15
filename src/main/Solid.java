package main;

import java.awt.*;

public class Solid extends Material {
    private int weight;
    private int slip;
    private int density;

    public Solid(Color color, int x, int y, int weight) {
        super(color, x, y);
        this.weight = weight;
    }
    public int getWeight() {
        return weight;
    }

    public int getSlip() {
        return slip;
    }

    public void move(Material[][] grid) {
        boolean isBottomLeftSolid = grid[getY() + 1][getX() - 1] instanceof Solid;
        boolean isBottomSolid = grid[getY() + 1][getX()] instanceof Solid;
        boolean isBottomRightSolid = grid[getY() + 1][getX() + 1] instanceof Solid;

        int random = (int) (Math.random() * 3);

        if (!isBottomLeftSolid && !isBottomSolid && !isBottomRightSolid) {
            setX(random < 0.75 ? getX() - 1 : random > 2.25 ? getX() + 1 : getX());
        }

        if (isBottomSolid) {
            if (!isBottomLeftSolid && !isBottomRightSolid) {
                setX((int) (Math.random() * 2) < 1 ? getX() - 1 : getX() + 1);
            }
            if (!isBottomLeftSolid && isBottomRightSolid) {
                setX(getX() - 1);
            }
            if (isBottomLeftSolid && !isBottomRightSolid) {
                setX(getX() + 1);
            }
        }

        if (!isBottomLeftSolid || !isBottomSolid || !isBottomRightSolid &&
            getY() < 500) {
            setY(getY() - 1);
        }
    }
}
