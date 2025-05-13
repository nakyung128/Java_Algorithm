import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14716_현수막 {
    static int m, n;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = { -1, 1, 0, 0, -1, 1, -1, 1 };
    static int[] dy = { 0, 0, -1, 1, -1, 1, 1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[m][n];
        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    visited[i][j] = true;
                    dfs(i, j);
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }

    static void dfs(int x, int y) {
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                if (!visited[nx][ny] && map[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    dfs(nx, ny);
                }
            }
        }
    }
}
