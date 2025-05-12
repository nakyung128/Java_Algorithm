import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ22865_가장먼곳 {
    static class Node implements Comparable<Node> {
        int x, dist;

        Node(int x, int dist) {
            this.x = x;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }

    static int n, cnt;
    static ArrayList<ArrayList<Node>> graph;
    static PriorityQueue<Node> pq;
    static int[][] dist;
    static int[] friends;
    static int maxDist, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        friends = new int[3];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            friends[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, d));
            graph.get(b).add(new Node(a, d));
        }

        // 각 친구 집별로의 거리 저장하기
        dist = new int[3][n + 1];
        for (int[] d : dist) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }

        // 각 친구의 집에서 다른 집까지의 거리 구하기
        for (int friend : friends) {
            pq = new PriorityQueue<>();
            pq.offer(new Node(friend, 0));
            dist[cnt][friend] = 0;
            cal();
            cnt++;
        }

        // 1 ~ n 집 중에 친구들과의 최소 거리가 가장 큰 집 찾기
        for (int i = 1; i < n + 1; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < 3; j++) {
                min = Math.min(min, dist[j][i]);
            }
            if (min > maxDist) {
                maxDist = min;
                answer = i;
            }
        }
        System.out.println(answer);
    }

    // 다익스트리
    static void cal() {
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            for (Node next : graph.get(now.x)) {
                if (dist[cnt][next.x] > dist[cnt][now.x] + next.dist) {
                    dist[cnt][next.x] = dist[cnt][now.x] + next.dist;
                    pq.offer(new Node(next.x, dist[cnt][next.x]));
                }
            }
        }
    }
}
