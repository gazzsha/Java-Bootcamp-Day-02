import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
                    Path newDir = path.resolve(string[1]).normalize();
                    fileManager.setFile(newDir.toFile());
                    path = newDir;
                }
                case "mv" -> {
                    Path source = path.resolve(string[1]).normalize();
                    Path newDir = path.resolve(string[2]).normalize();
                    if (CheckerFile.checkFileWithoutException(source.toFile())) {
                        if (!CheckerFile.checkDirectory(newDir.toFile()) && source.getParent().equals(newDir.getParent())) {
                            Files.move(source, source.resolveSibling(string[2]));
                        } else {
                            newDir = newDir.resolve(string[1]).normalize();
                            if (CheckerFile.checkDirectory(newDir.getParent().toFile())) {
                                Files.move(source, newDir, REPLACE_EXISTING);
                            }
                        }
                    }
                }
                case "touch" -> {
                    Files.createFile(path.resolve(string[1]).normalize());
                }
                case "rm" -> {
                    Path rmFile = path.resolve(string[1]).normalize();
                    if (!CheckerFile.checkDirectory(rmFile.toFile())) {
                        Files.deleteIfExists(rmFile);
                    }
                }
                case "mkdir" -> {
                    Files.createDirectory(path.resolve(string[1]).normalize());
                }
                case "pwd" -> {
                    System.out.println(path.toAbsolutePath());
                }
                default -> {
                    System.out.println("Not command line!");
                }
            }
        }
    }
}
