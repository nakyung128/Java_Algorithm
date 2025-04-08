import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2644_촌수계산 {
    static ArrayList<ArrayList<Integer>> arr;
    static int n, a, b, m;
    static boolean[] visited;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        visited = new boolean[n + 1];

        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        m = Integer.parseInt(br.readLine());
        arr = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            arr.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr.get(x).add(y);
            arr.get(y).add(x);
        }

        dfs(a, 0);
        if (!flag) {
            System.out.println(-1);
        }
    }

    static void dfs(int idx, int step) {
        visited[idx] = true;
        if (idx == b) {
            System.out.println(step);
            flag = true;
        }
        for (int p : arr.get(idx)) {
            if (!visited[p]) {
                dfs(p, step + 1);
            }
        }
    }
}
