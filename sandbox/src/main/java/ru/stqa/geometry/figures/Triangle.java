package ru.stqa.geometry.figures;

public record Triangle(double a, double b, double c) {

    public Triangle {
        if (a < 0 || b < 0 || c < 0) {
            throw new IllegalArgumentException("Triangle length side should be non-negative");
        }
        if (a + b < c || b + c < a || c + a < b) {
            throw new IllegalArgumentException("The triangle inequality is violated");
        }
    }

    public static void printTrianglePerimeter(Triangle t) {
        var text = String.format("Периметр треугольника со сторонами %f, %f и %f = %f", t.a, t.b, t.c, t.trianglePerimeter());
        System.out.println(text);
    }

    public static void printTriangleleArea(Triangle t) {
        var text = String.format("Площадь треугольника со сторонами %f, %f и %f = %f", t.a, t.b, t.c, t.triangleArea());
        System.out.println(text);
    }

    public double trianglePerimeter() {
        return this.a + this.b + this.c;
    }

    public double triangleArea() {
        double polu = (this.a + this.b + this.c) / 2d;
        double s = polu * (polu - this.a) * (polu - this.b) * (polu - this.c);
        return Math.sqrt(s);
    }
}