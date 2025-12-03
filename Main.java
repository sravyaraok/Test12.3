// Sravya Jocelyn 12/3
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {

        String inputFile1 = "shakespeare.txt";
        String outputFile1 = "spUpper.txt";

        String inputFile2 = "mobydick.txt";
        String outputFile2 = "mdUpper.txt";

        processFile(inputFile1, outputFile1);
        processFile(inputFile2, outputFile2);
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
