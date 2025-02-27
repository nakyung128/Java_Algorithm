import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1256_사전 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // 만들 수 있는 단어 개수 = n+m개 중 m개를 고르는 경우의 수
        int[][] dp = new int[n + m + 1][n + m + 1];
        for (int i = 0; i < n + m + 1; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
            dp[i][1] = i;
        }

        for (int i = 2; i < n + m + 1; i++) {
            for (int j = 2; j < n + m + 1; j++) {
                if (j <= i) {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1]; // 점화식
                    if (dp[i][j] > 1_000_000_000) { // 경우의 수가 k의 최대보다 큰 경우
                        dp[i][j] = 1_000_000_001; // 그냥 최댓값인 1,000,000,001로 설정해 준다.
                    }
                }
            }
        }

        if (dp[n + m][m] < k) { // 만약에 만들 수 있는 경우의 수가 구하는 순서보다 작다면 -1
            System.out.println(-1);
        } else {
            StringBuilder sb = new StringBuilder();
            int cnt = m; // cnt = 남아 있는 "z"의 개수
            for (int i = 1; i < n + m + 1; i++) {
                // 현재 위치에서 a를 선택했다고 가정
                // 남아 있는 문자들 중 cnt개 선택하는 경우의 수가 구하는 순서와 같거나 크다면
                // 그대로 a 선택
                if (dp[n + m - i][cnt] >= k) {
                    sb.append("a");
                } else { // 만약에 구하는 순서보다 경우의 수가 작으면 z 선택
                    sb.append("z");
                    k -= dp[n + m - i][cnt]; // 구하는 순서 = 구하는 순서 - 경우의 수
                    cnt--; // z 선택했으니 개수 1 줄여주기
                }
            }
            System.out.println(sb.toString());
        }
    }
}
