import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2467_용액 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] liquid = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            liquid[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = n - 1;

        int value = Integer.MAX_VALUE;

        int[] nums = new int[2];

        while (left < right) {
            int hab = liquid[left] + liquid[right];
            if (hab == 0) {
                nums[0] = liquid[left];
                nums[1] = liquid[right];
                break;
            } else {
                if (Math.abs(hab) < value) {
                    value = Math.abs(hab);
                    nums[0] = liquid[left];
                    nums[1] = liquid[right];
                }
                if (hab > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        System.out.println(nums[0] + " " + nums[1]);
    }
}