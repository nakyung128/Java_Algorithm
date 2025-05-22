import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ4386_별자리만들기 {
    static int n;
    static double answer;
    static boolean[] visited;
    static PriorityQueue<Node> pq;
    static ArrayList<ArrayList<Node>> arr;

    static class Node implements Comparable<Node> {
        int idx;
        double dist;

        Node(int idx, double dist) {
            this.idx = idx;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return (int) (this.dist - o.dist);
        }

        @Override
        public String toString() {
            return "idx: " + this.idx + ", dist: " + this.dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        double[][] point = new double[n][2];
        arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            arr.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            point[i][0] = Double.parseDouble(st.nextToken());
            point[i][1] = Double.parseDouble(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (i != j) {
                    double dis = Math
                            .sqrt(Math.pow(point[i][0] - point[j][0], 2) + Math.pow(point[i][1] - point[j][1], 2));
                    arr.get(i).add(new Node(j, dis));
                    arr.get(j).add(new Node(i, dis));
                }
            }
        }

        visited = new boolean[n];
        pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0));
        connect();
        System.out.printf("%.2f\n", answer);
    }

    static void connect() {
        int cnt = 0;
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (!visited[now.idx]) {
                visited[now.idx] = true;
                answer += now.dist;

                if (++cnt == n) {
                    break;
                }

                for (Node next : arr.get(now.idx)) {
                    if (!visited[next.idx]) {
                        pq.offer(next);
                    }
                }
            }
        }
    }
}
