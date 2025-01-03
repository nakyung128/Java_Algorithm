import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10942_팰린드롬 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        int[][] dp = new int[n][n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int i = 0; i < n - 1; i++) {
            if (arr[i] == arr[i + 1]) {
                dp[i][i + 1] = 1;
            }
        }

        for (int i = 2; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                if (arr[j] == arr[j + i]) {
                    if (dp[j + 1][i + j - 1] == 1) {
                        dp[j][i + j] = 1;
                    } else {
                        dp[j][i + j] = 0;
                    }
                }
            }
        }

        int m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            sb.append(dp[s - 1][e - 1] + "\n");
        }
        System.out.println(sb.toString());
    }
}
