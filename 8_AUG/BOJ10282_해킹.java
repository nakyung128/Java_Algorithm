import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ10282_해킹 {
    static class Node implements Comparable<Node> {
        int idx;
        int sec;

        Node(int idx, int sec) {
            this.idx = idx;
            this.sec = sec;
        }

        @Override
        public int compareTo(Node o) {
            return this.sec - o.sec;
        }
    }

    static int n, d, c;
    static ArrayList<ArrayList<Node>> graph;
    static boolean[] visited;
    static int[] cost;
    static PriorityQueue<Node> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());

        for (int t = 0; t < tc; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // 컴퓨터의 개수
            d = Integer.parseInt(st.nextToken()); // 의존성 개수
            c = Integer.parseInt(st.nextToken()); // 해킹당한 컴퓨터 번호

            graph = new ArrayList<>();

            for (int i = 0; i < n + 1; i++) {
                graph.add(new ArrayList<>());
            }

            visited = new boolean[n + 1];
            cost = new int[n + 1];
            pq = new PriorityQueue<>();

            Arrays.fill(cost, Integer.MAX_VALUE);

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                graph.get(b).add(new Node(a, s)); // b 감염되면 a 감염 b -> a
            }

            int com_cnt = 0;
            int answer = 0;
            cost[c] = 0;
            pq.add(new Node(c, 0));

            infection();

            for (int i = 1; i < n + 1; i++) {
                if (cost[i] != Integer.MAX_VALUE) {
                    com_cnt++;
                    answer = Math.max(answer, cost[i]);
                }
            }

            System.out.println(com_cnt + " " + answer);
        }
    }

    static void infection() {
        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (!visited[now.idx]) {
                visited[now.idx] = true;

                for (Node node : graph.get(now.idx)) {
                    if (cost[node.idx] > cost[now.idx] + node.sec) {
                        cost[node.idx] = cost[now.idx] + node.sec;
                        pq.offer(new Node(node.idx, cost[node.idx]));
                    }
                }
            }
        }
    }
}