import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileReader {
    private Map<String, String> tokens;

    public FileReader() {
        tokens = new HashMap<>();
    }

    public void readTokens(InputStream inputStream) throws IOException {
        try (Scanner scanner = new Scanner(inputStream)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] tokensInString = line.split(", ");
                tokens.put(tokensInString[0], tokensInString[1]);
            }
        }
    }


    public void findExecution(InputStream inputStream, OutputStream outputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i != 10; i++) {
            stringBuilder.append(String.format("%02X", inputStream.read())).append(" ");
        }
        for (var pair : tokens.entrySet()) {
            Pattern pattern = Pattern.compile(pair.getValue());
            Matcher matcher = pattern.matcher(stringBuilder);
            if (matcher.find()) {
                outputStream.write(pair.getKey().getBytes());
                outputStream.write('\n');
            }
        }
    }
}
