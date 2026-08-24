import java.io.*;

class FileLogger extends Logger {
    private String fileName;

    // Constructor
    public FileLogger(String name, String fileName) {
        // Bug 1: Something is missing here to properly initialize the parent class
        super(fileName);
        this.fileName = fileName;
    }

    // Overriding base class method
    @Override
    public void logMessage(String message) {
        System.out.println("[" + loggerName + "] Writing to file: " + message);

        // File Writing with Exception Handling
        try {
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(message + "\n");
            // Bug 2: Missing something crucial to ensure the text actually saves to the
            // file right now
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Method to read back logs
    public void readLogs() {
        System.out.println("\n--- Reading Logs from " + fileName + " ---");
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Log file not found yet!");
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        }
        // Bug 3: A standard try-with-resources or try-catch block here is missing a
        // mandatory catch/finally if handled improperly, or is this one okay? (Hint:
        // Check compile errors)
    }
}