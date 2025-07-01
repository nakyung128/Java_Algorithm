import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ6593_상범빌딩 {
    static class Point {
        int l, r, c, t;

        Point(int l, int r, int c, int t) {
            this.l = l;
            this.r = r;
            this.c = c;
            this.t = t;
        }
    }

    static int L, R, C;
    static char[][][] map;
    static int[] end;
    static boolean[][][] visited;
    static Queue<Point> q;
    static int[] dz = { -1, 1, 0, 0, 0, 0 };
    static int[] dx = { 0, 0, -1, 1, 0, 0 };
    static int[] dy = { 0, 0, 0, 0, -1, 1 };
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        sb = new StringBuilder();
        while (true) {
            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            end = new int[3];

            if (L == 0 && R == 0 && C == 0) {
                break;
            }
            map = new char[L][R][C];
            visited = new boolean[L][R][C];
            q = new LinkedList<>();

            for (int k = 0; k < L; k++) {
                for (int i = 0; i < R; i++) {
                    String line = br.readLine();
                    for (int j = 0; j < C; j++) {
                        map[k][i][j] = line.charAt(j);
                        if (map[k][i][j] == 'S') {
                            q.offer(new Point(k, i, j, 0));
                            visited[k][i][j] = true;
                        } else if (map[k][i][j] == 'E') {
                            end[0] = k;
                            end[1] = i;
                            end[2] = j;
                        }
                    }
                }
                br.readLine();
            }
            bfs();
        }
        System.out.print(sb.toString());
    }

    static void bfs() {
        while (!q.isEmpty()) {
            Point now = q.poll();
            if (now.l == end[0] && now.r == end[1] && now.c == end[2]) {
                sb.append("Escaped in " + now.t + " minute(s).\n");
                return;
            }
            for (int i = 0; i < 6; i++) {
                int nz = now.l + dz[i];
                int nx = now.r + dx[i];
                int ny = now.c + dy[i];

                if (nz >= 0 && nz < L && nx >= 0 && nx < R && ny >= 0 && ny < C) {
                    if (map[nz][nx][ny] != '#' && !visited[nz][nx][ny]) {
                        visited[nz][nx][ny] = true;
                        q.offer(new Point(nz, nx, ny, now.t + 1));
                    }
                }
            }
        }
        sb.append("Trapped!\n");
    }
}
