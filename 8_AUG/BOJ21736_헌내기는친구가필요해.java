import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ21736_헌내기는친구가필요해 {
    static int n, m;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        visited = new boolean[n][m];

        int x = 0;
        int y = 0;

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'I') {
                    x = i;
                    y = j;
                }
            }
        }

        dfs(x, y);
        if (answer == 0) {
            System.out.println("TT");
        } else {
            System.out.println(answer);
        }
    }

    static void dfs(int x, int y) {
        visited[x][y] = true;

        if (map[x][y] == 'P')
            answer++;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                if (!visited[nx][ny] && map[nx][ny] != 'X') {
                    dfs(nx, ny);
                }
            }
        }
    }
}
