import java.io.*;
import java.util.ArrayList;

// --- BASE CLASS: Logger ---
class Logger {
    protected String loggerName;

    // Constructor
    public Logger(String name) {
        this.loggerName = name;
    }

    public void logMessage(String message) {
        System.out.println("[" + loggerName + "] General Log: " + message);
    }
}
