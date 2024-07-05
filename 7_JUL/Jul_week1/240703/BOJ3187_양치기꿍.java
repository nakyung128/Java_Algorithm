import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3187_양치기꿍 {
    static int r, c;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static int sheep, wolf;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        int wCnt = 0;
        int sCnt = 0;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (!visited[i][j] && map[i][j] != '#') {
                    sheep = 0;
                    wolf = 0;

                    if (map[i][j] == 'v')
                        wolf++;
                    if (map[i][j] == 'k')
                        sheep++;

                    dfs(i, j);

                    if (sheep > wolf) {
                        sCnt += sheep;
                    } else {
                        wCnt += wolf;
                    }
                }
            }
        }
        System.out.println(sCnt + " " + wCnt);
    }

    static void dfs(int x, int y) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
                if (!visited[nx][ny] && map[nx][ny] != '#') {
                    if (map[nx][ny] == 'v') {
                        wolf++;
                    } else if (map[nx][ny] == 'k') {
                        sheep++;
                    }
                    dfs(nx, ny);
                }
            }
        }
    }
}