import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ14226_이모티콘 {
    static int n;
    static Queue<Node> q;
    static boolean visited[][];

    static class Node {
        int clipboard, cnt, sec;

        public Node(int clipboard, int cnt, int sec) {
            this.clipboard = clipboard;
            this.cnt = cnt;
            this.sec = sec;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        q = new LinkedList<>();
        visited = new boolean[2001][2001]; // visited[cnt][clipboard]
        q.offer(new Node(0, 1, 0));
        visited[1][0] = true;
        System.out.println(bfs());
    }

    static int bfs() {
        while (!q.isEmpty()) {
            Node now = q.poll();
            if (now.cnt == n) {
                return now.sec;
            }

            // 복사
            if (!visited[now.cnt][now.cnt]) {
                visited[now.cnt][now.cnt] = true;
                q.offer(new Node(now.cnt, now.cnt, now.sec + 1));
            }

            // 붙여넣기
            if (now.clipboard > 0 && now.cnt + now.clipboard < 2001) {
                visited[now.cnt + now.clipboard][now.clipboard] = true;
                q.offer(new Node(now.clipboard, now.cnt + now.clipboard, now.sec + 1));
            }

            // 하나 삭제
            if (now.cnt > 1) {
                if (!visited[now.cnt - 1][now.clipboard]) {
                    visited[now.cnt - 1][now.clipboard] = true;
                    q.offer(new Node(now.clipboard, now.cnt - 1, now.sec + 1));
                }
            }
        }
        return -1;
    }
}
