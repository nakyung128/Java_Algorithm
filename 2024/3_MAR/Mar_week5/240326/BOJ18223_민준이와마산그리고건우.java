import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
    int idx;
    int cost;

    Edge(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }
}

public class BOJ18223_민준이와마산그리고건우 {
    static ArrayList<ArrayList<Edge>> graph;
    static boolean[] visited;
    static PriorityQueue<Edge> pq;
    static int v, e, p;
    static int[] costs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i < v + 1; i++) {
            graph.add(new ArrayList<>());
        }

        visited = new boolean[v + 1];

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Edge(to, cost));
            graph.get(to).add(new Edge(from, cost));
        }

        costs = new int[v + 1];
        for (int i = 0; i < v + 1; i++) {
            costs[i] = Integer.MAX_VALUE;
        }
        // 건우 => 마산
        costs[p] = 0;
        find(p);
        int first = costs[v];

        visited = new boolean[v + 1];
        costs = new int[v + 1];
        for (int i = 0; i < v + 1; i++) {
            costs[i] = Integer.MAX_VALUE;
        }
        // 민준 => 건우
        costs[1] = 0;
        find(1);
        int second = costs[p];

        if (first + second <= costs[v]) {
            System.out.println("SAVE HIM");
        } else {
            System.out.println("GOOD BYE");
        }
    }

    static void find(int start) {
        pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge now = pq.poll();

            if (!visited[now.idx]) {
                visited[now.idx] = true;

                for (Edge e : graph.get(now.idx)) {
                    if (costs[e.idx] > costs[now.idx] + e.cost) {
                        costs[e.idx] = costs[now.idx] + e.cost;
                        pq.offer(new Edge(e.idx, costs[e.idx]));
                    }
                }
            }
        }
    }
}
