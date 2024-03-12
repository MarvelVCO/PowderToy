package main;

import java.awt.*;

public class Material {
    private Color color;

    private int x;
    private int y;
    private Material[][] grid;
    public Material(Color color, int x, int y, Material[][] grid) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.grid = grid;
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Material[][] getGrid() {
        return grid;
    }
}
