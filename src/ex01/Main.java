import java.io.*;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        final String pathOfFirst = args[0];
        final String pathOfSecond = args[1];
        final String dictionary = "dictionary.txt";
        FileSystem fileSystem = new FileSystem();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathOfFirst, StandardCharsets.UTF_8));
             BufferedReader bufferedReader1 = new BufferedReader(new FileReader(pathOfSecond, StandardCharsets.UTF_8));
             PrintWriter printWriter= new PrintWriter(new BufferedWriter(new FileWriter(dictionary,StandardCharsets.UTF_8)))) {
            fileSystem.ReadFromFiles(bufferedReader, bufferedReader1,printWriter);
            System.out.format("%.2f\n", fileSystem.similarity());
        } catch (IOException ioException) {
            System.out.println(ioException.toString());
        }
    }
}