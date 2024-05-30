import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1300_K번째수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        int start = 1;
        int end = k;

        int answer = 0;

        while (start <= end) {
            int mid = (start + end) / 2;
            int cnt = 0; // 중앙값보다 같거나 작은 수

            for (int i = 1; i < n + 1; i++) {
                cnt += Math.min(mid / i, n);
            }

            if (cnt < k) {
                start = mid + 1;
            } else {
                end = mid - 1;
                answer = mid;
            }
        }

        System.out.println(answer);
    }
}