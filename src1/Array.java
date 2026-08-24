public class Array {
    public static void main(String[] args) {

/*        String[] names = {"Wanga", "John", "Kat", "Spongebob"};

        int numOfNames = names.length;

        for (int i = 0; i < numOfNames; i++) {
            System.out.println(names[i]);
        }
*/
        String[] foods = new String[4]; //Assign array length

        foods[0] = "Pizza";
        foods[1] = "Taco";
        foods[2] = "Burger";
        foods[3] = "Wings";

        for(String food : foods){
            System.out.println(food);
        }
    }
}