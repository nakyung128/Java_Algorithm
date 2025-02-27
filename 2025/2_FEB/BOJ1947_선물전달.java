import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1947_선물전달 {
    public static void main(String[] args) throws IOException {
        final int MOD = 1_000_000_000;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[] dp = new long[N + 1];
        dp[1] = 0;

        if (N > 2) {
            dp[2] = 1;
            for (int i = 3; i < N + 1; i++) {
                dp[i] = (i - 1) * (dp[i - 1] + dp[i - 2]) % MOD;
            }
            System.out.println(dp[N]);
        } else if (N == 2) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}
