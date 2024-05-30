import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1697_숨바꼭질 {
    static class Node {
        int x;
        int sec;

        Node(int x, int sec) {
            this.x = x;
            this.sec = sec;
        }
    }

    static int n, k;
    static Queue<Node> q;
    static int[] dx = { -1, 1, 2 };
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        q = new LinkedList<>();
        q.offer(new Node(n, 0));

        visited = new boolean[100001];

        bfs();
    }

    static void bfs() {
        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.x == k) {
                System.out.println(now.sec);
                System.exit(0);
            }

            // visited 처리 여기에!!!!
            visited[now.x] = true;

            for (int i = 0; i < 3; i++) {
                if (i < 2) {
                    int nx = now.x + dx[i];
                    if (nx >= 0 && nx < 100001 && !visited[nx]) {
                        q.offer(new Node(nx, now.sec + 1));
                    }
                } else {
                    if (now.x == 0)
                        continue;
                    int nx = now.x * dx[i];
                    if (nx >= 0 && nx < 100001 && !visited[nx]) {
                        q.offer(new Node(nx, now.sec + 1));
                    }
                }
            }
        }
    }
}