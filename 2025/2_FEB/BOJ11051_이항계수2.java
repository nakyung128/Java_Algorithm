import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11051_이항계수2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        final int MOD = 10007;
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N + 1][N + 1];
        for (int i = 0; i < N + 1; i++) {
            dp[i][0] = 1;
            dp[i][1] = i;
            dp[i][i] = 1;
        }

        for (int i = 2; i < N + 1; i++) {
            for (int j = 2; j < N + 1; j++) {
                if (j <= i) {
                    dp[i][j] = (dp[i - 1][j] + dp[i - 1][j - 1]) % MOD;
                } else {
                    break;
                }
            }
        }

        System.out.println(dp[N][K]);
    }
}
