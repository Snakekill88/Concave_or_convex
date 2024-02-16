import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.testng.AssertJUnit.assertEquals;


public class Concave_Convex_test {

    @Test(dataProvider = "speedTests")
    private static void testSpeed(ArrayList<Point> points, boolean expected) {
        long start = System.nanoTime();
        Assert.assertEquals(Main.isConvex(points),expected);
        long end = System.nanoTime();
        System.out.println("Time: " + (end - start) + "ns");
    }

    @Test(dataProvider = "baseTests")
    public void testIsConvex(ArrayList<Point> points, boolean expected) {
        assertEquals(expected, Main.isConvex(points));
    }

    @DataProvider
    public static Object[][] baseTests() {
        return new Object[][]{
                //square
                {new ArrayList<>(Arrays.asList(new Point(0, 0), new Point(0, 3), new Point(3, 3), new Point(3, 0))), true},
                //hexagon
                {new ArrayList<>(Arrays.asList(new Point(0, 0), new Point(2, 0), new Point(3, Math.sqrt(3)), new Point(2, 2 * Math.sqrt(3)), new Point(0, 2 * Math.sqrt(3)))), true},
                //heptagon
                {new ArrayList<>(Arrays.asList(new Point(0, 0), new Point(1, 0), new Point(1.5, Math.sqrt(3) / 2), new Point(1, Math.sqrt(3)), new Point(0, Math.sqrt(3)), new Point(-1, Math.sqrt(3) / 2), new Point(-1.5, 0))), true},
                //triangle
                {new ArrayList<>(Arrays.asList(new Point(0, 0), new Point(4, 0), new Point(2, 4))), true},
                //square2
                {new ArrayList<>(Arrays.asList(new Point(0, 0), new Point(0, 3), new Point(3, 3), new Point(3, 0), new Point(1, 1))), false},
                //concave shape
                {new ArrayList<>(Arrays.asList(new Point(0, 0), new Point(2, 0), new Point(3, Math.sqrt(3)), new Point(2, 2 * Math.sqrt(3)), new Point(0, 2 * Math.sqrt(3)),new Point(1,1))), false},
                //concave shape with multiple concavity
                {new ArrayList<>(Arrays.asList(new Point(0, 0), new Point(10, 0), new Point(3,2), new Point(3,7), new Point(10, 10),new Point(0,10))), false},
                //concave shape with multiple concavity 2
                {new ArrayList<>(Arrays.asList(new Point(0, 0), new Point(10, 0), new Point(3,2),new Point(10,5), new Point(3,7), new Point(10, 10),new Point(0,10),new Point(5,5))), false},
                //concave shape with barely a concavity
                {new ArrayList<>(Arrays.asList(new Point(0, 0), new Point(0, 3),new Point(2.99999,1.5), new Point(3, 3), new Point(3, 0))), false},
                //Shape with 1000 Points
                {generateNGon(1000L), true},
        };
    }


    @DataProvider
    public static Object[][] speedTests() {
        return new Object[][]{
                //there's a tiny chance that this could fail but it's so small that it's not worth worrying about
                {generatePolygonPointsRandom(100000), false},
                {generateNGon(10L), true},
                {generateNGon(100L), true},
                {generateNGon(1000L), true},
                {generateNGon(10000L), true},
                {generateNGon(100000L), true},
                {generateNGon(1000000L), true},
                {generateNGon(10000000L), true},
                //would test more but due to the implementation the system runs out of memory
        };
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
