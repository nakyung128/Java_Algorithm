import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ14728_벼락치기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int[][] dp = new int[n][t + 1];
        ArrayList<int[]> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            arr.add(new int[] { k, s });
        }

        // dp 채우기
        for (int i = arr.get(0)[0]; i < t + 1; i++) {
            dp[0][i] = arr.get(0)[1];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < t + 1; j++) {
                int k = arr.get(i)[0];
                int s = arr.get(i)[1];
                if (j < k) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - k] + s);
                }
            }
        }
        System.out.println(dp[n - 1][t]);
    }
}
