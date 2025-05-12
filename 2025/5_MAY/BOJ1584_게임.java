import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ1584_게임 {
    static class Node {
        int x, y, level;

        Node(int x, int y, int level) {
            this.x = x;
            this.y = y;
            this.level = level;
        }
    }

    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static int[][] map;
    static boolean[][] visited;
    static ArrayDeque<Node> dq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        map = new int[501][501];
        visited = new boolean[501][501];

        int n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int xLen = Math.abs(x1 - x2);
            int yLen = Math.abs(y1 - y2);

            int startX = Math.min(x1, x2);
            int startY = Math.min(y1, y2);

            xLen = startX + xLen + 1;
            yLen = startY + yLen + 1;

            for (int xx = startX; xx < xLen; xx++) {
                for (int yy = startY; yy < yLen; yy++) {
                    map[xx][yy] = 1; // 위험한 구역
                }
            }
        }
        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int xLen = Math.abs(x1 - x2);
            int yLen = Math.abs(y1 - y2);

            int startX = Math.min(x1, x2);
            int startY = Math.min(y1, y2);

            xLen = startX + xLen + 1;
            yLen = startY + yLen + 1;

            for (int xx = startX; xx < xLen; xx++) {
                for (int yy = startY; yy < yLen; yy++) {
                    map[xx][yy] = 2; // 죽음의 구역 (들어갈 수 없음)
                }
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        dq = new ArrayDeque<>();
        dq.offer(new Node(0, 0, 0));
        visited[0][0] = true;

        while (!dq.isEmpty()) {
            Node now = dq.poll();
            if (now.x == 500 && now.y == 500) {
                return now.level;
            }
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (isValid(nx, ny)) {
                    visited[nx][ny] = true;
                    if (map[nx][ny] == 0) {
                        dq.offerFirst(new Node(nx, ny, now.level));
                    } else {
                        dq.offerLast(new Node(nx, ny, now.level + 1));
                    }
                }
            }
        }

        return -1;
    }

    static boolean isValid(int x, int y) {
        if (x < 0 || x > 500 || y < 0 || y > 500) {
            return false;
        } else if (visited[x][y]) {
            return false;
        } else if (map[x][y] == 2) {
            return false;
        }
        return true;
    }
}
