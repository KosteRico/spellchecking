import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DictionaryBuilder {

    private Set<String> dictionary;

    public DictionaryBuilder() {
        dictionary = new HashSet<String>();
    }

    private String trim(StringBuilder string) {

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            if (Character.isLetter(string.charAt(i))) {
                builder.append(string.charAt(i));
            }
        }

        if (builder.length() == 1 && !(builder.toString().equalsIgnoreCase("a")
                || builder.toString().equalsIgnoreCase("I"))) {
            return "";
        }

        return builder.toString().toLowerCase();
    }

    public DictionaryBuilder setFileForScan(String fileName) throws Exception {

        URL url = getClass().getResource(fileName);

        File file = new File(url.getFile());

        Scanner scanner = new Scanner(new FileReader(file));

        while (scanner.hasNext()) {
            StringBuilder stringBuilder = new StringBuilder(scanner.next());
            String string = trim(stringBuilder);
            dictionary.add(string);
        }

        scanner.close();

        return this;
    }

    public Set<String> getDictionary() {
        return dictionary;
    }
}
