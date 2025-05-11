import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ13913_숨바꼭질4 {
    static int n, k;
    static int[] dx = { 1, -1, 2 };
    static boolean[] visited;
    static int[] root;
    static Queue<Node> q;

    static class Node {
        int idx, time;

        Node(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        q = new LinkedList<>();
        q.add(new Node(n, 0));

        visited = new boolean[200002];
        root = new int[200002];
        visited[n] = true;
        bfs();
    }

    static void bfs() {
        while (!q.isEmpty()) {
            Node now = q.poll();
            int x = now.idx;
            if (x == k) {
                System.out.println(now.time);
                Stack<Integer> path = new Stack<>();
                for (int i = k; i != n; i = root[i]) {
                    path.push(i);
                }
                path.push(n);
                StringBuilder sb = new StringBuilder();
                while (!path.isEmpty()) {
                    sb.append(path.pop() + " ");
                }
                System.out.println(sb.toString());
                break;
            }

            for (int i = 0; i < 3; i++) {
                int nx = x + dx[i];
                if (i == 2) {
                    nx = x * dx[i];
                }
                if (isValid(nx)) {
                    visited[nx] = true;
                    q.add(new Node(nx, now.time + 1));
                    root[nx] = x;
                }
            }

        }
    }

    static boolean isValid(int x) {
        if (x >= 0 && x < 200002 && !visited[x]) {
            return true;
        }
        return false;
    }
}
