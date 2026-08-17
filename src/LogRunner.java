class LogRunner extends Thread {
    private Logger logger;
    private String[] logArray; // Array topic

    public LogRunner(Logger logger, String[] messages) {
        this.logger = logger;
        this.logArray = messages;
    }

    // Bug 5: Is this the correct method name to override for running a thread?
    public void run() {
        // Loop through the fixed array
        for (int i = 0; i <= logArray.length; i++) { // Bug 6: Watch the loop boundaries closely!
            logger.logMessage(logArray[i]);
            try {
                Thread.sleep(500); // Sleep for half a second
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted.");
            }

            if (i == logArray.length) {
                break;
            }
        }
    }
}