import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1932 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 삼각형의 크기
        int[] dp = new int[n];

        int num = 0;
        for (int i = 1; i <= n; i++) {
            num += i;
        }

        int[] sam = new int[num];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < i+1; j++) {
                sam[i+j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0] = sam[0];

        int idx = 0;

        for (int i = 1; i < n; i++) {
            if (sam[idx+1] > sam[idx+2]) {
                dp[i] = dp[i-1] + sam[idx+i];
                idx = idx + 1;
            } else {
                dp[i] = dp[i-1] + sam[idx+i+1];
                idx = idx + 2;
            }
        }

        System.out.println(dp[n-1]);
    }
}
