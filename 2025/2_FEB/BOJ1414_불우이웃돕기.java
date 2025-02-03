import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

class BOJ1414_불우이웃돕기 {
    static ArrayList<ArrayList<Node>> graph;
    static int n, cnt, connect;
    static boolean[] visited;
    static PriorityQueue<Node> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        int all_len = 0;
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                char x = line.charAt(j);
                int len = 0;

                if (x >= 'a' && x <= 'z') {
                    len = x - 'a' + 1;
                } else if (x >= 'A' && x <= 'Z') {
                    len = x - 'A' + 27;
                }

                all_len += len;
                if (len > 0 && i != j) {
                    graph.get(i).add(new Node(j, len));
                    graph.get(j).add(new Node(i, len));
                }
            }
        }

        pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0));

        prim();

        if (cnt == n) {
            System.out.println(all_len - connect);
        } else {
            System.out.println(-1);
        }
    }

    static void prim() {
        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (!visited[now.idx]) {
                visited[now.idx] = true;
                connect += now.len;

                if (++cnt == n) {
                    break;
                }

                for (Node next : graph.get(now.idx)) {
                    if (!visited[next.idx]) {
                        pq.offer(next);
                    }
                }
            }
        }
    }
}

class Node implements Comparable<Node> {
    int idx, len;

    Node(int idx, int len) {
        this.idx = idx;
        this.len = len;
    }

    @Override
    public int compareTo(Node o) {
        return this.len - o.len;
    }
}