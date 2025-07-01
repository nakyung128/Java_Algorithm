import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15565_귀여운라이언 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        int cnt = arr[0] == 1 ? 1 : 0;
        int answer = Integer.MAX_VALUE;

        while (left <= right && right < n) {
            if (cnt < k) {
                right++;
                if (right < n && arr[right] == 1) {
                    cnt++;
                }
            } else {
                answer = Math.min(answer, right - left + 1);
                if (arr[left] == 1) {
                    cnt--;
                }
                left++;
            }
        }
        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }
}
