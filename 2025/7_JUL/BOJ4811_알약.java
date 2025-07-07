import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ4811_알약 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) {
                break;
            }

            long[][] dp = new long[n + 1][n + 1];
            dp[1][0] = 1;
            for (int w = 0; w < n + 1; w++) {
                for (int h = 0; h < n + 1; h++) {
                    if (w < n) {
                        dp[w + 1][h] += dp[w][h];
                    }
                    if (h < w) {
                        dp[w][h + 1] += dp[w][h];
                    }
                }
            }
            sb.append(dp[n][n] + "\n");
        }
        System.out.print(sb.toString());
    }
}
