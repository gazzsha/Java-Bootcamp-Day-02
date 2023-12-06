import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Path;

public class FileManager {
    File file;
    public FileManager(File file) {
        this.file = file;
        System.out.println(file.getAbsolutePath());
    }


    public void setFile(File fileOwner){
        if (!CheckerFile.checkFileWithoutException((fileOwner))) return;
        if (!CheckerFile.checkDirectory(fileOwner)) {
            System.out.format("%s %s\n",fileOwner.getAbsolutePath(),"is not Directory!");
            return;
        }
        file = fileOwner;
        System.out.println(file.getAbsolutePath());
    }

    public static final int Kilo = 1024;

    public void printFiles() {
        if (file.listFiles() == null) {return;}
        for (File files : file.listFiles()) {
            if (files.isFile()) {
                System.out.format("%s %d %s\n",files.getName(),toKb(files.length()),"KB");
            }
            else {
                System.out.format("%s %d %s\n",files.getName(),toKb(getSizeFile(files)),"KB");
            }
        }
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
        }
        return summarySize;
    }

    public long toKb(long bytes) {
        return BigDecimal.valueOf(bytes).divide(BigDecimal.valueOf(FileManager.Kilo), RoundingMode.FLOOR).setScale(0,RoundingMode.UNNECESSARY).longValue();
    }
}
