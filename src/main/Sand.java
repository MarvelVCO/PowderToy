package main;

import java.awt.*;

public class Sand extends Solid {

    public Sand(int x, int y, Graphics g, Material[][] grid) {
        super(Color.orange, x, y, g, 1, grid);
    }
}
