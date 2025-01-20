import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 모든 건물을 짓는데 걸리는 최소의 시간을 이용하여 근사 => 위상정렬 + 우선순위 큐
public class BOJ1516_게임개발 {
    static int[] deep, time;
    static PriorityQueue<Build> q;
    static ArrayList<ArrayList<Integer>> graph;

    static class Build implements Comparable<Build> {
        int idx;
        int time;

        Build(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }

        @Override
        public int compareTo(Build o) {
            return this.time - o.time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        deep = new int[n + 1];
        time = new int[n + 1];

        graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        q = new PriorityQueue<>();

        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            time[i] = t;

            while (true) {
                int next = Integer.parseInt(st.nextToken());
                if (next == -1) {
                    break;
                } else {
                    graph.get(next).add(i);
                    deep[i]++;
                }
            }

            if (deep[i] == 0) {
                q.add(new Build(i, time[i]));
            }
        }

        time();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n + 1; i++) {
            sb.append(time[i] + "\n");
        }
        System.out.print(sb.toString());
    }

    static void time() {
        while (!q.isEmpty()) {
            Build now = q.poll();

            for (int b : graph.get(now.idx)) {
                if (--deep[b] == 0) {
                    time[b] += time[now.idx];
                    q.offer(new Build(b, time[b]));
                }
            }
        }
    }
}
