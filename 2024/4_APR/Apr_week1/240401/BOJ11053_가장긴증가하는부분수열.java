import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11053_가장긴증가하는부분수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] num = new int[n];
        int[] dp = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        // dp 처음은 1로 초기화
        dp[0] = 1;

        // 숫자 길이가 1일 때도 있으므로 답은 1로 초기화해 둠
        int answer = 1;

        // 2중 for문
        // 나의 앞을 다 탐색해 줌
        for (int i = 1; i < n; i++) {
            int max = 0;
            for (int j = i - 1; j >= 0; j--) {
                // 나보다 숫자가 작고 max보다 dp값이 크면 갱신
                if (num[j] < num[i]) {
                    max = Math.max(max, dp[j]);
                }
            }

            // 만약 max값이 갱신되지 않았으면 1
            if (max == 0) {
                dp[i] = 1;
            } else { // 갱신됐으면 그 dp값의 + 1
                dp[i] = max + 1;
            }

            // 정답은 가장 큰 수여야 하므로 Math.max로 갱신해 주기
            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);
    }
}
