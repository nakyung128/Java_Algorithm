import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ23978_급상승 {
    static int n;
    static long k;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // n개의 날짜
        k = Long.parseLong(st.nextToken()); // k원 이상 현금화
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long start = 1;
        long end = 2_000_000_000L;
        long answer = Long.MAX_VALUE;

        while (start <= end) {
            long mid = (start + end) / 2;
            if (mid == 0) {
                start = mid + 1;
                continue;
            }
            if (isValid(mid)) { // mid일 때 현금화할 수 있는 돈이 k 이상이면 최솟값 더 줄여도 됨
                answer = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(answer);
    }

    static boolean isValid(long min) {
        long hab = min * (min + 1) / 2; // 마지막 구간 등차수열 합
        for (int i = 0; i < n - 1; i++) {
            long len = arr[i + 1] - arr[i];
            len = Math.min(len, min);
            hab += len * min - (len * (len - 1L)) / 2;
            if (hab >= k) {
                return true;
            }
        }
        return hab >= k;
    }
}
