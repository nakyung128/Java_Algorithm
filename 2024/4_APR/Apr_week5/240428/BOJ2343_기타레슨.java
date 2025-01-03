import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2343_기타레슨 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 강의의 수
        int m = Integer.parseInt(st.nextToken()); // 블루레이 개수

        int[] time = new int[n];
        int start = 0;
        int end = 0;
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            int minute = Integer.parseInt(st.nextToken());
            time[i] = minute;
            start = Math.max(start, minute);
            end += minute;
        }

        while (start <= end) {
            int mid = (start + end) / 2;

            int sum = 0;
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if (sum + time[i] > mid) {
                    cnt++;
                    sum = 0;
                }
                sum += time[i];
            }

            if (sum != 0) {
                cnt++;
            }

            if (cnt > m) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(start);
    }
}
