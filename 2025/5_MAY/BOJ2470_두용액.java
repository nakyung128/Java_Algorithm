import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2470_두용액 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = n - 1;
        int answer = Integer.MAX_VALUE;
        int[] ans = new int[2];

        Arrays.sort(arr); // 오름차순 정렬

        while (start < end) {
            int hab = arr[start] + arr[end];
            if (answer > Math.abs(hab)) {
                answer = Math.abs(hab);
                ans[0] = arr[start];
                ans[1] = arr[end];
            }
            if (hab > 0) {
                end--;
            } else if (hab < 0) {
                start++;
            } else {
                System.out.println(arr[start] + " " + arr[end]);
                break;
            }
        }
        System.out.println(ans[0] + " " + ans[1]);
    }
}
