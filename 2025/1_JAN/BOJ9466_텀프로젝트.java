import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9466_텀프로젝트 {
    static int n;
    static int[] arr;
    static boolean[] visited, finished;
    static int cycle;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            arr = new int[n + 1];
            finished = new boolean[n + 1];
            visited = new boolean[n + 1];
            cycle = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                if (!finished[i]) {
                    dfs(i);
                }
            }

            System.out.println(n - cycle);
        }
    }

    static void dfs(int idx) {
        // 이미 구성원 -> return
        if (finished[idx]) {
            return;
        }

        // 이미 방문했었으면 사이클 구성원이고, 검사 끝난 것.
        if (visited[idx]) {
            cycle++;
            finished[idx] = true;
        }

        visited[idx] = true;
        dfs(arr[idx]);
        finished[idx] = true; // 검사 끝났으니까 true
        visited[idx] = false; // 방문 다시 해제 (사이클 도니까)
    }
}
