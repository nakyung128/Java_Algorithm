import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1722_순열의순서 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int Q = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[21];
        long[] F = new long[21];
        int[] S = new int[21];

        F[0] = 1;
        for (int i = 1; i < N + 1; i++) {
            F[i] = i * F[i - 1];
        }

        if (Q == 1) {
            long k = Long.parseLong(st.nextToken());
            for (int i = 1; i < N + 1; i++) {
                int cnt = 1;
                for (int j = 1; j < N + 1; j++) {
                    if (visited[j]) {
                        continue;
                    }
                    if (k <= cnt * F[N - i]) {
                        S[i] = j;
                        visited[j] = true;
                        k -= (F[N - i] * (cnt - 1));
                        break;
                    }
                    cnt++;
                }
            }
            for (int i = 1; i < N + 1; i++) {
                System.out.print(S[i] + " ");
            }
        } else {
            long k = 1; // 첫 번째로 초기화
            for (int i = 1; i < N + 1; i++) {
                S[i] = Integer.parseInt(st.nextToken());
                long cnt = 0; // 내가 남은 것 중에 몇 번째 숫자인지
                for (int j = 1; j < S[i]; j++) {
                    if (!visited[j]) {
                        cnt++;
                    }
                }
                k += cnt * F[N - i]; // 각 자릿수에 따라 순서 더하기
                visited[S[i]] = true;
            }
            System.out.println(k);
        }
    }
}
