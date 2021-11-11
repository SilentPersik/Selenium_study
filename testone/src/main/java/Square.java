public class Square implements Figure2D {

    private final double s;

    public Square(double s) {
        this.s = s;
    }

    @Override
    public double area() {
        return s * s;
    }

    @Override
    public double perimeter() {
        return 4 * s;
    }
}
