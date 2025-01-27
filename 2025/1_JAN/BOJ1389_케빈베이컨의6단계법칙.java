import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1389_케빈베이컨의6단계법칙 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] friend = new int[n + 1][n + 1];

        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (i != j) {
                    friend[i][j] = 10000001;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            friend[a][b] = 1;
            friend[b][a] = 1;
        }

        for (int k = 0; k < n + 1; k++) {
            for (int i = 0; i < n + 1; i++) {
                for (int j = 0; j < n + 1; j++) {
                    friend[i][j] = Math.min(friend[i][j], friend[i][k] + friend[k][j]);
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        int ppl = n + 1;
        for (int i = 1; i < n + 1; i++) {
            int hab = 0;
            for (int j = 1; j < n + 1; j++) {
                hab += friend[i][j];
            }
            if (hab < answer) {
                answer = hab;
                ppl = i;
            }
        }

        System.out.println(ppl);
    }
}
