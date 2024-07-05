import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class BOJ2230_수고르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        int left = 0;
        int right = 0;

        // 오름차순 정렬
        Arrays.sort(nums);

        int answer = Integer.MAX_VALUE;
        while (left <= right) {
            int sub = nums[right] - nums[left];
            if (sub < m) {
                if (right != n - 1) {
                    right++;
                } else {
                    break;
                }
            }
            if (sub > m) {
                answer = Math.min(answer, sub);
                left++;
            } else if (sub == m) {
                answer = m;
                break;
            }
        }
        System.out.println(answer);
    }
}