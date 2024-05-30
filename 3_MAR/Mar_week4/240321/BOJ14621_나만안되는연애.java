import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Univ implements Comparable<Univ> {
    int idx;
    int cost;

    Univ(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }

    @Override
    public int compareTo(Univ o) {
        return this.cost - o.cost;
    }
}

public class BOJ14621_나만안되는연애 {
    static int n;
    static ArrayList<ArrayList<Univ>> graph;
    static PriorityQueue<Univ> pq;
    static boolean[] visited;
    static int answer, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        int[] g = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
            if (st.nextToken().equals("M")) {
                g[i] = 1;
            } else {
                g[i] = 2;
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (g[a] != g[b]) {
                graph.get(a).add(new Univ(b, c));
                graph.get(b).add(new Univ(a, c));
            }
        }

        visited = new boolean[n + 1];
        pq = new PriorityQueue<>();
        answer = 0;
        cnt = 0;

        pq.offer(new Univ(1, 0));

        prim();

        if (cnt != n)
            System.out.println(-1);
        else
            System.out.println(answer);
    }

    static void prim() {
        while (!pq.isEmpty()) {
            Univ me = pq.poll();

            if (!visited[me.idx]) {
                visited[me.idx] = true;

                answer += me.cost;

                if (++cnt == n)
                    break;

                for (Univ u : graph.get(me.idx)) {
                    if (!visited[u.idx]) {
                        pq.offer(u);
                    }
                }
            }
        }
    }
}