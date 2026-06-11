import java.util.Scanner;

public  class CompoundCal{
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        double principal;
        double amount;
        int compoundPeriod;
        double rate;
        int years;

        System.out.print("Enter the principal amount: ");
        principal = scanner.nextDouble();

        System.out.print("Enter the rate in %: ");
        rate = scanner.nextDouble() / 100;

        System.out.print("Enter # of years: ");
        years = scanner.nextInt();

        System.out.print("Enter compound period: ");
        compoundPeriod = scanner.nextInt();

        amount = principal * (Math.pow(1+rate/compoundPeriod, compoundPeriod*years));

        System.out.println("The amount of R" + principal + " after "+ years + " at a compound rate of " + rate + "% compounded " + compoundPeriod + " times" + " a year yields R" + amount + ".");

        scanner.close();

    }
}