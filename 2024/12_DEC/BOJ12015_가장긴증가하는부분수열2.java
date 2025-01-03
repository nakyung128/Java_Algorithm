import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12015_가장긴증가하는부분수열2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        int[] LIS = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        LIS[0] = a[0]; // 맨 첫 번째 값 넣어 주기
        int answer = 1;

        for (int i = 1; i < n; i++) {
            // 새로운 숫자가 부분 수열 맨 끝 숫자보다 크면 추가해 주기
            if (LIS[answer - 1] < a[i]) {
                answer++;
                LIS[answer - 1] = a[i];
            } else {
                int idx = binary(answer, LIS, a[i]); // 어느 자리에 들어갈지 찾기
                LIS[idx] = a[i]; // 대치
            }
        }

        System.out.println(answer);

    }

    // 이분 탐색
    // 새로운 값이 들어갈 자리 찾기
    static int binary(int n, int[] LIS, int target) {
        int left = 0;
        int right = n - 1;

        while (left < right) {
            int mid = (left + right) / 2;
            if (LIS[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
