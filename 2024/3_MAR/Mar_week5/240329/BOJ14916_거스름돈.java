import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ14916_거스름돈 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int[] dp = new int[1000001];
    Arrays.fill(dp, Integer.MAX_VALUE);

    dp[2] = 1;
    dp[4] = 2;
    dp[5] = 1;

    for (int i = 6; i < 1000001; i++) {
      if (dp[i - 2] == Integer.MAX_VALUE && dp[i - 5] == Integer.MAX_VALUE) {
        continue;
      }
      dp[i] = Math.min(dp[i - 2], dp[i - 5]) + 1;
    }

    int n = Integer.parseInt(br.readLine());

    System.out.println(dp[n] == Integer.MAX_VALUE ? -1 : dp[n]);
  }
}
