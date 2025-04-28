import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ2668_숫자고르기 {
    static int n;
    static int[] arr;
    static boolean[] visited; // 방문했는지, i 출발해서 탐색 모두 끝냈는지
    static ArrayList<Integer> ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        ans = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            visited = new boolean[n + 1];
            if (dfs(i, i)) {
                ans.add(i);
            }
        }

        Collections.sort(ans);
        StringBuilder sb = new StringBuilder();
        sb.append(ans.size()).append("\n");
        for (int num : ans) {
            sb.append(num).append("\n");
        }
        System.out.print(sb.toString());
    }

    // dfs 돌면서 방문 안 한 곳 가고, 만약 다시 start로 돌아오면 싸이클
    // 싸이클 성립할 때 출발지 ans에 추가
    static boolean dfs(int start, int now) {
        if (visited[now]) {
            return now == start;
        }
        visited[now] = true;
        return dfs(start, arr[now]);
    }
}
