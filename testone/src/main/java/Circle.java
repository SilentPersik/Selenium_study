public class Circle implements Figure2D {

    private final double r;

    public Circle(double r) {
        this.r = r;
    }

    @Override
    public double area() {
        return Math.PI * r * r;    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * r;
    }
}