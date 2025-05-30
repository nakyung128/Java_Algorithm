import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class BOJ3584_가장가까운공통조상 {
    static int n;
    static ArrayList<ArrayList<Integer>> tree;
    static int[] parent, depth;
    static boolean[] hasParent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        for (int tc = 0; tc < t; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // 노드의 수
            tree = new ArrayList<>();
            parent = new int[n + 1];
            depth = new int[n + 1];
            hasParent = new boolean[n + 1];

            for (int i = 0; i < n + 1; i++) {
                tree.add(new ArrayList<>());
            }
            for (int i = 0; i < n - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                tree.get(a).add(b);
                hasParent[b] = true;
            }

            // 루트 찾기
            int root = 1;
            for (int i = 1; i < n + 1; i++) {
                if (!hasParent[i]) {
                    root = i;
                    break;
                }
            }
            dfs(root, 0, 0);

            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            sb.append(lca(n1, n2) + "\n");
        }
        System.out.print(sb.toString());
    }

    static void dfs(int now, int par, int d) {
        parent[now] = par;
        depth[now] = d;
        for (int next : tree.get(now)) {
            dfs(next, now, d + 1);
        }
    }

    static int lca(int n1, int n2) {
        // 깊이 맞추기
        while (depth[n1] > depth[n2]) {
            n1 = parent[n1];
        }
        while (depth[n2] > depth[n1]) {
            n2 = parent[n2];
        }
        // 공통 조상 찾기
        while (n1 != n2) {
            n1 = parent[n1];
            n2 = parent[n2];
        }
        return n1;
    }
}
