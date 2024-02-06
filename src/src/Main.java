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


//        public static boolean isConvex(ArrayList<Point> points) {
//            boolean angle1 = false;
//            boolean angle2 = false;
//            for (int i = 0; i < points.size(); i++) {
//                int next = i + 1 < points.size() ? i + 1 : 0;
//                int prev = i - 1 >= 0 ? i - 1 : points.size() - 1;
//                double result = calculateAngle(points.get(prev), points.get(i), points.get(next));
//                if (!angle1 && result > 180) {
//                    angle1 = true;
//                }
//                if (!angle2 && Math.abs(result - 360) > 180) {
//                    angle2 = true;
//                }
//                if (angle1 && angle2) {
//                    i = Integer.MAX_VALUE;
//                }
//            }
//            if (angle1 && angle2) {
//                return true;
//            } else {
//                return false;
//
//            }
//        }


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
        long start = System.currentTimeMillis();
        isConvex(points);
        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start) + "ms");
        for (ArrayList<Point> pointArrayList : points2) {
            start = System.currentTimeMillis();
            isConvex(pointArrayList);
            end = System.currentTimeMillis();
            System.out.println("Size:" + pointArrayList.size() + "\nTime: " + (end - start) + "ms\n");
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

//    private static double calculateAngle(Point p1, Point p2, Point p3) {
//        Vector vector1 = new Vector(p2.x-p1.x,p2.y-p1.y);
//        Vector vector2 = new Vector(p2.x-p3.x,p2.y-p3.y);
//
//        return Math.toDegrees(Math.acos(vector1.dotProduct(vector2)/(vector1.length()*vector2.length())));
//    }
}



