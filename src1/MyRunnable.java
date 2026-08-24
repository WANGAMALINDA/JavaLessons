public class MyRunnable implements Runnable {
    @Override
    public void run() {

        /*
         * for (int i = 0; i <= 5; i++) {
         * 
         * try {
         * Thread.sleep(1000);
         * } catch (Exception e) {
         * System.out.println("Something went wrong!");
         * }
         * if (i == 5) {
         * System.out.println("Time is up!");
         * System.exit(0);
         * }
         * }
         */
        // MultiThreading
        for (int i = 1; i <= 5; i++) {

            try {
                Thread.sleep(1000);
                System.out.println(i);
            } catch (Exception e) {
                System.out.println("Something went wrong!");
            }
            if (i == 5) {
                System.out.println("Time is up!");
                System.exit(0);
            }
        }
    };
}