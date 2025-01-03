import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;

class House implements Comparable<House> {
    int idx;
    int cost;

    House(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }

    @Override
    public int compareTo(House o) {
        return this.cost - o.cost;
    }
}

public class BOJ1647_도시분할계획 {
    static int n, m, max_cost, answer;
    static ArrayList<ArrayList<House>> graph;
    static boolean[] visited;
    static PriorityQueue<House> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 집의 개수
        m = Integer.parseInt(st.nextToken()); // 길의 개수

        graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(from).add(new House(to, cost));
            graph.get(to).add(new House(from, cost));
        }

        visited = new boolean[n + 1];
        pq = new PriorityQueue<>();
        answer = 0;
        max_cost = 0;
        pq.offer(new House(1, 0));
        prim();

        // 두 개로 분리할 거니까 제일 큰 비용 전체에서 빼기 ==> 두 개로 분리
        System.out.println(answer - max_cost);
    }

    static void prim() {
        int cnt = 0;

        while (!pq.isEmpty()) {
            House now = pq.poll();

            if (!visited[now.idx]) {
                visited[now.idx] = true;
                answer += now.cost;
                max_cost = Math.max(max_cost, now.cost);

                if (++cnt == n) {
                    break;
                }

                for (House h : graph.get(now.idx)) {
                    if (!visited[h.idx]) {
                        pq.offer(h);
                    }
                }
            }
        }
    }
}
