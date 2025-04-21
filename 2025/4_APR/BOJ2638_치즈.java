import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2638_치즈 {
    static int n, m, time;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static ArrayList<int[]> cheese;
    static int cheeseCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        cheese = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
                    map[i][j] = -1;
                }
                if (map[i][j] == 1) {
                    cheeseCnt++;
                    cheese.add(new int[] { i, j });
                }
            }
        }

        while (cheeseCnt != 0) {
            time++;
            visited = new boolean[n][m];
            dfs(0, 0);
            melt();
        }
        System.out.println(time);
    }

    static void dfs(int x, int y) {
        visited[x][y] = true;
        map[x][y] = -1;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                if (!visited[nx][ny] && map[nx][ny] != 1) {
                    dfs(nx, ny);
                }
            }
        }
    }

    static void melt() {
        for (int i = 0; i < cheese.size(); i++) {
            int cnt = 0;
            int x = cheese.get(i)[0];
            int y = cheese.get(i)[1];
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if (map[nx][ny] == -1) {
                    cnt++;
                }
            }
            if (cnt >= 2) {
                map[x][y] = 0;
                cheeseCnt--;
                cheese.remove(i);
                i--;
            }
        }
    }

    static boolean isEmpty() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
