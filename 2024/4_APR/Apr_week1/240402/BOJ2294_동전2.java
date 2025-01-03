import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2294_동전2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] dp = new int[k + 1];
        for (int i = 0; i < k + 1; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        int[] coins = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int coin = Integer.parseInt(st.nextToken());
            coins[i] = coin;
            if (coin <= k) {
                dp[coin] = 1;
            }
        }

        for (int i = 1; i <= k; i++) {
            if (dp[i] == 1) {
                continue;
            } else {
                int min_value = Integer.MAX_VALUE;
                for (int coin : coins) {
                    if (i >= coin) {
                        min_value = Math.min(min_value, dp[i - coin]);
                    }
                }
                if (min_value != Integer.MAX_VALUE) {
                    dp[i] = min_value + 1;
                }
            }
        }

        System.out.println(dp[k] != Integer.MAX_VALUE ? dp[k] : -1);
    }
}
