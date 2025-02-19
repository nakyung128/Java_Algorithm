import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1761_정점들의거리 {
    static int N, M, K;
    static boolean[] visited;
    static int[] depth, parent, distance;
    static ArrayList<ArrayList<Node>> tree;
    static Queue<Node> q;

    static class Node {
        int idx, distance;

        public Node(int idx, int distance) {
            this.idx = idx;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];
        depth = new int[N + 1];
        parent = new int[N + 1];
        distance = new int[N + 1];
        tree = new ArrayList<>();

        for (int i = 0; i < N + 1; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            tree.get(a).add(new Node(b, distance));
            tree.get(b).add(new Node(a, distance));
        }

        q = new LinkedList<>();
        q.offer(new Node(1, 0));
        visited[1] = true;
        bfs();

        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(find(a, b) + "\n");
        }
        System.out.print(sb.toString());
    }

    static int find(int a, int b) {
        int answer = 0;

        if (depth[a] > depth[b]) {
            int temp = b;
            b = a;
            a = temp;
        }

        while (depth[a] != depth[b]) {
            answer += distance[b];
            b = parent[b];
        }

        while (a != b) {
            answer += distance[a];
            answer += distance[b];
            a = parent[a];
            b = parent[b];
        }

        return answer;
    }

    static void bfs() {
        int cnt = 0;
        int size = 1;
        int level = 1;

        while (!q.isEmpty()) {
            Node now = q.poll();
            for (Node next : tree.get(now.idx)) {
                if (!visited[next.idx]) {
                    visited[next.idx] = true;
                    q.offer(next);
                    depth[next.idx] = level;
                    parent[next.idx] = now.idx;
                    distance[next.idx] = next.distance;
                }
            }
            cnt++;
            if (cnt == size) {
                cnt = 0;
                size = q.size();
                level++;
            }
        }
    }
}
