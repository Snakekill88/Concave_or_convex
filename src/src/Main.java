import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) {

    }

    public static boolean isConvex(ArrayList<Point> points) {
        boolean angle1 = false;
        boolean angle2 = false;
        for (int i = 0; i < points.size(); i++) {
            int next = i + 1 < points.size() ? i + 1 : 0;
            int prev = i - 1 >= 0 ? i - 1 : points.size() - 1;
            double result = calculateAngle(points.get(prev), points.get(i), points.get(next));
            if (!angle1 && result > 180) {
                angle1 = true;
            }
            if (!angle2 && Math.abs(result - 360) > 180) {
                angle2 = true;
            }
            if (angle1 && angle2) {
                i = Integer.MAX_VALUE;
            }
        }
        if (angle1 && angle2) {
            return true;
        } else {
            return false;

        }
    }

    private static double calculateAngle(Point p1, Point p2, Point p3) {
        Vector vector1 = new Vector(p2.x-p1.x,p2.y-p1.y);
        Vector vector2 = new Vector(p2.x-p3.x,p2.y-p3.y);

        return Math.toDegrees(Math.acos(vector1.dotProduct(vector2)/(vector1.length()*vector2.length())));
    }
}
