public class OverLoadedMethods {
/* Same name different arguments*/
    public static void main(String []args){
        double num1 = 56;
        double num2 = 656;
        double num3 = 3;
        double num4;

        double result = add(num1, num2, num3);
        System.out.print(result);
    }

    static double add(double num1, double num2){
        return num1 + num2;
    }
    static double add(double num1, double num2, double num3){
        return num1 + num2 + num3;
    }
    static double add(double num1, double num2, double num3, double num4){
        return num1 + num2 + num3 + num4;
    }
}