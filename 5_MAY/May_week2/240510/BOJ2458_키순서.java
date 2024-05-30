import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2458_키순서 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int INF = 1000;

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] students = new int[n + 1][n + 1];
        for (int[] student : students) {
            Arrays.fill(student, INF);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            students[a][b] = 1; // a가 b보다 작다. b가 a보다 크다.
        }

        for (int k = 1; k < n + 1; k++) {
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    students[i][j] = Math.min(students[i][j], students[i][k] + students[k][j]);
                }
            }
        }

        int answer = 0;
        for (int i = 1; i < n + 1; i++) {
            int cnt = 0;
            for (int j = 1; j < n + 1; j++) {
                if (students[i][j] != INF || students[j][i] != INF) {
                    cnt++;
                }
            }

            if (cnt == n - 1) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}
