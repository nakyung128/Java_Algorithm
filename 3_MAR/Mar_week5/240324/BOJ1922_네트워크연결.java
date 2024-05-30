import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Network implements Comparable<Network> {
    int idx;
    int cost;

    Network(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }

    @Override
    public int compareTo(Network o) {
        return this.cost - o.cost;
    }
}

public class BOJ1922_네트워크연결 {
    static int n, m, answer;
    static ArrayList<ArrayList<Network>> tree;
    static boolean[] visited;
    static PriorityQueue<Network> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());

        tree = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            tree.get(from).add(new Network(to, cost));
            tree.get(to).add(new Network(from, cost));
        }

        visited = new boolean[n + 1];
        pq = new PriorityQueue<>();
        pq.offer(new Network(1, 0));
        prim();
        System.out.println(answer);
    }

    static void prim() {
        int cnt = 0;
        answer = 0;

        while (!pq.isEmpty()) {
            Network now = pq.poll();

            if (!visited[now.idx]) {
                visited[now.idx] = true;
                answer += now.cost;

                if (++cnt == n) {
                    break;
                }

                for (Network nw : tree.get(now.idx)) {
                    if (!visited[nw.idx]) {
                        pq.offer(nw);
                    }
                }
            }
        }
    }
}
