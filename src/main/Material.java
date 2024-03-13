package main;

import java.awt.*;

public class Material {
    private int x;
    private int y;
    private Material[][] grid;
    private Graphics g;
    public Material(Color color, int x, int y, Material[][] grid, Graphics g) {
        this.x = x;
        this.y = y;
        this.grid = grid;
        this.g = g;
        g.setColor(color);
    }

    public Graphics getGraphics() {
        return g;
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
