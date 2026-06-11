import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        /*
         * //Functions without constructor
         * Objects and attributes for a dog
         * Dog dog = new Dog();
         * dog.name = "Peter";
         * dog.age = 10;
         * dog.height = 2.1;
         * dog.gender = "Male";
         * dog.color = "White";
         * dog.run();
         * 
         * Dog dog1 = new Dog();
         * dog1.name = "Samantha";
         * dog1.age = 2;
         * dog1.height = 1.1;
         * dog1.gender = "Female";
         * dog1.color = "White";
         * dog1.bark();
         * 
         * System.out.println("Name: " + dog.name);
         * System.out.println("Color: " + dog.color);
         * System.out.println("Gender: " + dog.gender);
         * System.out.println("Age: " + dog.age);
         * System.out.println("Height: " + dog.height);
         * 
         * System.out.println("===================================");
         * System.out.println("Name: " + dog1.name);
         * System.out.println("Color: " + dog1.color);
         * System.out.println("Gender: " + dog1.gender);
         * System.out.println("Age: " + dog1.age);
         * System.out.print("Height: " + dog1.height);
         * 
         * //constructors for students class
         * 
         * Students student1 = new Students("Wanga", 30, 4.0);
         * Students student2 = new Students("Spongebob", 23, 2.5);
         * 
         * System.out.println(student1.name + " is " + student1.age +
         * " years old with a GPA of " + student1.gpa + ".");
         * System.out.println(student2.name + " is " + student2.age +
         * " years old with a GPA of " + student2.gpa + ".");
         * 
         * //OOP without a constructor
         * 
         * System.out.print("Choose car: ");
         * int option = scanner.nextInt();
         * 
         * if (option == 1) {
         * Car car1 = new Car("VW", "Polo TSI", 2024, 450000.56);
         * 
         * System.out.println("Car brand: " + car1.brand);
         * System.out.println("Car model: " + car1.model);
         * System.out.println("Year: " + car1.year);
         * System.out.println("Car price: R" + car1.price);
         * 
         * car1.run();
         * } else {
         * 
         * Car car2 = new Car("Toyota", "Hilux", 2005, 50000);
         * 
         * System.out.println("Car brand: " + car2.brand);
         * System.out.println("Car model: " + car2.model);
         * System.out.println("Year: " + car2.year);
         * System.out.println("Car price: R" + car2.price);
         * 
         * car2.run();
         * }
         * 
         * 
         * // Over loaded constructors
         * User user1 = new User("JohnCraig", "Johncraig@gmail.com");
         * System.out.println("User Name: " + user1.username);
         * System.out.println("User Email: " + user1.email);
         * 
         * System.out.println();
         * 
         * User user2 = new User("JohnDoe", "Johndoe@gmail.com", 21213232, 'M');
         * System.out.println("User Name: " + user2.username);
         * System.out.println("User Email: " + user2.email);
         * System.out.println("User ID: " + user2.ID);
         * System.out.println("User Gender: " + user2.gender);
         * 
         * System.out.println();
         * 
         * User user3 = new User("PeterSam", "Petersam@gmail.com", 34343232);
         * System.out.println("User Name: " + user3.username);
         * System.out.println("User Email: " + user3.email);
         * System.out.println("User ID: " + user3.ID);
         * 
         * //Array of objects
         * Phone phone1 = new Phone("Honor 200 lite", "Black");
         * Phone phone2 = new Phone("Honor 400", "Blue");
         * Phone phone3 = new Phone("Honor 600", "White");
         * 
         * Phone[] Phone = { phone1, phone2, phone3 };
         * 
         * for (Phone phone : Phone) {
         * phone.show();
         * 
         * }
         * 
         * // Static
         * Car car1 = new Car("VW", "Polo TSI"[, 2024,] 450000.56);
         * Car car2 = new Car("Toyota", "Hilux", 2005, 50000);
         * Car car3 = new Car("BMw", "M4", 2025, 2050000);
         * System.out.println();
         * 
         * Car.showNumOfCars();
         * 
         * System.out.println();
         * 
         * // Inheritence (Human; Male; Female)
         * 
         * Male male = new Male();
         * male.walk();
         * male.speak();
         * System.out.println();
         * 
         * Female female = new Female();
         * female.walk();
         * female.speak();
         * 
         * // toString() method
         * Car car1 = new Car("BMW", "M4", 2025, 450000);
         * System.out.println(t);
         * 
         * Car car2 = new Car("Mercedes", "G63", 2026, 650000);
         * System.out.println(car2);
         * 
         * //abstract
         * Circle circle = new Circle(3);
         * 
         * System.err.println(circle.area());
         * 
         * Triangle triangle = new Triangle(10, 7);
         * System.out.println(triangle.area());
         * 
         * Rectangle rectangle = new Rectangle(5, 15);
         * System.out.println(rectangle.area());
         */
        // Interface (Prey -> chicken & fish; Predator -> Leopard & fish
        System.out.println();

        Leopard leopard = new Leopard();
        leopard.hunt();

        System.out.println();

        Chicken chicken = new Chicken();
        chicken.flee();

        System.out.println();

        Fish fish = new Fish();
        fish.flee();
        fish.hunt();

        System.out.println();
    }
}