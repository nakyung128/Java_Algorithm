import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17265_나의인생에는수학과함께 {
    static int n;
    static boolean[][] visited;
    static char[][] map;
    static int[] dx = { 1, 0 };
    static int[] dy = { 0, 1 };
    static int max_value, min_value;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        map = new char[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = st.nextToken().charAt(0);
            }
        }

        max_value = Integer.MIN_VALUE;
        min_value = Integer.MAX_VALUE;
        visited[0][0] = true;
        dfs(0, 0, map[0][0] - '0', ' ');
        System.out.println(max_value + " " + min_value);
    }

    static void dfs(int x, int y, int num, char cal) {
        if (x == n - 1 && y == n - 1) {
            max_value = Math.max(max_value, num);
            min_value = Math.min(min_value, num);
            return;
        }

        for (int i = 0; i < 2; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    if (map[nx][ny] == '+' || map[nx][ny] == '-' || map[nx][ny] == '*') {
                        dfs(nx, ny, num, map[nx][ny]);
                    } else {
                        int next = map[nx][ny] - '0';
                        if (cal == '+') {
                            dfs(nx, ny, num + next, ' ');
                        } else if (cal == '-') {
                            dfs(nx, ny, num - next, ' ');
                        } else {
                            dfs(nx, ny, num * next, ' ');
                        }
                    }
                    visited[nx][ny] = false;
                }
            }
        }
    }
}
