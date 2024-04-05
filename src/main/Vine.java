package main;


import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


public class Vine extends Solid {
    public Vine(int x, int y) {
        super(Color.green, x, y, 1);
    }


    @Override
    public void move(Material[][] grid) {
        double random = Math.random() * 3;


        grid[getY()][getX()] = null;
        Material[] leftRight = new Material[]{getX() > 0 ? grid[getY()][getX() - 1] : new Material(Color.BLACK, 0, 0), getX() < grid.length - 1 ? grid[getY()][getX() + 1] : new Material(Color.BLACK, 0, 0)};


        if (getY() < grid.length - 2) {
            Material[] bottomThree = new Material[]{getX() > 0 ? grid[getY() + 1][getX() - 1] : new Material(Color.BLACK, -1, getX()), grid[getY() + 1][getX()], getX() < grid.length - 1 ? grid[getY() + 1][getX() + 1] : new Material(Color.BLACK, 500, getX())};


            if (Arrays.stream(bottomThree).allMatch(Objects::isNull)) {
                setX(random < 0.5 ? getX() - 1 : random > 2.5 ? getX() + 1 : getX());


                momentumDirection = "";
                momentum += momentum < super.getSlip() ? 1 : 0;
                setY(getY() + 1);
            }
            else if (Arrays.stream(bottomThree).anyMatch(Objects::isNull)) {
                ArrayList<Material> openSpots = new ArrayList<>();


                for (int i = 0; i < bottomThree.length; i++) {
                    if (!(bottomThree[i] instanceof main.Solid)) {
                        if (i == 0) {
                            openSpots.add(bottomThree[0]);
                        }
                        if (i == 1) {
                            openSpots.add(bottomThree[1]);
                        }
                        if (i == 2) {
                            openSpots.add(bottomThree[2]);
                        }
                    }
                }


                if (openSpots.size() == 2) {
                    if (random < 1.5) {
                        if (openSpots.get(0) instanceof Water) {
                            grid[getY()][getX()] = grid[getY() + 1][getX() + 1];
                            setX(openSpots.get(0).getX());
                            setY(openSpots.get(0).getY());
                        }
                    }
                    if (random > 1.5) {
                        if (openSpots.get(1) instanceof Water) {
                            grid[getY()][getX()] = grid[getY() + 1][getX() + 1];
                            setX(openSpots.get(1).getX());
                            setY(openSpots.get(1).getY());
                        }
                    }
                }
                else {
                    if (openSpots.get(0) instanceof Water) {
                        grid[getY()][getX()] = grid[getY() + 1][getX() + 1];
                        setX(openSpots.get(0).getX());
                        setY(openSpots.get(0).getY());
                    }
                }


                momentumDirection = "";
                momentum += momentum < super.getSlip() ? 1 : 0;
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
        if (momentumDirection != null && momentum <= super.getSlip() && momentum > 0 && !(momentumDirection.equals("left") && getX() > 0) && !(momentumDirection.equals("right") && getX() < 500) && !(momentumDirection == null)) {
            setX(momentumDirection.equals("left") && getX() > 0 && leftRight[0].getX() != getX() - 1 ? getX() - 1 : getX() < grid.length && leftRight[1].getX() != getX() + 1 ? getX() + 1 : getX());
        }
    }
}
