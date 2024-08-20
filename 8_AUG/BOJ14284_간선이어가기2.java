import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ14284_간선이어가기2 {
    static class Node implements Comparable<Node> {
        int p;
        int w;

        Node(int p, int w) {
            this.p = p;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }

    static int n, m, s, t;
    static ArrayList<ArrayList<Node>> graph;
    static boolean[] visited;
    static int[] cost;
    static PriorityQueue<Node> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        visited = new boolean[n + 1];
        cost = new int[n + 1];
        Arrays.fill(cost, Integer.MAX_VALUE);

        cost[s] = 0;
        visited[s] = true;

        pq = new PriorityQueue<>();
        pq.add(new Node(s, 0));

        daik(s);

        System.out.println(cost[t]);
    }

    static void daik(int idx) {
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (node.p == t) {
                break;
            }

            for (Node n : graph.get(node.p)) {
                if (!visited[n.p]) {
                    if (cost[n.p] > cost[node.p] + n.w) {
                        cost[n.p] = cost[node.p] + n.w;
                        pq.add(new Node(n.p, cost[n.p]));
                    }
                }
            }
        }
    }
}