package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    private Thread gameThread;
    private final MouseHandler mH = new MouseHandler();
    private final KeyHandler kH = new KeyHandler();

    private int mouseX1;
    private int mouseY1;

    private int FPS = 80;

    private Material[][] grid;
    private int gridSize;
    private int gridPxSize;

    private int brushSize;
    private int materialSelected;
    private String[] materials;

    public GamePanel() {
        gridSize = 500;
        gridPxSize = 2;
        grid = new Material[gridSize][gridSize];

        brushSize = gridPxSize;
        materialSelected = 0;
        materials = new String[]{"sand", "water", "vine", "acid"};

        this.setPreferredSize(new Dimension(gridPxSize * gridSize - gridPxSize, gridPxSize * gridSize - gridPxSize));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        this.addMouseListener(mH);
        this.addKeyListener(kH);
        this.addMouseMotionListener(mH);

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
                if (mH.mousePos.y / 2 >= 0 && mH.mousePos.y / 2 < gridSize && mH.mousePos.x / 2 >= 0 && mH.mousePos.x / 2 < gridSize && grid[mH.mousePos.y / 2][mH.mousePos.x / 2] == null) {
                    for (int i = mH.mousePos.y / 2 - brushSize / 2; i < mH.mousePos.y / 2 + brushSize / 2 && i < gridSize - 1 && i > 0; i++) {
                        for (int j = mH.mousePos.x / 2 - brushSize / 2; j < mH.mousePos.x / 2 + brushSize / 2 && j < gridSize - 1 && j > 0; j++) {
                            if (materials[materialSelected].equals("sand")) {
                                grid[i][j] = new Sand(j, i);
                            }
                            if (materials[materialSelected].equals("water")) {
                                grid[i][j] = new Water(j, i);
                            }
                            if (materials[materialSelected].equals("vine")) {
                                grid[i][j] = new Vine(j, i);
                            }
                            if (materials[materialSelected].equals("acid")) {
                                grid[i][j] = new Acid(j, i);
                            }
                        }
                    }
                }

                mH.mouseClicked = false;
            }

            if (kH.upPressed) {
                brushSize += gridPxSize;
                kH.upPressed = false;
            }

            if (kH.downPressed) {
                brushSize -= brushSize <= gridPxSize ? 0 : gridPxSize;
                kH.downPressed = false;
            }

            if (kH.leftPressed) {
                for (int r = 0; r < gridSize; r++) {
                    for (int c = 0; c < gridSize; c++) {
                        grid[r][c] = null;
                    }
                }
                kH.leftPressed = false;
            }

            if (kH.rightPressed) {
                materialSelected += materialSelected != materials.length - 1 ? 1 : -(materials.length - 1);
                kH.rightPressed = false;
            }

            if(delta >= 1) {

                for (int r = 0; r < gridSize; r++) {
                    for (int c = 0; c < gridSize; c++) {
                        if (grid[r][c] != null && !grid[r][c].isProcessed()) {
                            grid[r][c].setProcessed(true);

                            if (grid[r][c] instanceof Solid) {
                                ((Solid) grid[r][c]).move(grid);
                            }

                            if (grid[r][c] instanceof Liquid) {
                                ((Liquid) grid[r][c]).move(grid);
                            }
                        }
                    }
                }
                for (int r = 0; r < gridSize; r++) {
                    for (int c = 0; c < gridSize; c++) {
                        if (grid[r][c] != null) {
                            grid[r][c].setProcessed(false);
                        }
                    }
                }

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
                    g.setColor(grid[r][c].getColor());
                    g.fillRect(c * gridPxSize, r * gridPxSize, gridPxSize, gridPxSize);

                }
            }
        }
    }
}
