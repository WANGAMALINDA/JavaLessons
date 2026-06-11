import java.util.Scanner;

public class ShoppingCart {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("What do you want to buy?: ");
        String item = scanner.nextLine();

        System.out.print("How much per item?: ");
        double price = scanner.nextDouble();

        System.out.print("Number of items: ");
        int quantity = scanner.nextInt();

        double totalPrice = price * quantity;

        System.out.println("---------------------------------------");
        System.out.println("               Receipt                 ");
        System.out.println("---------------------------------------");
        System.out.println("Item: " + item);
        System.out.println("Quantity: " + quantity);
        System.out.println("Price per item: R" + price);
        System.out.println("Total price: R" + totalPrice);
        System.out.println("---------------------------------------");


        scanner.close();

    }
}