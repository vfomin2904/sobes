package hw1;

public class Triangle extends Figure{

    private double height;
    private double length;

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setLength(double length, double height) {
        this.length = length;
        this.height = height;
    }

    @Override
    public double square() {
        return 0.5 * length * height;
    }
}
