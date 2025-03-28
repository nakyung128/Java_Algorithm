import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3055_탈출 {
    static class Node {
        int x, y, time;

        Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    static int r, c;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static Queue<Node> q;
    static Queue<Node> waterQ;
    static Node s, d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        visited = new boolean[r][c];
        q = new LinkedList<>();
        waterQ = new LinkedList<>();

        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'S') {
                    s = new Node(i, j, 0);
                    q.add(s);
                } else if (map[i][j] == 'D') {
                    d = new Node(i, j, 0);
                } else if (map[i][j] == '*') {
                    waterQ.add(new Node(i, j, 0));
                }
            }
        }

        visited[s.x][s.y] = true;
        if (!bfs()) {
            System.out.println("KAKTUS");
        }
    }

    static boolean bfs() {
        while (!q.isEmpty()) {
            water();
            int size = q.size();

            for (int s = 0; s < size; s++) {
                Node now = q.poll();

                if (now.x == d.x && now.y == d.y) {
                    System.out.println(now.time);
                    return true;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];

                    if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
                        if (!visited[nx][ny] && map[nx][ny] != 'X' && map[nx][ny] != '*') {
                            visited[nx][ny] = true;
                            q.offer(new Node(nx, ny, now.time + 1));
                        }
                    }
                }
            }
        }
        return false;
    }

    // 물 확장
    static void water() {
        int size = waterQ.size();
        for (int s = 0; s < size; s++) {
            Node water = waterQ.poll();
            for (int i = 0; i < 4; i++) {
                int nx = water.x + dx[i];
                int ny = water.y + dy[i];

                if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
                    if (map[nx][ny] == '.') {
                        map[nx][ny] = '*';
                        waterQ.add(new Node(nx, ny, 0));
                    }
                }
            }
        }
    }
}
