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
}
