package ru.stqa.geometry.figures;

public class Triangle {

    private double a;
    private double b;
    private double c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public static void printTrianglePerimeter(double a, double b, double c) {
        var text = String.format("Периметр треугольника со сторонами %f, %f и %f = %f", a, b, c, trianglePerimeter(a, b, c));
        System.out.println(text);
    }
    public static double trianglePerimeter(double a, double b, double c) {
        return a + b + c;
    }

    public static void printTriangleleArea(double a, double b, double c) {
        var text = String.format("Площадь треугольника со сторонами %f, %f и %f = %f", a, b, c, triangleArea(a, b, c));
        System.out.println(text);
    }

    public static double triangleArea(double a, double b, double c) {
        double polu = (a + b + c) / 2d;
        double s = polu * (polu - a) * (polu - b) * (polu - c);
        return Math.sqrt(s);
    }
}