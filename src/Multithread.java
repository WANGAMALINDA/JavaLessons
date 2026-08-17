import java.util.concurrent.ExecutionException;

public class Multithread extends Thread {

    @Override
    public void run() {
        for (int i = 1; i <= 7; i++) {

            try {
                Thread.sleep(1000);
                System.out.println(i);

            } catch (Exception e) {
                System.out.println("Something went wrong!!");
            }

            if (i == 7) {
                System.out.println("Time's up!!!!");
                break;
            }
        }
    }
}
