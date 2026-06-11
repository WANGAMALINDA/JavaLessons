public class Phone {
    String brand;
    String color;

    Phone(String brand, String color) {
        this.color = color;
        this.brand = brand;
    }

    void show() {
        System.out.println("Phone brand: " + this.brand + ". Color: " + this.color + ".");
    }
}
