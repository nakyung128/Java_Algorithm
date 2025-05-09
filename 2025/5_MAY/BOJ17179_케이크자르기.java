import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17179_케이크자르기 {
    static int n, m, l, q;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        arr = new int[m];

        for (int i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < n; i++) {
            q = Integer.parseInt(br.readLine());
            int left = 0;
            int right = l;
            int ans = right;

            while (left <= right) {
                int mid = (left + right) / 2;
                if (isValid(mid)) {
                    // q조각 넘게 만들 수 있으면 더 길게 잘라도 됨
                    ans = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            System.out.println(ans);
        }
    }

    static boolean isValid(int min) {
        int cnt = 0;
        int cut = 0;

        for (int i = 0; i < m; i++) {
            if (arr[i] - cut >= min && cnt < q) {
                cnt++;
                cut = arr[i];
            } 
        }

        if (l - cut < min) {
            return false;
        }

        return cnt == q;
    }
}
