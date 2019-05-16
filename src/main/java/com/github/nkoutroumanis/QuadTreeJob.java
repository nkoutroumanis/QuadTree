package com.github.nkoutroumanis;

import java.util.Random;

public class QuadTreeJob {

    public static void main(String args[]){

        QuadTree quadTree = QuadTree.newQuadTree(0,0,1000,1000, 100);

        Random r = new Random();
        for(int i=0;i<5000000;i++)
        {
            System.out.println(i);
            quadTree.insertPoint(Point.newPoint(Math.random()*0.5d,Math.random()*0.5d));
        }



        quadTree.insertPoint(Point.newPoint(1.00000000000000015,1.00000000000000015));
        quadTree.insertPoint(Point.newPoint(1.0000000000000011,1.0000000000000011));
//        quadTree.insertPoint(Point.newPoint(1,1));
//        quadTree.insertPoint(Point.newPoint(3,3));
//        quadTree.insertPoint(Point.newPoint(4,4));
//        quadTree.insertPoint(Point.newPoint(4,1));
//        quadTree.insertPoint(Point.newPoint(5,5));
//        quadTree.insertPoint(Point.newPoint(5.5,5.5));
//        quadTree.insertPoint(Point.newPoint(6.6,6.7));

        System.out.println(quadTree.determineRadiusOfPoint(1, Point.newPoint(0,0)));
        System.out.println("Number of contained points of Tree "+ quadTree.getNumberOfInsertedPoints());
        System.out.println(QuadTree.o);


    }

}
