package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    private Thread gameThread;
    private final MouseHandler mH = new MouseHandler();
    private int mouseX1;
    private int mouseY1;
    int FPS = 60;

    private Material[][] grid;
    private int gridSize;
    private int gridPxSize;
    public GamePanel() {
        gridSize = 500;
        gridPxSize = 2;
        grid = new Material[gridSize][gridSize];

        this.setPreferredSize(new Dimension(gridPxSize * gridSize - gridPxSize, gridPxSize * gridSize - gridPxSize));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addMouseListener(mH);
        this.addMouseMotionListener(mH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(mH.mouseClicked) {
                System.out.println(mH.mousePos.y + " " + mH.mousePos.x);
                grid[mH.mousePos.x / 2][mH.mousePos.y / 2] = new Sand(mH.mousePos.x / 2, mH.mousePos.y / 2);
                mH.mouseClicked = false;

            }

            if(delta >= 1) {
                repaint();
                delta--;

                for (int r = 0; r < gridSize; r++) {
                    for (int c = 0; c < gridSize; c++) {
                        if (grid[r][c] != null) {
                            ((Solid) grid[r][c]).move(grid);
                        }
                    }
                }

                updateGrid();
            }
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int r = 0; r < gridSize; r++) {
            for (int c = 0; c < gridSize; c++) {
                if(grid[r][c] != null) {
                    g.setColor(grid[r][c].getColor());
                    g.fillRect(r * gridPxSize, c * gridPxSize, gridPxSize, gridPxSize);
                }
            }
        }
    }

    public void updateGrid() {
        Material[][] tempGrid = new Material[gridSize][gridSize];
        for (int r = 0; r < gridSize; r++) {
            for (int c = 0; c < gridSize; c++) {
                if(grid[r][c] != null) {
                    tempGrid[grid[r][c].getX()][grid[r][c].getY()] = grid[r][c];
                }
            }
        }
        grid = tempGrid;
    }
}