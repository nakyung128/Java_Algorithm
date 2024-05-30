import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Tomato {
    int z;
    int x;
    int y;

    Tomato(int z, int x, int y) {
        this.z = z;
        this.x = x;
        this.y = y;
    }
}

public class BOJ7569_토마토 {
    static int[][][] tomato;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static int[] dz = { -1, 1 };
    static int m, n, h, answer;
    static Queue<Tomato> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        tomato = new int[h][n][m];

        q = new LinkedList<>();
        int zero_cnt = 0;

        for (int k = 0; k < h; k++) {
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    tomato[k][i][j] = Integer.parseInt(st.nextToken());
                    if (tomato[k][i][j] == 1) {
                        q.offer(new Tomato(k, i, j));
                    } else if (tomato[k][i][j] == 0) {
                        zero_cnt++;
                    }
                }
            }
        }

        if (zero_cnt == 0) {
            System.out.println(0);
        } else {
            System.out.println(bfs());
        }
    }

    static int bfs() {
        int max_day = 0;

        while (!q.isEmpty()) {
            Tomato now = q.poll();
            int z = now.z;
            int x = now.x;
            int y = now.y;

            max_day = Math.max(max_day, tomato[z][x][y]);

            // 높이가 1 이상일 때만 위, 아래 보기
            if (h != 1) {
                for (int i = 0; i < 2; i++) {
                    int nz = z + dz[i];
                    if (nz >= 0 && nz < h) {
                        // 방문하지 않았으면
                        if (tomato[nz][x][y] == 0) {
                            tomato[nz][x][y] = tomato[z][x][y] + 1;
                            q.offer(new Tomato(nz, x, y));
                        }
                    }
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if (tomato[z][nx][ny] == 0) {
                        tomato[z][nx][ny] = tomato[z][x][y] + 1;
                        q.offer(new Tomato(z, nx, ny));
                    }
                }
            }
        }

        for (int k = 0; k < h; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (tomato[k][i][j] == 0) {
                        return -1;
                    }
                }
            }
        }

        return max_day - 1;
    }
}
