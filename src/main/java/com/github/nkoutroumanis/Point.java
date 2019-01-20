package com.github.nkoutroumanis;

public class Point {

    private Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    private final double  x;
    private final double  y;


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public static Point newPoint(double x, double y){
        return new Point(x, y);
    }
}
