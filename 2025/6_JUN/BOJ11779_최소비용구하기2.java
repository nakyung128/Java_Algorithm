import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;

public class BOJ11779_최소비용구하기2 {
    static class Node implements Comparable<Node> {
        int idx, d;

        Node(int idx, int d) {
            this.idx = idx;
            this.d = d;
        }

        @Override
        public int compareTo(Node o) {
            return this.d - o.d;
        }
    }

    static int n, m, s, e;
    static ArrayList<ArrayList<Node>> graph;
    static boolean[] visited;
    static int[] dist;
    static int[] root;
    static PriorityQueue<Node> q;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        visited = new boolean[n + 1];
        dist = new int[n + 1];
        root = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, d));
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        dist[s] = 0;
        q = new PriorityQueue<>();
        q.offer(new Node(s, 0));
        search();

        sb = new StringBuilder();
        sb.append(dist[e] + "\n");
        rootCheck(root);
        System.out.println(sb.toString());
    }

    static void search() {
        while (!q.isEmpty()) {
            Node now = q.poll();
            visited[now.idx] = true;
            if (now.idx == e) {
                break;
            }
            for (Node next : graph.get(now.idx)) {
                if (!visited[next.idx] && dist[next.idx] > dist[now.idx] + next.d) {
                    root[next.idx] = now.idx;
                    dist[next.idx] = dist[now.idx] + next.d;
                    q.offer(new Node(next.idx, dist[next.idx]));
                }
            }
        }
    }

    static void rootCheck(int[] root) {
        Stack<Integer> stack = new Stack<>();

        int prev = e;
        stack.add(prev);
        while (prev != s) {
            stack.add(root[prev]);
            prev = root[prev];
        }

        sb.append(stack.size() + "\n");
        while (!stack.isEmpty()) {
            sb.append(stack.pop() + " ");
        }
    }
}
