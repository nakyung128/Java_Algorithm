import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ13549_숨바꼭질3 {
    static class Node {
        int x;
        int sec;

        Node(int x, int sec) {
            this.x = x;
            this.sec = sec;
        }
    }

    static int n, k, answer, min_sec;
    static Queue<Node> q;
    static int[] dx = { -1, 1, 2 };
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        min_sec = Integer.MAX_VALUE;

        q = new LinkedList<>();
        q.offer(new Node(n, 0));

        visited = new boolean[1000001];

        bfs();

        System.out.println(min_sec);
    }

    static void bfs() {
        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.x == k) {
                min_sec = Math.min(min_sec, now.sec);
            }

            // visited 처리 여기에!!!!
            visited[now.x] = true;

            for (int i = 0; i < 3; i++) {
                if (i < 2) {
                    int nx = now.x + dx[i];
                    if (nx >= 0 && nx < 1000001 && !visited[nx] && now.sec + 1 < min_sec) {
                        q.offer(new Node(nx, now.sec + 1));
                    }
                } else {
                    if (now.x == 0)
                        continue;
                    int nx = now.x * dx[i];
                    if (nx < 1000001 && !visited[nx] && now.sec < min_sec) {
                        q.offer(new Node(nx, now.sec));
                    }
                }
            }
        }
    }
}
