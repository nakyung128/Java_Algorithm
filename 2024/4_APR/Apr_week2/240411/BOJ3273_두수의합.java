import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ3273_두수의합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int x = Integer.parseInt(br.readLine());

        Arrays.sort(nums);

        int left = 0;
        int right = n - 1;
        int answer = 0;

        while (left < right) {
            if (nums[left] + nums[right] == x) {
                answer++;
                right--;
                left++;
            } else if (nums[left] + nums[right] > x) {
                right--;
            } else {
                left++;
            }
        }
        System.out.println(answer);
    }
}