import java.util.Scanner;

public class TernaryOperator{
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        String Out = (age > 0 && age <=18) ? "You are a child" : "You are a grown up";

        System.out.println(Out);

        scanner.close();
    }
}