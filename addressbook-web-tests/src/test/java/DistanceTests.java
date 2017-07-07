import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Svetlana Verkholantceva on 05/07/2017.
 */
public class DistanceTests {

    @Test
    public void Test1() {
            Point p1 = new Point(1,1);
            Point p2 = new Point(5,4);
            System.out.println("p1.x = "+ p1.x+ " p1.y = "+ p1.y );

            // System.out.println("distance is "+ distance(p1, p2));
            Assert.assertEquals(distance(p1, p2), 5.0);
            System.out.println("distance from method is "+p1.distanceFrom(p2));
    }

    @Test
    public void Test2() {
            Point p1 = new Point(1,1.5);
            Point p2 = new Point(6.3,7);
            System.out.println("p1.x = "+ p1.x+ " p1.y = "+ p1.y );
            System.out.println("distance is "+ distance(p1, p2));
            Assert.assertEquals(Math.round(distance(p1, p2)), 8);

    }

        // **  вычисляет расстояние между двумя точками.
        public static double distance(Point p1, Point p2) {
            return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));

        }




}
