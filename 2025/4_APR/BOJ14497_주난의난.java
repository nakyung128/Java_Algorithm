import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ14497_주난의난 {
    static int n, m, x1, y1, x2, y2, answer;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static char[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        x1 = Integer.parseInt(st.nextToken()) - 1;
        y1 = Integer.parseInt(st.nextToken()) - 1;
        x2 = Integer.parseInt(st.nextToken()) - 1;
        y2 = Integer.parseInt(st.nextToken()) - 1;

        map = new char[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        System.out.println(bfs());
    }

    static int bfs() {
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.offer(new Node(x1, y1, 0));
        visited[x1][y1] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();
            if (now.x == x2 && now.y == y2) {
                return now.level;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }

                if (visited[nx][ny]) {
                    continue;
                }

                visited[nx][ny] = true;
                if (map[nx][ny] == '0') {
                    q.offerFirst(new Node(nx, ny, now.level));
                } else {
                    q.offerLast(new Node(nx, ny, now.level + 1));
                }
            }
        }
        return -1;
    }

    static class Node {
        int x, y, level;

        Node(int x, int y, int level) {
            this.x = x;
            this.y = y;
            this.level = level;
        }
    }
}
