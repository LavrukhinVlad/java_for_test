package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void canCalculatePerimeter() {
        Assertions.assertEquals(15.0, Triangle.trianglePerimeter(7.0, 5.0, 3.0));
}

    @Test
    void canCaclculateArea() {
        Assertions.assertEquals(6.49519052838329, Triangle.triangleArea(7.0, 5.0, 3.0));
    }
}
