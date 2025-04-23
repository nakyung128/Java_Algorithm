import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BOJ13424_비밀모임 {
    static int n, m, k, min_value, min_idx;
    static int[] dist, answer;
    static boolean[] visited;
    static ArrayList<ArrayList<Node>> graph;
    static ArrayList<Integer> friends;
    static PriorityQueue<Node> q;

    static class Node implements Comparable<Node> {
        int idx, dis;

        Node(int idx, int dis) {
            this.idx = idx;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node o) {
            return this.dis - o.dis;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(st.nextToken());
        for (int tc = 0; tc < t; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            graph = new ArrayList<>();
            answer = new int[n + 1];

            for (int i = 0; i < n + 1; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                graph.get(a).add(new Node(b, c));
                graph.get(b).add(new Node(a, c));
            }

            k = Integer.parseInt(br.readLine());
            friends = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < k; i++) {
                friends.add(Integer.parseInt(st.nextToken()));
            }

            for (int friend : friends) {
                q = new PriorityQueue<>();
                dist = new int[n + 1];
                visited = new boolean[n + 1];
                Arrays.fill(dist, Integer.MAX_VALUE);
                dist[friend] = 0;
                visited[friend] = true;
                q.add(new Node(friend, 0));
                min_value = Integer.MAX_VALUE;
                find();
            }

            int min_value = Integer.MAX_VALUE;
            int min_idx = 0;
            for (int i = 1; i < n + 1; i++) {
                if (answer[i] < min_value) {
                    min_value = answer[i];
                    min_idx = i;
                }
            }
            sb.append(min_idx + "\n");
        }
        System.out.print(sb.toString());
    }

    static void find() {
        while (!q.isEmpty()) {
            Node now = q.poll();
            visited[now.idx] = true;
            for (Node next : graph.get(now.idx)) {
                if (!visited[next.idx]) {
                    if (dist[next.idx] > dist[now.idx] + next.dis) {
                        dist[next.idx] = dist[now.idx] + next.dis;
                        q.offer(new Node(next.idx, dist[next.idx]));
                    }
                }
            }
        }
        for (int i = 1; i < n + 1; i++) {
            answer[i] += dist[i];
        }
    }
}
