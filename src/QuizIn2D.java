import java.util.Scanner;

public class QuizIn2D {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] questions = { "What is java?",
                "What year was wanga born at?",
                "When is the first fifa 2026 game starting?",
                "Which team won the previous fifa cup?"
        };
        String[][] options = { { "1. A progamming laguage", "2. A hot beverage", "3. An IDE" },
                { "1. 2003", "2. 2004", "3. 2005", "4. 2006" },
                { "1. May 25 2026", "2. June 2nd 2026", "3. June 11th 2026", "4. July 1st 2026" },
                { "1. Spain", "2. Argentina", "3. South Africa", "4. England" }
        };

        int[] answers = { 1, 4, 3, 2 };
        int input;

        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);

            for (String option : options[i]) {
                System.out.println(option);
            }

            System.out.print("Enter your answer: ");
            input = sc.nextInt();
            if (input == answers[i]) {
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect!");
            }

            System.out.println();
        }
    }
}
