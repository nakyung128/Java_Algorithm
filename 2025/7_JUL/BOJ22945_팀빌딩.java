import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ22945_팀빌딩 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = n - 1;
        int answer = 0;
        while (left < right) {
            int dist = right - left - 1;
            int min = Math.min(arr[left], arr[right]);
            answer = Math.max(answer, min * dist);

            if (arr[left] < arr[right]) {
                left++;
            } else {
                right--;
            }
        }
        System.out.println(answer);
    }
}
