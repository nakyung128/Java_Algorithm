import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9252_LCS2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine();
        String b = br.readLine();
        StringBuilder sb = new StringBuilder();

        int[][] dp = new int[b.length() + 1][a.length() + 1];
        for (int i = 1; i <= b.length(); i++) {
            for (int j = 1; j <= a.length(); j++) {
                if (b.charAt(i - 1) == a.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int row = b.length();
        int col = a.length();

        while (row > 0 && col > 0) {
            if (dp[row][col] == dp[row - 1][col]) {
                row--;
            } else if (dp[row][col] == dp[row][col - 1]) {
                col--;
            } else {
                sb.append(a.charAt(col - 1));
                row--;
                col--;
            }
        }

        if (dp[b.length()][a.length()] == 0) {
            System.out.println(0);
        } else {
            System.out.println(dp[b.length()][a.length()]);
            System.out.println(sb.reverse().toString());
        }
    }
}
