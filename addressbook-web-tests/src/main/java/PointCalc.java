import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Svetlana Verkholantceva  on 03/07/2017.
 * task 2:
 * 1. Создать класс Point для представления точек на двумерной плоскости. Объекты этого класса должны содержать два атрибута, которые соответствуют координатам точки на плоскости.

 2. Создать функцию
 public static double distance(Point p1, Point p2)
 которая вычисляет расстояние между двумя точками. Для вычисления квадратного корня можно использовать функцию Math.sqrt

 3. Сделать запускаемый класс, то есть содержащий функцию
 public static void main(String[] args) {...}
 и при помощи него убедиться, что функция вычисления расстояния между точками действительно работает. Результат вычисления выводить на экран и контролировать визуально.

 4. Реализовать то же самое (вычисление расстояния между двумя точками) при помощи метода в классе Point, и добавить в созданный в предыдущем пункте запускаемый класс примеры использования метода вместо ранее созданной функции
 */
public class PointCalc {


    public static void main(String[] args) {
        Point p1 = new Point(1,1);
        Point p2 = new Point(5,4);
        System.out.println("p1.x = "+ p1.x+ " p1.y = "+ p1.y );

       // System.out.println("distance is "+ distance(p1, p2));
        Assert.assertEquals(distance(p1, p2), 5.0);
        System.out.println("distance from method is "+p1.distanceFrom(p2));
    }


    // **  вычисляет расстояние между двумя точками.
    public static double distance(Point p1, Point p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));

    }


}
