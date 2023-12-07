
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Menu {

    public static void menu(String argument, Scanner scanner) throws IOException {
        Path path = Paths.get(argument).toAbsolutePath().normalize();
        CheckerFile.checkFile(path.toFile());
        FileManager fileManager = new FileManager(path.toFile());
        String[] string;
        while (scanner.hasNext()) {
            string = scanner.nextLine().split("\\s");
            switch (string[0]) {
                case "ls" -> fileManager.printFiles();
                case "cd" -> {
                    fileManager.setFile(string[1]);
                }
                case "mv" -> {
                    fileManager.moveFiles(string[1], string[2]);
                }
                case "touch" -> {
                    fileManager.touchFile(string[1]);
                }
                case "rm" -> {
                    fileManager.removeFile(string[1]);
                }
                case "mkdir" -> {
                    fileManager.mkdir(string[1]);
                }
                case "pwd" -> {
                    fileManager.pwd();
                }
                case "42" -> {
                    return;
                }
                default -> {
                    System.out.println("Not command line!");
                }
            }
        }
    }
}
