import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ11438_LCA2 {
    static int N, M, K;
    static int[] depth;
    static boolean[] visited;
    static int[][] parent;
    static Queue<Integer> q;
    static ArrayList<ArrayList<Integer>> tree;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        depth = new int[N + 1];
        visited = new boolean[N + 1];

        K = 0;
        while (true) {
            if (Math.pow(2, K) > N) {
                break;
            }
            K++;
        }
        K -= 1;

        parent = new int[K + 1][N + 1];
        tree = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        q = new LinkedList<>();
        q.offer(1);
        visited[1] = true;
        bfs(); // depth 채우기

        // parent 배열 채우기
        for (int i = 1; i < K + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                parent[i][j] = parent[i - 1][parent[i - 1][j]];
            }
        }

        sb = new StringBuilder();
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            find(a, b);
        }
        System.out.print(sb.toString());
    }

    static void find(int a, int b) {
        if (depth[a] > depth[b]) { // 더 깊은 depth가 b가 되도록
            int temp = b;
            b = a;
            a = temp;
        }

        // 깊이 맞추기
        for (int i = K; i >= 0; i--) {
            if (Math.pow(2, i) <= depth[b] - depth[a]) {
                if (depth[a] <= depth[parent[i][b]]) {
                    b = parent[i][b];
                }
            }
        }

        // 부모 찾기
        for (int i = K; i >= 0; i--) {
            if (parent[i][a] != parent[i][b]) {
                a = parent[i][a];
                b = parent[i][b];
            }
        }

        if (a == b) {
            sb.append(a + "\n");
        } else {
            sb.append(parent[0][a] + "\n");
        }
    }

    static void bfs() {
        int cnt = 0;
        int level = 1;
        int size = 1;

        while (!q.isEmpty()) {
            int now = q.poll();
            for (int next : tree.get(now)) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.offer(next);
                    depth[next] = level;
                    parent[0][next] = now;
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
