import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class BOJ10423_전기가부족해 {
    static class Node implements Comparable<Node> {
        int idx, dist;

        Node(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }

    static int answer, n, k;
    static ArrayList<ArrayList<Node>> arr;
    static boolean[] visited;
    static int[] bals;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 도시의 개수
        int m = Integer.parseInt(st.nextToken()); // 설치 가능한 케이블의 수
        k = Integer.parseInt(st.nextToken()); // 발전소의 개수

        visited = new boolean[n + 1];
        bals = new int[k];
        arr = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            arr.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            bals[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            arr.get(u).add(new Node(v, w));
            arr.get(v).add(new Node(u, w));
        }
        connect();
        System.out.println(answer);
    }

    static void connect() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int bal : bals) {
            pq.offer(new Node(bal, 0));
        }
        int cnt = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (!visited[now.idx]) {
                visited[now.idx] = true;
                answer += now.dist;

                if (++cnt == n) {
                    break;
                }

                for (Node next : arr.get(now.idx)) {
                    if (!visited[next.idx]) {
                        pq.offer(next);
                    }
                }
            }
        }
    }
}
