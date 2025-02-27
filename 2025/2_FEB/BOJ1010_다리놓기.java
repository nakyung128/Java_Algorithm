import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1010_다리놓기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if (n == m) {
                sb.append(1 + "\n");
            } else if (n == 1) {
                sb.append(m + "\n");
            } else {
                int[][] dp = new int[m + 1][m + 1];
                for (int i = 0; i < m + 1; i++) {
                    dp[i][0] = 1;
                    dp[i][1] = i;
                    dp[i][i] = 1;
                }

                for (int i = 2; i < m + 1; i++) {
                    for (int j = 2; j < m + 1; j++) {
                        if (i >= j) {
                            dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
                        } else {
                            break;
                        }
                    }
                }
                sb.append(dp[m][n] + "\n");
            }
        }
        System.out.print(sb.toString());
    }
}
