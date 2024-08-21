import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ23352_방탈출 {
    static int n, m;
    static int[][] map, dist;
    static Queue<int[]> q;
    static int[] dx = new int[] { -1, 1, 0, 0 };
    static int[] dy = new int[] { 0, 0, -1, 1 };
    static int answer, max_dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        dist = new int[n][m];
        answer = 0;
        max_dist = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 0) {
                    reset();
                    q.add(new int[] { i, j });
                    bfs(); // 거리 계산 완료
                    dist[i][j] = 1;
                    cal(i, j);
                }
            }
        }

        System.out.println(answer);
    }

    static void cal(int x, int y) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dist[i][j] != 0 && dist[i][j] == max_dist) {
                    if (map[x][y] + map[i][j] > answer) {
                        answer = map[x][y] + map[i][j];
                    }
                } else if (dist[i][j] != 0 && dist[i][j] > max_dist) {
                    max_dist = dist[i][j];
                    answer = map[x][y] + map[i][j];
                }
            }
        }
    }

    static void bfs() {
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if (map[nx][ny] != 0 && dist[nx][ny] == 0) {
                        dist[nx][ny] = dist[x][y] + 1;
                        q.add(new int[] { nx, ny });
                    }
                }
            }
        }
    }

    static void reset() {
        q = new LinkedList<>();
        for (int[] d : dist) {
            Arrays.fill(d, 0);
        }
    }
}
