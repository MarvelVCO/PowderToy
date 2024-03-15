package main;

import java.awt.*;

public class Material {
    private int x;
    private int y;
    private Color color;
    public Material(Color color, int x, int y) {
        this.x = x;
        this.y = y;
        this.color = color;
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
}
