import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2342_DDR {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int move[][] = { { 0, 2, 2, 2, 2 }, { 2, 1, 3, 4, 3 }, { 2, 3, 1, 3, 4 }, { 2, 4, 3, 1, 3 },
                { 2, 3, 4, 3, 1 } };

        int dp[][][] = new int[100001][5][5];
        for (int i = 0; i < 100001; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    dp[i][j][k] = 100001 * 4;
                }
            }
        }
        dp[0][0][0] = 0;

        int size = 1;
        while (true) {
            int n = Integer.parseInt(st.nextToken());
            if (n == 0) {
                break;
            }
            // 오른발 움직일 때
            for (int l = 0; l < 5; l++) {
                if (n == l) {
                    continue;
                }
                for (int r = 0; r < 5; r++) {
                    dp[size][l][n] = Math.min(dp[size][l][n], dp[size - 1][l][r] + move[r][n]);
                }
            }
            // 왼발 움직일 때
            for (int r = 0; r < 5; r++) {
                if (n == r) {
                    continue;
                }
                for (int l = 0; l < 5; l++) {
                    dp[size][n][r] = Math.min(dp[size][n][r], dp[size - 1][l][r] + move[l][n]);
                }
            }
            size++;
        }
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                answer = Math.min(answer, dp[size - 1][i][j]);
            }
        }
        System.out.println(answer);
    }
}
