package main;


import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


public class Liquid extends Material {




    public Liquid(Color color, int x, int y) {
        super(color, x, y);
    }




    public void move(Material[][] grid) {
        double random = Math.random() * 3;


        grid[getY()][getX()] = null;
        Material[] leftRight = new Material[]{getX() > 0 ? grid[getY()][getX() - 1] : new Liquid(Color.BLACK, 0, 0), getX() < grid.length - 1 ? grid[getY()][getX() + 1] : new Liquid(Color.BLACK, 0, 0)};


        if (getY() < grid.length - 2) {
            Material[] bottomThree = new Material[]{getX() > 0 ? grid[getY() + 1][getX() - 1] : new Liquid(Color.BLACK, 0, 0), grid[getY() + 1][getX()], getX() < grid.length - 1 ? grid[getY() + 1][getX() + 1] : new Liquid(Color.BLACK, 0, 0)};


            if (Arrays.stream(bottomThree).allMatch(Objects::isNull)) {
                setX(random < 0.5 ? getX() - 1 : random > 2.5 ? getX() + 1 : getX());
                setY(getY() + 1);


            }
            else if (Arrays.stream(bottomThree).anyMatch(Objects::isNull)) {
                ArrayList<Point> openSpots = new ArrayList<>();


                for (int i = 0; i < bottomThree.length; i++) {
                    if (!(bottomThree[i] instanceof Liquid)) {
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


                if (!openSpots.isEmpty()) {
                    Point selectedSpot = openSpots.get((int) (Math.random() * openSpots.size()));
                    setX(selectedSpot.x);
                    setY(selectedSpot.y);
                }


            }
            else {
                CheckSides(grid, random, leftRight);
            }
        }


        CheckSides(grid, random, leftRight);


        setX(getX() >= grid.length ? grid.length - 1 : Math.max(getX(), 1));
        grid[getY()][getX()] = this;
    }


    private void CheckSides(Material[][] grid, double random, Material[] leftRight) {
        if (Arrays.stream(leftRight).allMatch(Objects::isNull)) {
            setX(random < 0.2 ? getX() - 1 : random > 2.8 ? getX() + 1 : getX());
        }
        else if (Arrays.stream(leftRight).anyMatch(Objects::nonNull) && grid[getY() + 1][getX()] instanceof Liquid) {
            if (grid[getY()][getX() - 1] == null) {
                setX(getX() - 1);
            }
            else if (grid[getY()][getX() + 1] == null) {
                setX(getX() + 1);
            }
        }
    }
}
