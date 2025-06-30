import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ14002_가장긴증가하는부분수열4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] dp = new int[n];
        int[] root = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = 1;
        int answer = 1;
        int ans_idx = 0;
        for (int i = 1; i < n; i++) {
            int maxCnt = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] > arr[j]) {
                    if (dp[j] + 1 > maxCnt) {
                        maxCnt = dp[j] + 1;
                        root[i] = j;
                    }
                }
            }
            dp[i] = maxCnt;
            if (answer < dp[i]) {
                answer = dp[i];
                ans_idx = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        sb.append(answer + "\n");

        while (stack.size() < answer) {
            stack.add(arr[ans_idx]);
            ans_idx = root[ans_idx];
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop() + " ");
        }
        System.out.println(sb.toString());
    }
}
