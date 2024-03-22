//package main;
//
//import java.awt.*;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Objects;
//
//public class Solid extends Material {
//    private int weight;
//    private int slip;
//    private int density;
//    private int terminalMomentum;
//    int momentum;
//    String momentumDirection;
//
//    public Solid(Color color, int x, int y, int weight, int terminalMomentum) {
//        super(color, x, y);
//        this.weight = weight;
//        this.terminalMomentum = terminalMomentum;
//        this.momentum = terminalMomentum;
//        momentumDirection = "";
//    }
//    public int getWeight() {
//        return weight;
//    }
//
//    public int getSlip() {
//        return slip;
//    }
//
//    public void move(Material[][] grid) {
//        System.out.println("MOVENTOENT");
//        double random = Math.random() * 3;
//
//        grid[getY()][getX()] = null;
//        if (getY() + 1 < 500) {
//            Material[] bottomThree = new Material[]{grid[getY() + 1][getX() - 1], grid[getY() + 1][getX()], grid[getY() + 1][getX() + 1]};
//        }
//        else {
//            Material[] bottomThree = null;
//        }
//
//        if () {
//            if (Arrays.stream(bottomThree).allMatch(Objects::isNull)) {
//                if (random < 0.5) {
//                    setX(getX() - 1);
//                }
//                if (random > 2.5) {
//                    setX(getX() + 1);
//                }
//            } else if (Arrays.stream(bottomThree).anyMatch(Objects::isNull)) {
//                ArrayList<Point> openSpots = new ArrayList<>();
//
//                for (int i = 0; i < bottomThree.length; i++) {
//                    if (bottomThree[i] == null) {
//                        if (i == 0) {
//                            openSpots.add(new Point(getX() - 1, getY() + 1));
//                        }
//                        if (i == 1) {
//                            openSpots.add(new Point(getX(), getY() + 1));
//                        }
//                        if (i == 2) {
//                            openSpots.add(new Point(getX() + 1, getY() + 1));
//                        }
//                    }
//                }
//
//                if (openSpots.size() == 2) {
//                    if (random < 1.5) {
//                        setX(openSpots.get(0).x);
//                    }
//                    if (random > 1.5) {
//                        setX(openSpots.get(1).x);
//                    }
//                } else {
//                    setX(openSpots.get(0).x);
//                }
//            }
//
//        Material[] leftRight = new Material[]{grid[getY()][getX() - 1], grid[getY()][getX() + 1]};
//
//        if (getY() == 499 || Arrays.stream(bottomThree).allMatch(Objects::nonNull) && getY() < 500) {
//            if (Arrays.stream(leftRight).allMatch(Objects::isNull)) {
//                momentumDirection = (momentumDirection.isEmpty()) ? (random < 1.5 ? "left" : "right") : momentumDirection;
//            }
//
//            else if (Arrays.stream(leftRight).anyMatch(Objects::isNull)) {
//                momentumDirection = (momentumDirection.isEmpty()) ? (grid[getY()][getX() - 1] == null ? "left" : "right") : momentumDirection;
//            }
//
//            if (momentum <= terminalMomentum && momentum > 0 && !(momentumDirection.equals("left") && getX() > 0) && !(momentumDirection.equals("right") && getX() < 500)) {
//                setX((momentumDirection == null) ? getX() : (momentumDirection.equals("left") ? getX() - 1 : getX() + 1));
//            }
//        }
//
//        else {
//            momentumDirection = null;
//            momentum += momentum < terminalMomentum ? 1 : 0;
//            setY(getY() + 1);
//        }
//
//        grid[getY()][getX()] = this;
//    }
//}
