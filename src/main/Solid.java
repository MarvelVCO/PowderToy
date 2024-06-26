package main;


import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


public class Solid extends Material {
    private int slip;
    public String momentumDirection;
    public int momentum;
    public Solid(Color color, int x, int y, int slip) {
        super(color, x, y);
        this.slip = slip;
        this.momentum = slip;
        this.momentumDirection = "";
    }

    public int getSlip() {
        return slip;
    }

    public void move(Material[][] grid) {
        double random = Math.random() * 3;


        grid[getY()][getX()] = null;
        Material[] leftRight = new Material[]{getX() > 0 ? grid[getY()][getX() - 1] : new Material(Color.BLACK, 0, 0), getX() < grid.length - 1 ? grid[getY()][getX() + 1] : new Material(Color.BLACK, 0, 0)};


        if (getY() < grid.length - 2) {
            Material[] bottomThree = new Material[]{getX() > 0 ? grid[getY() + 1][getX() - 1] : new Material(Color.BLACK, -1, getX()), grid[getY() + 1][getX()], getX() < grid.length - 1 ? grid[getY() + 1][getX() + 1] : new Material(Color.BLACK, 500, getX())};


            if (Arrays.stream(bottomThree).allMatch(Objects::isNull)) {
                setX(random < 0.5 ? getX() - 1 : random > 2.5 ? getX() + 1 : getX());


                momentumDirection = "";
                momentum += momentum < slip ? 1 : 0;
                setY(getY() + 1);
            }
            else if (Arrays.stream(bottomThree).anyMatch(Objects::isNull)) {
                ArrayList<Point> openSpots = new ArrayList<>();


                for (int i = 0; i < bottomThree.length; i++) {
                    if (!(bottomThree[i] instanceof Solid)) {
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


                if (openSpots.size() == 2) {
                    if (random < 1.5) {
                        setX(openSpots.get(0).x);
                        setY(openSpots.get(0).y);
                    }
                    if (random > 1.5) {
                        setX(openSpots.get(1).x);
                        setY(openSpots.get(1).y);

                    }
                }
                else {
                    setX(openSpots.get(0).x);
                    setY(openSpots.get(0).y);

                }


                momentumDirection = "";
                momentum += momentum < slip ? 1 : 0;
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
            momentumDirection = (momentumDirection.isEmpty()) ? (random < 1.5 ? "left" : "right") : momentumDirection;
        }
        else if (Arrays.stream(leftRight).anyMatch(Objects::isNull)) {
            momentumDirection = (momentumDirection.isEmpty()) ? (getX() > 0 && !(grid[getY()][getX() - 1] instanceof main.Solid) ? "left" : "right") : momentumDirection;
        }
        if (momentumDirection != null && momentum <= slip && momentum > 0 && !(momentumDirection.equals("left") && getX() > 0) && !(momentumDirection.equals("right") && getX() < 500) && !(momentumDirection == null)) {
            setX(momentumDirection.equals("left") && getX() > 0 && leftRight[0].getX() != getX() - 1 ? getX() - 1 : getX() < grid.length && leftRight[1].getX() != getX() + 1 ? getX() + 1 : getX());
        }
    }
}
