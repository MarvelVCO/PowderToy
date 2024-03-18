package main;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

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

        double random = Math.random() * 3;
        Material[] bottomThree = new Material[]{grid[getY() + 1][getX() - 1], grid[getY() + 1][getX()], grid[getY() + 1][getX() + 1]};

        if (Arrays.stream(bottomThree).allMatch(Objects::isNull)) {
            if (random < 0.5) {
                setX(getX() - 1);
            }
            if (random > 2.5) {
                setX(getX() + 1);
            }
        }


        else if (Arrays.stream(bottomThree).anyMatch(Objects::isNull)) {
            ArrayList<Point> openSpots = new ArrayList<>();
            for (int i = 0; i < bottomThree.length; i++) {
                if (bottomThree[i] == null) {
                    if (i == 0) {
                        openSpots.add(new Point(getX() - 1, getY() + 1));
                    }
                    if (i == 1) {
                        openSpots.add(new Point(getX(), getY() + 1));
                    }
                    if (i == 2) {
                        openSpots.add(new Point(getX() + 1, getY() + 1));
                    }
                }
            }
            if(openSpots.size() == 2) {
                if (random < 1.5) {
                    setX(openSpots.get(0).x);
                }
                if (random > 1.5) {
                    setX(openSpots.get(1).x);
                }
            }
            else {
                setX(openSpots.get(0).x);
            }
        }

        if (Arrays.stream(bottomThree).allMatch(Objects::nonNull)) {
            // TODO: allow solid to slide across the floor if it hasn't encountered a wall yet
        }

        else {
            setY(getY() + 1);
        }

    }
}
