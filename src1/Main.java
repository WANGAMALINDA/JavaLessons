import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		/*
		 * // Functions without constructor
		 * // Objects and attributes for a dog
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
		 * 
		 * // Interface (Prey -> chicken & fish; Predator -> Leopard & fish
		 * System.out.println();
		 * 
		 * Leopard leopard = new Leopard();
		 * leopard.hunt();
		 * 
		 * System.out.println();
		 * 
		 * Chicken chicken = new Chicken();
		 * chicken.flee();
		 * 
		 * System.out.println();
		 * 
		 * Fish fish = new Fish();
		 * fish.flee();
		 * fish.hunt();
		 * 
		 * System.out.println();
		 * 
		 * // Polymorphism (Vihecle): bus, train, bike
		 * 
		 * Train train = new Train();
		 * Bus bus = new Bus();
		 * Bike bike = new Bike();
		 * 
		 * Vehicle[] vehicles = { train, bus, bike };
		 * 
		 * for (Vehicle vehicle : vehicles) {
		 * vehicle.go();
		 * }
		 * 
		 * // runtime polymorphism -> when the executed method is decided at run time
		 * Animal animal;
		 * int choice = 3;
		 * 
		 * switch (choice) {
		 * case 1:
		 * animal = new Dog2();
		 * animal.speak();
		 * break;
		 * case 2:
		 * animal = new Cat();
		 * animal.speak();
		 * break;
		 * default:
		 * System.out.println("Animal not found");
		 * break;
		 * }
		 * 
		 * 
		 * // Gatters -> Methods that make a field readable
		 * // Settters -> Methos that make a field writeable
		 * // They help protect object data and add rules for accessing and or modifying
		 * // them
		 * 
		 * Car car = new Car("BMW", "M4", 2026, 250000);
		 * System.out.println(car.getBrand());
		 * System.out.println(car.getModel());
		 * System.out.println(car.getYear());
		 * System.out.println("R" + car.price);
		 * 
		 * System.out.println();
		 * car.setPrice(200000.54);
		 * 
		 * System.out.println(car.getBrand());
		 * System.out.println(car.getModel());
		 * System.out.println(car.getYear());
		 * System.out.println("R" + car.price);
		 * 
		 * 
		 * // Aggregation -> Represents "has-a" relationship between objects, meaning
		 * that
		 * // one object contains another object as part of its structure, but contained
		 * // object/s can exist independently.
		 * // Library -> Books
		 * 
		 * Book book1 = new Book("John Craig", "Java Expo", 200);
		 * Book book2 = new Book("Peter Parker", "Python Utils", 450);
		 * Book book3 = new Book("Sam Smith", "Maps Of The World", 243);
		 * Book book4 = new Book("Wanga Malinda", "Jorney to success", 633);
		 * 
		 * System.out.println();
		 * 
		 * Book[] books = { book1, book2, book3, book4 };
		 * 
		 * Library library = new Library("Pretoria Main Library", 1998, books);
		 * library.displayInfo();
		 * 
		 * // Composition -> Represents a "part-of" relationship between objects
		 * Person person = new Person("Wanga", 20, "CSE");
		 * 
		 * System.out.println(person.name);
		 * System.out.println(person.age);
		 * System.out.println(person.heart.type);
		 * 
		 * // Wrapper Classes (Auto boxing and Unboxing)
		 * 
		 * Integer a = 12;
		 * Double b = 12.5;
		 * Character c = 'A';
		 * Boolean d = true;
		 * 
		 * // Unboxing -> removing
		 * 
		 * int x = a;
		 * double y = b;
		 * char z = c;
		 * boolean w = d;
		 * 
		 * //String variableName = valueDataType.toString(value)
		 * String num = Integer.toString(2005);
		 */
		// ArrayList<dataType> = new ArrayList<>;
		// .add(), .remove(index), .set(index, value), System.out.println(.get(index)),
		// System.out.println(.size()),

		/*
		 * String name = "Wanga";
		 * int year = 2025;
		 * double gpa = 3.5;
		 * 
		 * String a = Integer.toString(year);
		 * String b = Double.toString(gpa);
		 * 
		 * ArrayList<String> list = new ArrayList<>();
		 * 
		 * list.add(name);
		 * list.add(a);
		 * list.add(b);
		 * 
		 * ArrayList<String> Info = new ArrayList<>();
		 * ArrayList<String> Labels = new ArrayList<>();
		 * 
		 * String a = "Name: ";
		 * String b = "Surname: ";
		 * String c = "Campus: ";
		 * String d = "Age: ";
		 * String e = "GPA: ";
		 * 
		 * String name = "Wanga";
		 * String surname = "Malinda";
		 * int age = 20;
		 * double gpa = 3.3;
		 * String campus = "Pretoria CBD";
		 * String ageToStr = Integer.toString(age);
		 * String gpaToString = Double.toString(gpa);
		 * 
		 * Info.add(name);
		 * Info.add(surname);
		 * Info.add(campus);
		 * Info.add(ageToStr);
		 * Info.add(gpaToString);
		 * 
		 * Labels.add(a);
		 * Labels.add(b);
		 * Labels.add(c);
		 * Labels.add(d);
		 * Labels.add(e);
		 * 
		 * for (int i = 0; i < Info.size(); i++) {
		 * System.out.print(Labels.get(i));
		 * System.out.println(Info.get(i));
		 * }
		 * 
		 * // Exception uses try{}, catch{}, finally{}
		 * 
		 * try (Scanner scan = new Scanner(System.in)) {
		 * //
		 * System.out.print("Enter your name: ");
		 * int name = scan.nextInt();
		 * //
		 * } catch (InputMismatchException e // Catch invalid input data type error ) {
		 * //
		 * System.out.println("You entered an invalid input type!");
		 * //
		 * } catch (ArithmeticException e // mathematical invalid value eg /0 ) {
		 * //
		 * System.out.println("You entered an invalid name!");
		 * //
		 * } catch (Exception e) {
		 * //
		 * System.out.println("Something went wrong!!");
		 * //
		 * }
		 * 
		 * finally {
		 * // Finally is optional, it always execute!
		 * scanner.close();
		 * System.out.println("This always executes!!");
		 * }
		 * File handling 1. File writting
		 * 
		 * 
		 * String location = "C:\\Users\\wanga\\Documents\\Java\\2\\Data.txt";
		 * String text = "Hello world, I love y'all";
		 * //
		 * try (FileWriter writer = new FileWriter(location)) {
		 * writer.write(text);
		 * System.out.println("File is written!");
		 * } catch (IOException e) {
		 * System.out.println("Could not write file!");
		 * }
		 * 
		 * // Reading file, Buffer reader + file reader
		 * // FileInputStream: Best for binary files (image, videos, audio)
		 * // RandomAccessFile: Best for read/write specific portions of large files
		 * 
		 * String ReadLocation = location;
		 * try (BufferedReader reader = new BufferedReader(new
		 * FileReader(ReadLocation))) {
		 * 
		 * System.out.println("Reading file!!");
		 * String line;
		 * 
		 * while ((line = reader.readLine()) != null) {
		 * System.out.println(line);
		 * }
		 * } catch (IOException e) {
		 * System.out.println("Cannot read file!!");
		 * }
		 * 
		 * //Threading (Timed task) multiple task simulataneously
		 * 
		 * 
		 * MyRunnable runnable = new MyRunnable();
		 * Thread thread = new Thread(runnable);
		 * thread.setDaemon(true);
		 * thread.start();
		 * 
		 * System.out.println("You have 5 seconds to enter your name");
		 * System.out.print("Enter your name: ");
		 * String name = scanner.nextLine();
		 * 
		 * System.out.println("Hello, " + name);
		 * 
		 * // MultiThrading
		 * for (int i = 1; i <= 3; i++) {
		 * Multithread myThread = new Multithread();
		 * myThread.start();
		 * }
		 * Test
		 */
		String filename = "app_logs.txt";

		// Polymorphism: Holding a FileLogger in a Logger reference
		Logger myLogger = new FileLogger("massages", filename);

		// Fixed-size Array of initial logs
		String[] initialLogs = {
				"System initialized.",
				"Database connection established.",
				"User login successful."
		};

		// ArrayList topic: Dynamically managing multiple runners
		ArrayList<LogRunner> runners = new ArrayList<>();

		// Creating the background logging thread
		LogRunner thread1 = new LogRunner(myLogger, initialLogs);
		runners.add(thread1);

		System.out.println("Starting background logging thread...");
		// Bug 7: How do you properly start a thread so it runs concurrently?
		runners.get(0).run();

		// Wait for the thread to finish before reading the file
		try {
			runners.get(0).join();
		} catch (InterruptedException e) {
			e.printStackTrace();

		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Out of bound");
		}

		// Read the logs back using downcasting (Polymorphism)
		if (myLogger instanceof FileLogger) {
			((FileLogger) myLogger).readLogs();
		}

		scanner.close();
	}
}