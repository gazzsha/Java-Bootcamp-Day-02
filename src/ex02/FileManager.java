import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class FileManager {
    File file;

    Path path;

    public static final int Kilo = 1024;

    public FileManager(File file) {
        this.file = file;
        this.path = file.toPath();
        System.out.println(file.getAbsolutePath());
    }

    void touchFile(String fileName) throws IOException {
        Files.createFile(path.resolve(fileName).normalize());
    }


    public void setFile(String fileName) {
        Path temp = path.resolve(fileName).normalize();
        if (!CheckerFile.checkFileWithoutException((temp.toFile()))) return;
        if (!CheckerFile.checkDirectory(temp.toFile())) {
            System.out.format("%s %s\n", temp.toAbsolutePath(), "is not Directory!");
            return;
        }
        file = temp.toFile();
        this.path = temp;
        System.out.println(path);

    }

    public void moveFiles(String fileSource, String fileDestination) throws IOException {
        Path source = path.resolve(fileSource).normalize();
        Path newDir = path.resolve(fileDestination).normalize();
        if (CheckerFile.checkFileWithoutException(source.toFile())) {
            if (!CheckerFile.checkDirectory(newDir.toFile()) && source.getParent().equals(newDir.getParent())) {
                Files.move(source, source.resolveSibling(fileDestination));
            } else {
                newDir = newDir.resolve(fileSource).normalize();
                if (CheckerFile.checkDirectory(newDir.getParent().toFile())) {
                    Files.move(source, newDir, REPLACE_EXISTING);
                }
            }
        }
    }

    public void removeFile(String fileName) throws IOException {
        if (!CheckerFile.checkDirectory(path.resolve(fileName).normalize().toFile())) {
            Files.deleteIfExists(path.resolve(fileName).normalize());
        }
    }

    public void mkdir(String directoryName) throws IOException {
        Files.createDirectory(path.resolve(directoryName).normalize());
    }


    public void printFiles() {
        if (file.listFiles() == null) {
            return;
        }
        for (File files : file.listFiles()) {
            if (files.isFile()) {
                System.out.format("%s %d %s\n", files.getName(), toKb(files.length()), "KB");
            } else {
                System.out.format("%s %d %s\n", files.getName(), toKb(getSizeFile(files)), "KB");
            }
        }
    }

    void pwd() {
        System.out.println(path.toAbsolutePath());
    }
    public long getSizeFile(File file) {
        long summarySize = 0;
        if (file.isDirectory()) {
            for (File files : file.listFiles()) {
                if (files.isFile()) {
                    summarySize += files.length();
                } else {
                    summarySize += getSizeFile(files);
                }
            }
        } else if (file.isFile()) { 
            summarySize += file.length();
        }
        return summarySize;
    }

    public long toKb(long bytes) {
        return BigDecimal.valueOf(bytes).divide(BigDecimal.valueOf(FileManager.Kilo), RoundingMode.FLOOR).setScale(0, RoundingMode.UNNECESSARY).longValue();
    }
}
