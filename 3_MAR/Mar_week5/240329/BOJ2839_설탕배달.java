import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2839_설탕배달 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int[] dp = new int[5001];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[3] = 1;
    dp[5] = 1;

    for (int i = 6; i < 5001; i++) {
      if (dp[i - 3] == Integer.MAX_VALUE && dp[i - 5] == Integer.MAX_VALUE) {
        continue;
      } else {
        dp[i] = Math.min(dp[i - 3], dp[i - 5]) + 1;
      }
    }

    int n = Integer.parseInt(br.readLine());
    System.out.println(dp[n] == Integer.MAX_VALUE ? -1 : dp[n]);
  }
}
