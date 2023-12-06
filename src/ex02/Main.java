
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

// D:\VirtualBox\UICommon.dll
public class Main {


    public static void run(String argument) {
        try (Scanner scanner = new Scanner(System.in)) {
        try {
            Menu.menu(argument,scanner);
        } catch (IOException ioException) {
            System.out.println(ioException.toString());
                System.out.println("Write a directory");
                run(scanner.nextLine());
            }
        }
    }

    public static void main(String[] args) {
        run(args[0]);
    }
}