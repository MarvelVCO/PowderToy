package main;


import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


public class Acid extends Liquid {
    public Acid(int x, int y) {
        super(Color.pink, x, y);
    }
    @Override
    public void move(Material[][] grid) {
        double random = Math.random() * 3;


        grid[getY()][getX()] = null;


        if (getY() < grid.length - 2) {
            setX(random < 0.5 ? getX() - 1 : random > 2.5 ? getX() + 1 : getX());
            setY(getY() + 1);
        }


        setX(getX() >= grid.length ? grid.length - 1 : Math.max(getX(), 1));
        grid[getY()][getX()] = this;
    }
}
