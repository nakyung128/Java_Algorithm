import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Cow implements Comparable<Cow> {
    int idx;
    int cost;

    Cow(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }

    @Override
    public int compareTo(Cow o) {
        return this.cost - o.cost;
    }
}

public class BOJ5972_택배배송 {
    static ArrayList<ArrayList<Cow>> graph;
    static boolean[] visited;
    static PriorityQueue<Cow> pq;
    static int[] costs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        visited = new boolean[n + 1];
        costs = new int[n + 1];

        for (int i = 0; i < n + 1; i++) {
            costs[i] = Integer.MAX_VALUE;
        }

        graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Cow(to, cost));
            graph.get(to).add(new Cow(from, cost));
        }

        pq = new PriorityQueue<>();
        pq.offer(new Cow(1, 0));
        costs[1] = 0;
        find();
        System.out.println(costs[n]);
    }

    static void find() {
        while (!pq.isEmpty()) {
            Cow now = pq.poll();

            if (!visited[now.idx]) {
                visited[now.idx] = true;

                for (Cow c : graph.get(now.idx)) {
                    if (costs[c.idx] > costs[now.idx] + c.cost) {
                        costs[c.idx] = costs[now.idx] + c.cost;
                        pq.offer(new Cow(c.idx, costs[c.idx]));
                    }
                }
            }
        }
    }
}
