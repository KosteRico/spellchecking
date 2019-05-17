import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Input your word: ");

        String word = scanner.next();

        scanner.close();

        Spellchecker spellchecker = new Spellchecker();

        List<String> list = spellchecker.check(word);

        if (list == null) {
            System.out.format("Word \"%s\" is correct!", word);
        } else {
            System.out.println("Word \"" + word + "\" is incorrect.\nMaybe you mean: ");
            list.forEach(s -> {
                String comma = ", ";
                if (s.equals(list.get(list.size() - 1))) {
                    comma = "\n";
                }
                System.out.print(s + comma);
            });
        }
    }
}
