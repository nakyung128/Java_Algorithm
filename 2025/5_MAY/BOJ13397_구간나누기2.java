import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13397_구간나누기2 {
    static int n, m;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];

        int max = 0;
        int min = 100001;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }

        int s = 0;
        int e = max - min;
        int ans = e;

        while (s <= e) {
            int mid = (s + e) / 2;
            if (canDivide(mid)) {
                ans = mid;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        System.out.println(ans);
    }

    static boolean canDivide(int max_diff) {
        int cnt = 1;
        int max = arr[0];
        int min = arr[0];

        for (int i = 1; i < n; i++) {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);

            if (max - min > max_diff) {
                cnt++;
                min = arr[i];
                max = arr[i];
            }
        }
        return cnt <= m;
    }
}
