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

        double random = Math.random() * 3;

        boolean leftOpen = true;
        boolean rightOpen = true;
        int i = 1;
//        System.out.println((getX() < getX() + (getX() + slip >= grid.length ? grid.length - getX() - 1 : slip)));
//        for (int c = getX(); (c < getX() + (getX() + slip >= grid.length ? grid.length - getX() - 1 : slip)) && (rightOpen || leftOpen); c++) {
//            if ((!(grid[getY()][c] instanceof Solid) && rightOpen) && (!(grid[getY()][c - i] instanceof Solid) && leftOpen)) {
//                if (random < 1.5) {
//                    if (grid[getY()][c] == null && rightOpen) {
//                        setX(c);
//                        break;
//                    }
//                    else {
//                        rightOpen = false;
//                    }
//                }
//
//                else {
//                    if (grid[getY()][c - i] == null && leftOpen) {
//                        setX(c - i);
//                        break;
//                    }
//                    else {
//                        leftOpen = false;
//                    }
//                }
//            }
//
//            if (grid[getY()][c] == null && rightOpen) {
//                setX(c);
//                break;
//            }
//            else {
//                rightOpen = false;
//            }
//
//
//            if (grid[getY()][c - i] == null && leftOpen) {
//                setX(c - i);
//                break;
//            }
//            else {
//                leftOpen = false;
//            }
//
//            i++;
//        }
    }
}
