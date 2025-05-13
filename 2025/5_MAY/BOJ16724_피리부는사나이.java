import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16724_피리부는사나이 {
    static int n, m, cnt;
    static char[][] map;
    static boolean[][] visited, finished;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        visited = new boolean[n][m];
        finished = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    dfs(i, j);
                }
            }
        }
        System.out.println(cnt);
    }

    static void dfs(int x, int y) {
        int dir = getDir(map[x][y]);
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if (!visited[nx][ny]) {
            visited[nx][ny] = true;
            dfs(nx, ny);
        } else if (!finished[nx][ny]) {
            // 방문했는데 아직 안 끝났음 싸이클
            cnt++;
        }
        finished[x][y] = true;
    }

    static int getDir(char c) {
        switch (c) {
            case 'U':
                return 0;
            case 'D':
                return 1;
            case 'L':
                return 2;
            case 'R':
                return 3;
        }
        return -1;
    }
}
