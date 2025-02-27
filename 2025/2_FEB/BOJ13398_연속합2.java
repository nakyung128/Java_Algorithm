import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13398_연속합2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        int[] L = new int[n];
        int[] R = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 왼쪽에서부터 연속합 구하기
        L[0] = arr[0];
        int answer = arr[0];
        for (int i = 1; i < n; i++) {
            L[i] = Math.max(arr[i], L[i - 1] + arr[i]);
            answer = Math.max(answer, L[i]);
        }

        // 오른쪽에서부터 연속합 구하기
        R[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            R[i] = Math.max(arr[i], R[i + 1] + arr[i]);
        }

        // 숫자 하나 제거했을 때 최댓값 나오는지
        for (int i = 1; i < n - 1; i++) {
            answer = Math.max(answer, L[i - 1] + R[i + 1]);
        }
        System.out.println(answer);
    }
}
