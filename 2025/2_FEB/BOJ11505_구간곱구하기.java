import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11505_구간곱구하기 {
    static final int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken()); // 수의 변경이 일어나는 횟수
        int k = Integer.parseInt(st.nextToken()); // 구간 곱 구하는 횟수

        int pow = 0;
        while (true) {
            if (Math.pow(2, pow) >= n) {
                break;
            }
            pow++;
        }

        int start = (int) Math.pow(2, pow);
        long[] tree = new long[start * 2];
        for (int i = 0; i < n; i++) {
            tree[start + i] = Long.parseLong(br.readLine());
        }
        for (int i = start - 1; i > 0; i--) {
            tree[i] = tree[i * 2] * tree[i * 2 + 1] % MOD;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 1) {
                // b번째를 c로 바꾸기
                int idx = b + start - 1;
                tree[idx] = c;
                while (idx > 0) {
                    idx /= 2;
                    tree[idx] = tree[idx * 2] * tree[idx * 2 + 1] % MOD;
                }
            } else {
                // b부터 c까지 구간곱 구하기
                int start_idx = b + start - 1;
                int end_idx = c + start - 1;
                long answer = 1;

                while (start_idx <= end_idx) {
                    if (start_idx % 2 == 1) {
                        answer *= tree[start_idx] % MOD;
                        answer %= MOD;
                    }
                    if (end_idx % 2 == 0) {
                        answer *= tree[end_idx] % MOD;
                        answer %= MOD;
                    }
                    start_idx = (start_idx + 1) / 2;
                    end_idx = (end_idx - 1) / 2;
                }
                sb.append(answer + "\n");
            }
        }
        System.out.print(sb.toString());
    }
}
