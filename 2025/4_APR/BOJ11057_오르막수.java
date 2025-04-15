import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11057_오르막수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        final int MOD = 10007;

        int[][] dp = new int[n + 1][10];
        for (int i = 0; i < 10; i++) {
            dp[1][i] = 1;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = j; k < 10; k++) {
                    dp[i][j] += dp[i - 1][k] % MOD;
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < 10; i++) {
            answer += dp[n][i];
        }
        System.out.println(answer % MOD);
    }
}
