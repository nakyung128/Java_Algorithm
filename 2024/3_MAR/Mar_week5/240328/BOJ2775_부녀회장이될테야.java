import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2775_부녀회장이될테야 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int t = Integer.parseInt(st.nextToken());

    int[][] dp = new int[15][14];

    for (int i = 0; i < 14; i++) {
      dp[0][i] = i + 1;
    }

    for (int i = 1; i < 15; i++) {
      for (int j = 0; j < 14; j++) {
        if (j == 0) {
          dp[i][j] = 1;
        } else {
          dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
        }
      }
    }

    for (int tc = 0; tc < t; tc++) {
      st = new StringTokenizer(br.readLine());
      int k = Integer.parseInt(st.nextToken()); // 층
      st = new StringTokenizer(br.readLine());
      int b = Integer.parseInt(st.nextToken()); // 호수

      System.out.println(dp[k][b - 1]);
    }
  }
}
