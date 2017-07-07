/**
 * Created by uasso on 30/06/2017.
 */
public class Point {
    double x;
    double y;

    public Point(double a, double b) {
        this.x = a;
        this.y = b;

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public  double distanceFrom( Point p2) {
        return Math.sqrt((this.x - p2.x) * (this.x - p2.x) + (this.y - p2.y) * (this.y - p2.y));

    }

}