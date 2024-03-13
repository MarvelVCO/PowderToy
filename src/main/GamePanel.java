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

        this.setPreferredSize(new Dimension(gridPxSize * gridSize, gridPxSize * gridSize));
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
//                grid[mH.mousePos.y][mH.mousePos.x] = new Sand(mH.mousePos.y, mH.mousePos.x);
                mH.mouseClicked = false;

            }

            if(delta >= 1) {
                repaint();
                delta--;
            }
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int r = 0; r < gridSize; r++) {
            for (int c = 0; c < gridSize; c++) {
                if(grid[r][c] != null) {
                    grid[r][c].getGraphics().drawRect(r * 2, c * 2, gridPxSize, gridPxSize);
                }
            }
        }
    }
}