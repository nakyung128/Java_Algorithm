import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14921_용액합성하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = n - 1;
        int answer = Integer.MAX_VALUE;
        while (left < right) {
            if (Math.abs(answer) > Math.abs(arr[left] + arr[right])) {
                answer = arr[left] + arr[right];
            }
            if (arr[left] + arr[right] > 0) {
                right--;
            } else {
                left++;
            }
        }
        System.out.println(answer);
    }
}
