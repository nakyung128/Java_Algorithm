import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ13904_과제 {
    static class Node implements Comparable<Node> {
        int d, w;

        Node(int d, int w) {
            this.d = d;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            if (this.w == o.w) {
                return o.d - this.d;
            }
            return o.w - this.w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int max_day = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            pq.offer(new Node(d, w));
            max_day = Math.max(max_day, d);
        }

        boolean[] check = new boolean[max_day + 1];
        int answer = 0;
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (!check[now.d]) {
                check[now.d] = true;
                answer += now.w;
            } else {
                for (int i = now.d - 1; i > 0; i--) {
                    if (!check[i]) {
                        check[i] = true;
                        answer += now.w;
                        break;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
