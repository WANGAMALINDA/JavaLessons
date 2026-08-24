public class secondD {
    public static void main(String[] args) {

        char[] row0 = { '1', '2', '3' };
        char[] row1 = { '4', '5', '6' };
        char[] row2 = { '7', '8', '9' };
        char[] row3 = { '*', '0', '#' };

        char[][] rows = { row0, row1, row2, row3 };

        for (char[] row : rows) {

            for (char button : row) {
                System.out.print(button + " ");
            }

            System.out.println();
        }
    }
}
