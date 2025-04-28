import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ24479_알고리즘수업 {
    static int n, m, cnt;
    static boolean[] visited;
    static int[] order;
    static ArrayList<ArrayList<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        order = new int[n + 1];
        visited = new boolean[n + 1];
        graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        for (ArrayList<Integer> arr : graph) {
            arr.sort(null);
        }

        visited[r] = true;
        cnt = 1;
        dfs(r);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(order[i] + "\n");
        }
        System.out.print(sb.toString());
    }

    static void dfs(int idx) {
        order[idx] = cnt;
        for (int n : graph.get(idx)) {
            if (!visited[n]) {
                visited[n] = true;
                cnt++;
                dfs(n);
            }
        }
    }
}
