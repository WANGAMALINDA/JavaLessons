public class Car {

    String brand;
    String model;
    int year;
    static int numOfCars;
    double price;

    Car(String brand, String model, int year, double price) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        numOfCars++;
    }

    void run() {
        System.out.println("Car is starting...");
    }

    static void showNumOfCars() {
        System.out.println("You have a total of " + numOfCars + " Cars.");
    }

    @Override
    public String toString() {
        return this.brand + " " + this.model + " " + this.year + " " + this.price;
    }

}
