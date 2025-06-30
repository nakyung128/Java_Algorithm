import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ19542_전단지돌리기 {
    static int n, s, d;
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 노드 개수
        s = Integer.parseInt(st.nextToken()); // 케니소프트 위치
        d = Integer.parseInt(st.nextToken()); // 힘
        graph = new ArrayList<>();
        visited = new boolean[n + 1];

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        dfs(s, 0);
        System.out.println(answer * 2);
    }

    static int dfs(int idx, int depth) {
        visited[idx] = true;
        int maxDist = 0;

        for (int next : graph.get(idx)) {
            if (!visited[next]) {
                int childDist = dfs(next, depth + 1);
                if (childDist > d) {
                    answer++; // 전단지 뿌리러 가야 하는 곳
                }
                maxDist = Math.max(maxDist, childDist);
            }
        }
        return maxDist + 1; // 현재 노드까지 포함한 거리
    }
}
