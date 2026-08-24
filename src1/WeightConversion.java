import java.util.Scanner;

public class WeightConversion{
    public static void main(String [] args){

        Scanner scanner = new Scanner(System.in);

        System.out.println("----------------------");
        System.out.println("---Weight Converter---");
        System.out.println("----------------------");
        System.out.println();
        System.out.println("Option 1: Convert lbs to kgs");
        System.out.println("Option 2: Convert kgs to lbs");
        System.out.println("----------------------");

        System.out.print("Enter option: ");
        int option = scanner.nextInt();

        if(option == 1){
            System.out.print("Enter weight in lbs: ");
            double weight = scanner.nextDouble();

            double ConvertedWeight = weight * 0.453592;
            System.out.println("Weight in kgs = " + ConvertedWeight + " kgs");
        }
        else if(option == 2){
            System.out.print("Enter weight in kgs: ");
            double weight = scanner.nextDouble();

            double ConvertedWeight = weight * 2.20462;
            System.out.println("Weight in lbs = " + ConvertedWeight + " lbs");
        }
        else{
            System.out.print("Entered invalid option");
        }

        scanner.close();
    }
}