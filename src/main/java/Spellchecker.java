import java.util.*;

public class Spellchecker {

    public Spellchecker() {
        dictionaryBuilder = new DictionaryBuilder();
        setFiles(dictionaryBuilder, "textfiles/dorian_gray.txt",
                "textfiles/hamlet.txt",
                "textfiles/prime_and_predjustice.txt",
                "textfiles/Martin_Eden.txt",
                "textfiles/Atlas_Shrugged.txt",
                "textfiles/A_Suitable_Boy.txt");
    }

    private static final int MAX_SUGGESTIONS = 5;
    private DictionaryBuilder dictionaryBuilder;

    public List<String> check(String word) {
        List<String> suggestions = new LinkedList<>();
        List<Integer> weights = new LinkedList<>();

        Set<String> set = dictionaryBuilder.getDictionary();

        for (String string : set) {
            int weight = editDist(word, string);
            if (weight == 0) {
                return null;
            }
            suggestions.add(string);
            weights.add(weight);
            int i = suggestions.size() - 1;
            while (i > 0 && weights.get(i) < weights.get(i - 1)) {
                Collections.swap(weights, i - 1, i);
                Collections.swap(suggestions, i - 1, i);
                i--;
            }
            if (suggestions.size() > MAX_SUGGESTIONS) {
                weights.remove(weights.size() - 1);
                suggestions.remove(suggestions.size() - 1);
            }
        }
        return suggestions;
    }

    private void setFiles(DictionaryBuilder dictionaryBuilder, String... paths) {
        for (String path : paths) {
            try {
                dictionaryBuilder.setFileForScan(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private int min(int x, int y, int z) {
        if (x <= y && x <= z) return x;
        if (y <= x && y <= z) return y;
        else return z;
    }

    private int editDist(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0)
                    dp[i][j] = j;
                else if (j == 0)
                    dp[i][j] = i;
                else if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = 1 + min(dp[i][j - 1],
                            dp[i - 1][j],
                            dp[i - 1][j - 1]);
            }
        }

        return dp[m][n];
    }
}
