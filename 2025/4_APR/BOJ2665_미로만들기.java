import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class BOJ2665_미로만들기 {
    static int n;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
        System.out.println(bfs());
    }

    static int bfs() {
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.offer(new Node(0, 0, 0));
        visited[0][0] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();
            if (now.x == n - 1 && now.y == n - 1) {
                return now.level;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                visited[nx][ny] = true;
                if (map[nx][ny] == 1) {
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
