import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class BOJ14267_회사문화1 {
    static int[] answer;
    static ArrayList<ArrayList<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        answer = new int[n + 1];
        graph = new ArrayList<>();

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int boss = Integer.parseInt(st.nextToken());
            if (boss != -1) {
                graph.get(boss).add(i + 1);
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            answer[idx] += w;
        }

        dfs(1);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n + 1; i++) {
            sb.append(answer[i] + " ");
        }
        System.out.println(sb.toString());
    }

    static void dfs(int idx) {
        for (int next : graph.get(idx)) {
            answer[next] += answer[idx];
            dfs(next);
        }
    }
}
