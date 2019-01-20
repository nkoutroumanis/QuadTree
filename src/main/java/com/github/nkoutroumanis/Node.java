package com.github.nkoutroumanis;

import java.util.Set;

public class Node {

    private Node(Node parent, double lowerBoundx, double lowerBoundy, double upperBoundx, double upperBoundy) {
        this.parent = parent;
        this.upperBoundx = upperBoundx;
        this.upperBoundy = upperBoundy;
        this.lowerBoundx = lowerBoundx;
        this.lowerBoundy = lowerBoundy;
    }

    private final double upperBoundx;
    private final double upperBoundy;
    private final double lowerBoundx;
    private final double lowerBoundy;

    public Node getParent() {
        return parent;
    }

    private Node parent;
    private Node topLeftChildQuadrant;
    private Node topRightChildQuadrant;
    private Node bottomRightChildQuadrant;
    private Node bottomLeftChildQuadrant;

    public boolean hasChildrenQuadrants() {
        return hasChildrenQuadrants;
    }

    public int getNumberOfContainedPoints() {
        return numberOfContainedPoints;
    }

    private int numberOfContainedPoints ;
    private boolean hasChildrenQuadrants = false;
    private Point[] points;

    public Node getTopLeftChildQuadrant() {
        return topLeftChildQuadrant;
    }

    public void increaseByOneNumberOfContainedPoints(){
        numberOfContainedPoints++;
    }

    public void setChildQuadrants(Node topLeftChildQuadrant, Node topRightChildQuadrant, Node bottomRightChildQuadrant, Node bottomLeftChildQuadrant){
        this.topLeftChildQuadrant = topLeftChildQuadrant;
        this.topRightChildQuadrant = topRightChildQuadrant;
        this.bottomRightChildQuadrant = bottomRightChildQuadrant;
        this.bottomLeftChildQuadrant = bottomLeftChildQuadrant;
        hasChildrenQuadrants = true;
    }

    public Node getTopRightChildQuadrant() {
        return topRightChildQuadrant;
    }

    public Node getBottomRightChildQuadrant() {
        return bottomRightChildQuadrant;
    }

    public Node getBottomLeftChildQuadrant() {
        return bottomLeftChildQuadrant;
    }

    public static Node newNode(Node parent, double lowerBoundx, double lowerBoundy, double upperBoundx, double upperBoundy){
        return new Node(parent, lowerBoundx, lowerBoundy, upperBoundx, upperBoundy);
    }

    public double getUpperBoundx() {
        return upperBoundx;
    }

    public double getUpperBoundy() {
        return upperBoundy;
    }

    public double getLowerBoundx() {
        return lowerBoundx;
    }

    public double getLowerBoundy() {
        return lowerBoundy;
    }

    public boolean intersects(Point point){
        if(((Double.compare(lowerBoundx, point.getX()) != 1) && (Double.compare(upperBoundx, point.getX()) == 1)) &&
                ((Double.compare(lowerBoundy, point.getY()) != 1) && (Double.compare(upperBoundy, point.getY()) == 1))){
            return true;
        }
        return false;
    }

    public Point[] getPoints() {
        return points;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }

    public String toString(){

        StringBuilder sb = new StringBuilder();
        sb.append("Top Left");
        return "Node ["+lowerBoundx + ","+lowerBoundy +"], ["+upperBoundx +","+upperBoundy +"]" +" - has "+ getNumberOfContainedPoints() + " array:"+getPoints();
//        if(hasChildrenQuadrants==true){
//
//
//            return "Node ["+lowerBoundx + ","+lowerBoundy +"], ["+upperBoundx +","+upperBoundy +"]" +" - has "+ getNumberOfContainedPoints() + " array:"+getPoints();
//        }
//        else{
//            return "Leaf Node ["+lowerBoundx + ","+lowerBoundy +"], ["+upperBoundx +","+upperBoundy +"]" +" - has "+ getNumberOfContainedPoints() + " array:"+getPoints();
//        }


    }
//
//    public String toString(){
//
//
//
//    }
}
