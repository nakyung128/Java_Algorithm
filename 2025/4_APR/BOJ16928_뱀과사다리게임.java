import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16928_뱀과사다리게임 {
    static ArrayList<int[]> snakes, ladders;
    static Queue<Node> q;
    static boolean[] visited;
    static int answer;

    static class Node {
        int idx, cnt;

        Node(int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 사다리 수
        int m = Integer.parseInt(st.nextToken()); // 뱀 수

        ladders = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            ladders.add(new int[] { x, y });
        }

        snakes = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            snakes.add(new int[] { u, v });
        }

        visited = new boolean[101];
        q = new LinkedList<>();
        q.offer(new Node(1, 0));
        answer = Integer.MAX_VALUE;
        bfs();
        System.out.println(answer);
    }

    static void bfs() {
        while (!q.isEmpty()) {
            Node now = q.poll();
            if (now.idx == 100) {
                answer = Math.min(answer, now.cnt);
            }
            for (int i = 1; i <= 6; i++) {
                int next = now.idx + i;
                if (next > 100) {
                    continue;
                }

                // 사다리 / 뱀 확인
                for (int[] l : ladders) {
                    if (l[0] == next) {
                        next = l[1];
                        break;
                    }
                }
                for (int[] s : snakes) {
                    if (s[0] == next) {
                        next = s[1];
                        break;
                    }
                }
                if (!visited[next]) {
                    q.offer(new Node(next, now.cnt + 1));
                    visited[next] = true;
                }
            }
        }
    }
}
