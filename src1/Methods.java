/*public class Methods{
    public static void main(String[] args){
        String name = "john";

        song(name);
    }

}*/

public class Methods{
    public static void main(String[] args){

        String result = fullName("Wanga", "Malinda");
        System.out.print(result);
    }

    static void song(String name){
        System.out.println("Happy birthday " + name);
        System.out.println("Happy birthday " + name);
        System.out.println("Happy birthday " + name);
        System.out.println("How old are you now");
        System.out.println("How old are you now");
    }

    static double squaredNum(double number){

        return number * number;
    }

    static String fullName(String first, String last){

        return "Your full name is " + first + " " + last;
    }

}