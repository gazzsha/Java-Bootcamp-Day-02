import java.io.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class FileSystem {
    private Set<String> set;
    private List<String> list1;
    private List<String> list2;
    private List<Integer> positionOfFirst;
    private List<Integer> positionOfSecond;

    public FileSystem() {
        set = new TreeSet<>();
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        positionOfFirst = new ArrayList<>();
        positionOfSecond = new ArrayList<>();
    }


    public void ReadFromFiles(BufferedReader bufferedReader1, BufferedReader bufferedReader2, PrintWriter printWriter) throws IOException {
        String line;
        String line2;
        while ((line = bufferedReader1.readLine()) != null) {
            String[] worlds = line.split("\\s");
            list1.addAll(Arrays.asList(worlds));
            set.addAll(Arrays.asList(worlds));
        }
        while ((line2 = bufferedReader2.readLine()) != null) {
            String[] worlds = line2.split("\\s");
            list2.addAll(Arrays.asList(worlds));
            set.addAll(Arrays.asList(worlds));
        }
        findPosition();
        printWriter.write(Arrays.toString(set.toArray()));
    }

    private void findPosition() {
        for (var word : set) {
            positionOfFirst.add(findElem(word, list1));
            positionOfSecond.add(findElem(word, list2));
        }
    }

    private int findElem(String str, List<? extends String> list) {
        int count = 0;
        for (String elem : list) {
            if (str.equals(elem)) count++;
        }
        return count;
    }

    public double similarity() {
        int numerator = 0;
        double denominator = 0;
        double square1 = 0;
        double square2 = 0;
        int size = positionOfFirst.size();
        for (int i = 0; i != size; i++) {
            numerator += positionOfFirst.get(i) * positionOfSecond.get(i);
            square1 += Math.pow(positionOfFirst.get(i), 2);
            square2 += Math.pow(positionOfSecond.get(i), 2);
        }
        denominator = Math.sqrt(square1 * square2);
        BigDecimal bigDecimal = new BigDecimal(denominator);
        bigDecimal = bigDecimal.setScale(1, RoundingMode.HALF_DOWN);
        return (double) numerator / bigDecimal.doubleValue();
    }
}
