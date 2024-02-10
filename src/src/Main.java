import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
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
}



