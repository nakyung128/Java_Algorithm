import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ13023_ABCDE {
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;
    static int n, m;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[n];
        graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        flag = false;
        for (int i = 0; i < n; i++) {
            dfs(i, 0);
            visited[i] = false;

            if (flag) {
                System.out.println(1);
                System.exit(0);
            }
        }
        System.out.println(0);
    }

    static void dfs(int idx, int depth) {
        visited[idx] = true;

        if (depth == 4) {
            flag = true;
            return;
        }

        for (int num : graph.get(idx)) {
            if (!visited[num]) {
                dfs(num, depth + 1);
                visited[num] = false;
            }
        }
    }
}