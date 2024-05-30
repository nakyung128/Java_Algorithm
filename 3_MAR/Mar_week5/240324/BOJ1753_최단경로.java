import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
    int idx;
    int cost;

    Node(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }
}

public class BOJ1753_최단경로 {
    static ArrayList<ArrayList<Node>> tree;
    static int[] costs;
    static PriorityQueue<Node> pq;
    static boolean[] visited;
    static int v, e, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken()); // 시작 정점의 번호

        tree = new ArrayList<>();
        for (int i = 0; i < v + 1; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            tree.get(from).add(new Node(to, cost));
        }

        costs = new int[v + 1];

        for (int i = 0; i < v; i++) {
            costs[i + 1] = Integer.MAX_VALUE;
        }

        visited = new boolean[v + 1];
        pq = new PriorityQueue<>();
        pq.offer(new Node(k, 0));
        costs[k] = 0;
        find(k);

        for (int i = 1; i < v + 1; i++) {
            if (costs[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(costs[i]);
            }
        }
    }

    static void find(int start) {
        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (!visited[now.idx]) {
                visited[now.idx] = true;

                // 인접 노드 탐색
                for (Node n : tree.get(now.idx)) {
                    if (costs[n.idx] > costs[now.idx] + n.cost) {
                        costs[n.idx] = costs[now.idx] + n.cost;
                        pq.offer(new Node(n.idx, costs[n.idx]));
                    }
                }
            }
        }
    }
}
