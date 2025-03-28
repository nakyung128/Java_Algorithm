import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ4179_불 {
    static class Node {
        int x, y, time;

        Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    static int r, c;
    static Queue<Node> q;
    static Queue<Node> fireQ;
    static char[][] map;
    static boolean[][] visited;
    static boolean[][] fireVisited;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        visited = new boolean[r][c];
        fireVisited = new boolean[r][c];
        q = new LinkedList<>();
        fireQ = new LinkedList<>();

        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'J') {
                    visited[i][j] = true;
                    q.add(new Node(i, j, 0));
                } else if (map[i][j] == 'F') {
                    fireVisited[i][j] = true;
                    fireQ.add(new Node(i, j, 0));
                }
            }
        }

        if (!bfs()) {
            System.out.println("IMPOSSIBLE");
        }
    }

    static boolean bfs() {
        while (!q.isEmpty()) {
            fire();
            int size = q.size();
            for (int s = 0; s < size; s++) {
                Node now = q.poll();

                if (now.x == 0 || now.x == r - 1 || now.y == 0 || now.y == c - 1) {
                    System.out.println(now.time + 1);
                    return true;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];

                    if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
                        if (!visited[nx][ny] && map[nx][ny] != '#' && map[nx][ny] != 'F') {
                            visited[nx][ny] = true;
                            q.offer(new Node(nx, ny, now.time + 1));
                        }
                    }
                }
            }
        }
        return false;
    }

    static void fire() {
        int size = fireQ.size();
        for (int s = 0; s < size; s++) {
            Node fire = fireQ.poll();
            for (int i = 0; i < 4; i++) {
                int nx = fire.x + dx[i];
                int ny = fire.y + dy[i];

                if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
                    if (!fireVisited[nx][ny] && map[nx][ny] != '#') {
                        map[nx][ny] = 'F';
                        fireVisited[nx][ny] = true;
                        fireQ.add(new Node(nx, ny, fire.time + 1));
                    }
                }
            }
        }
    }
}
