package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void canCalculatePerimeter() {
        var t  = new Triangle(7.0, 5.0, 3.0);
        double result = t.trianglePerimeter();
        Assertions.assertEquals(15.0, result);
}

    @Test
    void canCaclculateArea() {
        Assertions.assertEquals(6.49519052838329, new Triangle(7.0, 5.0, 3.0).triangleArea());
    }

    @Test
    void cannotCreateTriangleWithNegativeLengthSide () {
        try {
            new Triangle(7.0, 5.0, 1.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // OK
        }
    }

    @Test
    void testEquality() {
        var t1 = new Triangle(3.0, 4.0, 5.0);
        var t2 = new Triangle(3.0, 4.0, 5.0);
        Assertions.assertEquals(t1, t2);
    }

    @Test
    void testEquality2(){
        var a = 2;
        var b = 3;
        var c = 4;
        var t1 = new Triangle(a, b, c);
        var t2 = new Triangle(b, a, c);
        Assertions.assertEquals(t1, t2);
    }
}
