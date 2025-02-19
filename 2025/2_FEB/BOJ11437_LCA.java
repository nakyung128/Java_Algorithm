import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ11437_LCA {
    static ArrayList<ArrayList<Integer>> tree;
    static int N, M;
    static int[] parent, depth;
    static boolean[] visited;
    static Queue<Integer> q;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        tree = new ArrayList<>();
        parent = new int[N + 1];
        depth = new int[N + 1];
        visited = new boolean[N + 1];

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
        q.add(1);
        visited[1] = true;
        bfs(); // 깊이, 부모 계산

        M = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            find(a, b);
        }
        System.out.print(sb.toString());
    }

    static void find(int a, int b) {
        // 만약에 a가 b보다 깊은 경우 두 개 바꿔 주기
        // b가 더 깊도록 변경
        if (depth[a] > depth[b]) {
            int temp = b;
            b = a;
            a = temp;
        }

        // 두 노드의 깊이 맞춰 주기
        while (depth[a] != depth[b]) {
            b = parent[b];
        }

        // 같은 조상이 나올 때까지 같이 한 칸씩 올라가기
        while (a != b) {
            a = parent[a];
            b = parent[b];
        }
        sb.append(a + "\n");
    }

    static void bfs() {
        int level = 1;
        int cnt = 0;
        int size = 1;
        while (!q.isEmpty()) {
            int now = q.poll();
            for (int next : tree.get(now)) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.offer(next);
                    parent[next] = now;
                    depth[next] = level;
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
