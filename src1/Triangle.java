public class Triangle extends Shape {

    double h = 10;
    double base = 7;

    Triangle(double h, double base) {
        this.h = h;
        this.base = base;
    }

    @Override
    double area() {
        return (this.h * this.base) / 2;
    }

}
