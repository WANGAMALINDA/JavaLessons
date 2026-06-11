import java.util.Scanner;

public class Substring{
    public static void main(String [] args){

        String email = "wanga2@yahoo.com";

        String username = email.substring(0, email.indexOf("@"));
        String domain = email.substring(email.indexOf("@") + 1);

        System.out.print(domain);
    }
}