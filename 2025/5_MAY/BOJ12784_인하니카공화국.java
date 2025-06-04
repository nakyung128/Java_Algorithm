import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ12784_인하니카공화국 {
    static class Node {
        int idx, dist;

        Node(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }
    }

    static ArrayList<ArrayList<Node>> graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < t; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 섬의 수
            int m = Integer.parseInt(st.nextToken()); // 다리의 수

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
                graph.get(b).add(new Node(a, d));
            }
            visited = new boolean[n + 1];
            if (n == 1) {
                sb.append(0 + "\n");
            } else {
                sb.append(dfs(1) + "\n");
            }
        }
        System.out.print(sb);
    }

    static int dfs(int now) {
        visited[now] = true;
        int total = 0;
        boolean isLeaf = true;

        for (Node next : graph.get(now)) {
            if (!visited[next.idx]) {
                isLeaf = false;
                int cost = dfs(next.idx);
                total += Math.min(cost, next.dist);
            }
        }
        if (isLeaf) // 리프 노드라면 반드시 그 간선을 끊게 하도록 큰 수 반환
            return 1000000000;
        return total;
    }
}
