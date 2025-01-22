import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ2211_네트워크복구 {
    static int[] dist, parent;
    static ArrayList<ArrayList<Computer>> graph;
    static PriorityQueue<Computer> pq;
    static ArrayList<String> answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        dist = new int[n + 1];
        parent = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Computer(b, c));
            graph.get(b).add(new Computer(a, c));
        }

        dist[1] = 0;
        pq = new PriorityQueue<>();
        answer = new ArrayList<>();
        pq.offer(new Computer(1, 0));
        connect();

        int cnt = 0;
        for (int i = 1; i < n + 1; i++) {
            if (parent[i] != i && parent[i] != 0) {
                cnt++;
                answer.add(i + " " + parent[i]);
            }
        }

        System.out.println(cnt);
        for (String s : answer) {
            System.out.println(s);
        }
    }

    static void connect() {
        while (!pq.isEmpty()) {
            Computer now = pq.poll();
            for (Computer next : graph.get(now.idx)) {
                if (dist[next.idx] > dist[now.idx] + next.time) {
                    dist[next.idx] = dist[now.idx] + next.time;
                    pq.offer(new Computer(next.idx, dist[next.idx]));
                    parent[next.idx] = now.idx;
                }
            }
        }
    }
}

class Computer implements Comparable<Computer> {
    int idx, time;

    Computer(int idx, int time) {
        this.idx = idx;
        this.time = time;
    }

    @Override
    public int compareTo(Computer c) {
        return this.time - c.time;
    }
}
