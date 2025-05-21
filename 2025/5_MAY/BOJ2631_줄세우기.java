import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2631_줄세우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n];
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = 1;
        int longest = 0;
        for (int i = 1; i < n; i++) {
            int max_value = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    max_value = Math.max(max_value, dp[j]);
                }
            }
            dp[i] = max_value + 1;
            longest = Math.max(dp[i], longest);

        }
        System.out.println(n - longest);
    }
}
