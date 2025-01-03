import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1806_부분합 {
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        int answer = Integer.MAX_VALUE;
        int hab = arr[0];

        while (left <= right) {
            if (hab < s) {
                if (right != n - 1) {
                    right++;
                    hab += arr[right];
                } else {
                    break;
                }
            } else if (hab >= s) {
                answer = Math.min(answer, right - left + 1);
                if (left != n - 1) {
                    hab -= arr[left];
                    left++;
                } else {
                    break;
                }
            }
        }

        if (answer == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(answer);
        }
    }
}
