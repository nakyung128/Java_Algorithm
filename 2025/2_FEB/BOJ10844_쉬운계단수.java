import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10844_쉬운계단수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int MOD = 1_000_000_000;
        int N = Integer.parseInt(br.readLine());

        long[][] dp = new long[N + 1][10];
        for (int i = 1; i < 10; i++) {
            dp[1][i] = 1;
        }

        if (N == 1) {
            System.out.println(9);
        } else {
            for (int i = 2; i < N + 1; i++) {
                for (int j = 0; j < 10; j++) {
                    if (j == 0) {
                        dp[i][j] = dp[i - 1][j + 1];
                    } else if (j == 9) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % MOD;
                    }
                }
            }
            long answer = 0;
            for (int i = 0; i < 10; i++) {
                answer = (answer + dp[N][i]) % MOD;
            }
            System.out.println(answer);
        }
    }
}
