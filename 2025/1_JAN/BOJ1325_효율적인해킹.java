import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1325_효율적인해킹 {
    static int cnt;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> graph;
    static Queue<Integer> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        visited = new boolean[n + 1];
        graph = new ArrayList<>();

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(b).add(a);
        }

        int[] can = new int[n + 1];
        int max_value = 0;

        for (int i = 1; i <= n; i++) {
            q = new LinkedList<>();
            visited = new boolean[n + 1];
            visited[i] = true;
            cnt = 1;
            q.offer(i);
            hack();
            can[i] = cnt;
            max_value = Math.max(max_value, cnt);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            if (can[i] == max_value) {
                sb.append(i + " ");
            }
        }

        System.out.println(sb.toString());
    }

    static void hack() {
        while (!q.isEmpty()) {
            int idx = q.poll();
            for (int com : graph.get(idx)) {
                if (!visited[com]) {
                    visited[com] = true;
                    cnt++;
                    q.offer(com);
                }
            }
        }
    }
}