import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2133_타일채우기 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());

    int[] dp = new int[31];
    dp[0] = 1;
    dp[1] = 0;
    dp[2] = 3;

    if (n % 2 == 1) {
      System.out.println(0);
    } else {
      for (int i = 4; i <= 30; i++) {
        dp[i] += dp[i - 2] * 3;

        for (int j = 4; j <= i; j += 2) {
          dp[i] += dp[i - j] * 2;
        }

      }
      System.out.println(dp[n]);
    }
  }
}
