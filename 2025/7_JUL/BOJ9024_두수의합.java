import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ9024_두수의합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(st.nextToken());
        for (int tc = 0; tc < t; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr); // 오름차순 정렬
            int answer = 0;
            int near_value = Integer.MAX_VALUE;
            int left = 0;
            int right = n - 1;

            while (left < right) {
                int hab = arr[left] + arr[right];
                if (arr[left] + arr[right] > k) {
                    right--;
                } else {
                    left++;
                }
                if (Math.abs(hab - k) < near_value) {
                    answer = 1;
                    near_value = Math.abs(hab - k);
                } else if (Math.abs(hab - k) == near_value) {
                    answer++;
                }
            }
            sb.append(answer + "\n");
        }
        System.out.print(sb.toString());
    }
}
