import java.util.Scanner;

public class Test{
    public static void main(String [] args ){

        Scanner scanner = new Scanner(System.in);
        int age;

        System.out.print("Enter your age: ");
        age = scanner.nextInt();

        if(age == 0){
            System.out.print("Your are a baby");
        }
        else if(age < 0){
            System.out.print("Invalid age");
        }
        else if(age <= 10){
            System.out.print("YOu are a child");
        }
        else if( age > 10 && age <= 18 ){
            System.out.print("You are a minor");
        }
        else if(age > 18){
            System.out.print("You are old enough");
        }

        scanner.close();

    }
}