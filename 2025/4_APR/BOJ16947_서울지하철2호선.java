import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ16947_서울지하철2호선 {
    static int n, len;
    static int[] distance;
    static ArrayList<ArrayList<Integer>> map;
    static boolean[] visited;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        map = new ArrayList<>();
        distance = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            map.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map.get(a).add(b);
            map.get(b).add(a);
        }

        sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            visited = new boolean[n + 1];
            len = 0;
            dfs(i, i);
        }
        System.out.println(sb.toString());
    }

    static void dfs(int start, int idx) {
        // 싸이클 있다
        if (visited[idx]) {
            if (start == idx) {

            }
        }
        for (int station : map.get(idx)) {
            if (!visited[station]) {
                visited[station] = true;
                dfs(start, station);
            }
        }
    }
}
