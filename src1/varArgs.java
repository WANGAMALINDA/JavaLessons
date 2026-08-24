public class varArgs {
    public static void main(String[] args) {
        System.out.println(average(2, 4, 4, 6, 7));
    }

    static double average(double... numbers) {
        double sum = 0;

        for (double number : numbers) {
            sum += number;
        }
        return sum / numbers.length;
    }
}