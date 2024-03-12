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
    public GamePanel() {
        this.setPreferredSize(new Dimension(1920, 1080));
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

    }
}