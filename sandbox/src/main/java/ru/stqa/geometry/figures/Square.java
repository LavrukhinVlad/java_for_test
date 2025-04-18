package ru.stqa.geometry.figures;

public record Square(double side) {


    public Square{
        if ( side < 0) {
            throw new IllegalArgumentException("Square side should be non-negative");
        }
    }

    public static void printSquareArea(Square s) {
        String text = String.format("Площадь квадрата со стороной %f = %f", s.side, s.area());
        System.out.println(text);
    }

    public double area() {
        return this.side * this.side;
    }

    public static void printSquarePerimeter(Square s) {
        String text = String.format("Периметр квадрата со стороной %f = %f", s.side, s.perimeter());
        System.out.println(text);
    }

    public double perimeter() {
        return 4 * this.side;
    }
}
