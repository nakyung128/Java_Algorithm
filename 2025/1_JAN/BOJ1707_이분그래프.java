import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1707_이분그래프 {
    static boolean flag;
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;
    static int[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());

        for (int tc = 0; tc < k; tc++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph = new ArrayList<>();
            visited = new boolean[v + 1];
            check = new int[v + 1];

            for (int i = 0; i < v + 1; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                graph.get(a).add(b);
                graph.get(b).add(a);
            }

            flag = false;

            for (int i = 1; i <= v; i++) {
                if (!flag) {
                    dfs(i);
                } else {
                    System.out.println("NO");
                    break;
                }
            }
            if (!flag) {
                System.out.println("YES");
            }
        }
    }

    static void dfs(int idx) {
        visited[idx] = true;
        for (int v : graph.get(idx)) {
            if (!visited[v]) {
                // 나랑 다른 집합으로 처리하기
                check[v] = (check[idx] + 1) % 2;
                dfs(v);
            } else if (check[v] == check[idx]) {
                flag = true;
            }
        }
    }
}
