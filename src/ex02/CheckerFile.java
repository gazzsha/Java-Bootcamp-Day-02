import java.io.File;
import java.io.IOException;

public class CheckerFile {
    public static void checkFile(File file) throws IOException {
        if (!file.exists()) throw new IOException("IllegalArgument");
    }


    public static boolean checkDirectory(File file) {
        return file.isDirectory();
    }

    public static boolean checkFileWithoutException(File file) {
        if (!file.exists()) {
            System.out.format("%s %s\n", file.getPath(), "File Not exist!");
            return false;
        }
        return true;
    }
}
