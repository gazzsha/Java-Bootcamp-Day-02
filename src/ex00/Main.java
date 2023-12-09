
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final String pathSource = "text.txt";
        final String pathStore = "result";
        String path = "";
        try (Scanner scanner = new Scanner(System.in)) {
            path = scanner.nextLine();
            while (!path.equals("42")) {
                try (InputStream fileInputStream = new FileInputStream(pathSource);
                     InputStream fileInput = new FileInputStream(path);
                     OutputStream fileOutput = new FileOutputStream(pathStore,true)) {
                    FileReader fileReader = new FileReader();
                    fileReader.readTokens(fileInputStream);
                    fileReader.findExecution(fileInput, fileOutput);
                    System.out.println("PROCESSED");
                } catch (FileNotFoundException exception) {
                    System.out.println(exception.toString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                path = scanner.nextLine();
            }
        }
    }
}