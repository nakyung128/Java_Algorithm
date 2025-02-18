import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10868_최솟값 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = 0;

        while (true) {
            if (Math.pow(2, k) >= n) {
                break;
            }
            k++;
        }

        int start = (int) Math.pow(2, k);
        int[] tree = new int[start * 2];

        for (int i = 0; i < n; i++) {
            tree[start + i] = Integer.parseInt(br.readLine());
        }

        for (int i = start - 1; i > 0; i--) {
            tree[i] = Math.min(tree[i * 2], tree[i * 2 + 1]);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // a부터 b까지 중에 최솟값
            int start_idx = a + start - 1;
            int end_idx = b + start - 1;
            int answer = Integer.MAX_VALUE;

            while (start_idx <= end_idx) {
                if (start_idx % 2 == 1) {
                    answer = Math.min(answer, tree[start_idx]);
                }
                if (end_idx % 2 == 0) {
                    answer = Math.min(answer, tree[end_idx]);
                }
                start_idx = (start_idx + 1) / 2;
                end_idx = (end_idx - 1) / 2;
            }
            sb.append(answer + "\n");
        }
        System.out.print(sb.toString());
    }
}
