import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) {
        testIsConvex();
        testSpeed();
    }

    private static double CrossProduct(Point A, Point B, Point C) {
        double X1 = (B.x - A.x);
        double Y1 = (B.y - A.y);
        double X2 = (C.x - A.x);
        double Y2 = (C.y - A.y);
        return (X1 * Y2 - Y1 * X2);
    }




    /**
     * Checks if shape is convex
     * Implementation based off this geeks for geeks article: https://www.geeksforgeeks.org/check-if-given-polygon-is-a-convex-polygon-or-not/#
     * @param points List of points that make up the polygon
     * @return true if the polygon is convex, false otherwise
     */
    static boolean isConvex(ArrayList<Point> points) {
        int N = points.size();
        double prev = 0;
        double curr;

        // Traverse the array
        for (int i = 0; i < N; i++) {
            Point A = points.get(i);
            Point B = points.get((i + 1) % N);
            Point C = points.get((i + 2) % N);

            curr = CrossProduct(A, B, C);

            if (curr != 0) {
                if (curr * prev < 0) {
                    return false;
                } else {
                    prev = curr;
                }
            }
        }
        return true;
    }

    private static void testIsConvex() {
        ArrayList<Point> square = new ArrayList<>();
        square.add(new Point(0, 0));
        square.add(new Point(0, 3));
        square.add(new Point(3, 3));
        square.add(new Point(3, 0));

        ArrayList<Point> hexagon = new ArrayList<>();
        hexagon.add(new Point(0, 0));
        hexagon.add(new Point(2, 0));
        hexagon.add(new Point(3, Math.sqrt(3)));
        hexagon.add(new Point(2, 2 * Math.sqrt(3)));
        hexagon.add(new Point(0, 2 * Math.sqrt(3)));
        hexagon.add(new Point(-1, Math.sqrt(3)));

        ArrayList<Point> heptagon = new ArrayList<>();
        heptagon.add(new Point(0, 0));
        heptagon.add(new Point(1, 0));
        heptagon.add(new Point(1.5, Math.sqrt(3) / 2));
        heptagon.add(new Point(1, Math.sqrt(3)));
        heptagon.add(new Point(0, Math.sqrt(3)));
        heptagon.add(new Point(-1, Math.sqrt(3) / 2));
        heptagon.add(new Point(-1.5, 0));

        ArrayList<Point> triangle = new ArrayList<>();

        triangle.add(new Point(0, 0));
        triangle.add(new Point(4, 0));
        triangle.add(new Point(2, 4));

        ArrayList<Point> square2 = new ArrayList<>();
        square2.add(new Point(0, 0));
        square2.add(new Point(0, 2));
        square2.add(new Point(1,1));
        square2.add(new Point(2, 2));
        square2.add(new Point(2, 0));


        ArrayList<Point> pentagon = new ArrayList<>();
        pentagon.add(new Point(0, 10));
        pentagon.add(new Point(5,5));
        pentagon.add(new Point(10,10));
        pentagon.add(new Point(10,0));
        pentagon.add(new Point(0, 0));


        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(0, 1));
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));


        System.out.println("Square : " + isConvex(square) + " should be true");
        System.out.println("Hexagon  " + isConvex(hexagon) + " should be true");
        System.out.println("Triangle " + isConvex(triangle)+ " should be true");
        System.out.println("Square wit a point in da middle " + isConvex(square2)+ " should be false");
        System.out.println("Pentagon: " + isConvex(pentagon)+ " should be false");
        System.out.println("Concave Shape " + isConvex(pentagon)+ " should be false");

    }


    private static void testSpeed(){
        ArrayList<Point> points = generatePolygonPointsRandom(100000);
        ArrayList<ArrayList<Point>> points2 = new ArrayList<>();
        for(long i = 1; i <= 100000000L; i*=10) {
            points2.add(generateNGon(i));
        }
        long start = System.nanoTime();
        isConvex(points);
        long end = System.nanoTime();
        System.out.println("Time: " + (end - start) + "ns");
        for (ArrayList<Point> pointArrayList : points2) {
            start = System.nanoTime();
            isConvex(pointArrayList);
            end = System.nanoTime();
            System.out.println("Size:" + pointArrayList.size() + "\nTime: " + (end - start) + "ns\n");
        }
    }

    public static ArrayList<Point> generatePolygonPointsRandom(int numberOfPoints) {
        ArrayList<Point> points = new ArrayList<>();
        for (int i = 0; i < numberOfPoints; i++) {
            int x = (int) (Math.random() * 100);
            int y = (int) (Math.random() * 100);
            points.add(new Point(x, y));
        }
        return points;
    }

    public static ArrayList<Point> generateNGon(long n) {
        int radius = 1000;
        ArrayList<Point> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            double x = radius * Math.cos(2 * Math.PI * i / n);
            double y = radius * Math.sin(2 * Math.PI * i / n);
            points.add(new Point(x, y));
        }
        return points;
    }
}



