import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17951_흩날리는시험지속에서내평점이느껴진거야 {
    static int n, k;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 시험지의 개수
        k = Integer.parseInt(st.nextToken()); // 시험지를 나눌 그룹의 수
        arr = new int[n];
        st = new StringTokenizer(br.readLine());

        int left = 0;
        int right = 0;
        int answer = 0;

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            right += arr[i];
        }

        while (left <= right) {
            int mid = (left + right) / 2;
            if (isValid(mid)) {
                left = mid + 1;
                answer = mid;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(answer);
    }

    static boolean isValid(int min) {
        int cnt = 0;
        int hab = 0;

        for (int i = 0; i < n; i++) {
            hab += arr[i];
            if (hab >= min) {
                cnt++;
                hab = 0;
            }
        }
        return cnt >= k;
    }
}
