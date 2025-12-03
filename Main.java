// Sravya Jocelyn 12/3
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        String inputFile1 = "shakespeare.txt";
        String outputFile1 = "spUpper.txt";

        String inputFile2 = "mobydick.txt";
        String outputFile2 = "mdUpper.txt";

        // Create a thread pool with 2 threads
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Submit both tasks (they now run at the same time)
        executor.submit(() -> processFile(inputFile1, outputFile1));
        executor.submit(() -> processFile(inputFile2, outputFile2));

        // Proper shutdown
        executor.shutdown();

        try {
            // Wait for all tasks to finish
            if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }

    private static void processFile(String inputFile, String outputFile) {
        Path inputPath = Path.of(inputFile);
        Path outputPath = Path.of(outputFile);

        try {
            long start = System.nanoTime();

            // read file
            String content = Files.readString(inputPath);

            long dummyCount = 0;
            for (int i = 0; i < content.length(); i++) {
                char c = content.charAt(i);
                dummyCount += c; // meaningless work to slow things down
            }

            // uppercase conversion
            String upper = content.toUpperCase();

            // write file
            Files.writeString(outputPath, upper);

            long end = System.nanoTime();
            long duration = end - start;

            System.out.println("Processed: " + inputFile);
            System.out.println("Output:    " + outputFile);
            System.out.println("Dummy count (ignore): " + dummyCount);
            System.out.println("Time (ns): " + duration);
            System.out.println("Time (ms): " + duration / 1_000_000.0);
            System.out.println("--------------------------------------------");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
