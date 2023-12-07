
import java.io.IOException;
import java.util.Scanner;

public class Main {


    public static void run(String argument) {
        try (Scanner scanner = new Scanner(System.in)) {
            try {
                Menu.menu(argument, scanner);
            } catch (IOException ioException) {
                System.out.println(ioException.toString());
                System.out.println("Write a directory");
                run(scanner.nextLine());
            }
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No path");
            System.exit(-1);
        }
        run(args[0]);
    }
}