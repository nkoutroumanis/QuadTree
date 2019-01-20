package com.github.nkoutroumanis;

public class QuadTree {

    private final Node root;
    private final int MAX_NUMBER_OF_POINTS_IN_LEAVES = 1;
    public static int o = 0 ;

    private QuadTree(Node root){
        this.root = root;
    }

    public static QuadTree newQuadTree(double lowerBoundx, double lowerBoundy, double upperBoundx, double upperBoundy){
        return new QuadTree(Node.newNode(null, lowerBoundx, lowerBoundy, upperBoundx, upperBoundy));
    }

    public void insertPoint(Point point){
        insertPoint(root, point);
    }

    public double determineRadiusOfPoint(int k,Point point){
        Node node = determineLeafNodeForRadius(root, point);

        double d = Integer.MIN_VALUE;
        boolean i = true;
        while(i){

            if(node.getNumberOfContainedPoints()< k){
                node = node.getParent();
            }
            else{
                
                double distance;

                double d1 = harvesine(point.getX(), point.getY(), node.getUpperBoundx(), node.getUpperBoundy());
                distance = d1;

                double d2 = harvesine(point.getX(), point.getY(), node.getLowerBoundx(), node.getLowerBoundy());

                if (Double.compare(d2, distance) == 1) {
                    distance = d2;
                }

                double d3 = harvesine(point.getX(), point.getY(), node.getUpperBoundx(), node.getLowerBoundy());

                if (Double.compare(d3, distance) == 1) {
                    distance = d3;
                }

                double d4 = harvesine(point.getX(), point.getY(), node.getLowerBoundx(), node.getUpperBoundy());

                if (Double.compare(d4, distance) == 1) {
                    distance = d4;
                }

                d = distance;
                i = false;

            }
        }

        return d;
    }

    private Node determineLeafNodeForRadius(Node node, Point point){

        if(node.hasChildrenQuadrants()){
            if(node.getTopLeftChildQuadrant().intersects(point)){
                return determineLeafNodeForInsertion(node.getTopLeftChildQuadrant(), point);
            }
            else if(node.getTopRightChildQuadrant().intersects(point)){
                return determineLeafNodeForInsertion(node.getTopRightChildQuadrant(), point);
            }
            else if(node.getBottomRightChildQuadrant().intersects(point)){
                return determineLeafNodeForInsertion(node.getBottomRightChildQuadrant(), point);
            }
            else if(node.getBottomLeftChildQuadrant().intersects(point)){
                return determineLeafNodeForInsertion(node.getBottomLeftChildQuadrant(), point);
            }
            else{
                try {
                    throw new Exception("Error1");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return node;
    }

    private static double harvesine(double lon1, double lat1, double lon2, double lat2) {

        double r = 6378.1;

        double f1 = Math.toRadians(lat1);
        double f2 = Math.toRadians(lat2);

        double df = Math.toRadians(lat2 - lat1);
        double dl = Math.toRadians(lon2 - lon1);

        double a = Math.sin(df / 2) * Math.sin(df / 2) + Math.cos(f1) * Math.cos(f2) * Math.sin(dl / 2) * Math.sin(dl / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return r * c;
    }

    private void insertPoint(Node node, Point point){

        Node leafNode = determineLeafNodeForInsertion(node, point);

        if(leafNode.getNumberOfContainedPoints() == MAX_NUMBER_OF_POINTS_IN_LEAVES){
            createQuadrants(leafNode);
            o++;
            disseminatePointsToQuadrants(leafNode);
            insertPoint(leafNode, point);
        }
        else{
            addPointToNode(leafNode, point);
        }
    }

    private Node determineLeafNodeForInsertion(Node node, Point point){

        if(node.hasChildrenQuadrants()){
            if(node.getTopLeftChildQuadrant().intersects(point)){
                node.increaseByOneNumberOfContainedPoints();
                return determineLeafNodeForInsertion(node.getTopLeftChildQuadrant(), point);
            }
            else if(node.getTopRightChildQuadrant().intersects(point)){
                node.increaseByOneNumberOfContainedPoints();
                return determineLeafNodeForInsertion(node.getTopRightChildQuadrant(), point);
            }
            else if(node.getBottomRightChildQuadrant().intersects(point)){
                node.increaseByOneNumberOfContainedPoints();
                return determineLeafNodeForInsertion(node.getBottomRightChildQuadrant(), point);
            }
            else if(node.getBottomLeftChildQuadrant().intersects(point)){
                node.increaseByOneNumberOfContainedPoints();
                return determineLeafNodeForInsertion(node.getBottomLeftChildQuadrant(), point);
            }
            else{
                try {
                    throw new Exception("Error1");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
            return node;
    }


    private void createQuadrants(Node node){

        node.setChildQuadrants(Node.newNode(node, node.getLowerBoundx(), (node.getUpperBoundy() + node.getLowerBoundy())/2, (node.getUpperBoundx() + node.getLowerBoundx())/2, node.getUpperBoundy()),

                Node.newNode(node,(node.getUpperBoundx() + node.getLowerBoundx())/2, (node.getUpperBoundy() + node.getLowerBoundy())/2, node.getUpperBoundx(), node.getUpperBoundy()),

                Node.newNode(node,(node.getUpperBoundx() + node.getLowerBoundx())/2, node.getLowerBoundy(), node.getUpperBoundx(), (node.getUpperBoundy() + node.getLowerBoundy())/2),

                Node.newNode(node, node.getLowerBoundx(), node.getLowerBoundy(), (node.getUpperBoundx() + node.getLowerBoundx())/2, (node.getUpperBoundy() + node.getLowerBoundy())/2));
    }

    private void disseminatePointsToQuadrants(Node node){

        Point[] points = node.getPoints();

        for(int i=0;i<points.length;i++){

            if(node.getTopLeftChildQuadrant().intersects(points[i])){
                addPointToNode(node.getTopLeftChildQuadrant(), points[i]);
                continue;
            }

            if(node.getTopRightChildQuadrant().intersects(points[i])){
                addPointToNode(node.getTopRightChildQuadrant(), points[i]);
                continue;
            }

            if(node.getBottomRightChildQuadrant().intersects(points[i])){
                addPointToNode(node.getBottomRightChildQuadrant(), points[i]);
                continue;
            }

            if(node.getBottomLeftChildQuadrant().intersects(points[i])){
                addPointToNode(node.getBottomLeftChildQuadrant(), points[i]);
                continue;
            }

        }

        node.setPoints(null);

    }

    private void addPointToNode(Node node, Point point){

        if(node.getPoints() == null){
            node.setPoints(new Point[MAX_NUMBER_OF_POINTS_IN_LEAVES]);
        }

        node.getPoints()[node.getNumberOfContainedPoints()] = point;
        node.increaseByOneNumberOfContainedPoints();

    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

       return sb.toString();

    }

    public int getNumberOfInsertedPoints(){
        return root.getNumberOfContainedPoints();
    }

}
