import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ15989_1,2,3 더하기4
{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine());
            int[][] dp = new int[n + 1][4];

            if (n == 1) {
                System.out.println(1);
            } else if (n == 2) {
                System.out.println(2);
            } else if (n == 3) {
                System.out.println(3);
            } else {
                dp[1][1] = 1;
                dp[1][2] = 0;
                dp[1][3] = 0;
                dp[2][1] = 1;
                dp[2][2] = 1;
                dp[2][3] = 0;
                dp[3][1] = 1;
                dp[3][2] = 1;
                dp[3][3] = 1;

                for (int i = 4; i <= n; i++) {
                    dp[i][1] = dp[i - 1][1];
                    dp[i][2] = dp[i - 2][1] + dp[i - 2][2];
                    dp[i][3] = dp[i - 3][1] + dp[i - 3][2] + dp[i - 3][3];
                }
                int answer = 0;
                for (int i = 1; i <= 3; i++) {
                    answer += dp[n][i];
                }
                System.out.println(answer);
            }
        }
    }
}
