import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12837_가계부 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        int k = 0;

        while (true) {
            if (Math.pow(2, k) >= N) {
                break;
            }
            k++;
        }

        int start = (int) Math.pow(2, k);
        long[] tree = new long[start * 2];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int idx = p + start - 1;

            if (type == 1) {
                long x = Long.parseLong(st.nextToken());
                tree[idx] += x;
                while (idx > 0) {
                    idx /= 2;
                    tree[idx] += x;
                }
            } else {
                int q = Integer.parseInt(st.nextToken());
                int start_idx = p + start - 1;
                int end_idx = q + start - 1;
                long answer = 0;

                while (start_idx <= end_idx) {
                    if (start_idx % 2 == 1) {
                        answer += tree[start_idx];
                        start_idx++;
                    }
                    if (end_idx % 2 == 0) {
                        answer += tree[end_idx];
                        end_idx--;
                    }
                    start_idx /= 2;
                    end_idx /= 2;
                }
                sb.append(answer + "\n");
            }
        }
        System.out.print(sb.toString());
    }
}
