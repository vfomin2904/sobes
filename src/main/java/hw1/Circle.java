package hw1;

public class Circle extends Figure{

    private double radius;

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    double square() {
        return Math.PI * radius * radius;
    }
}
