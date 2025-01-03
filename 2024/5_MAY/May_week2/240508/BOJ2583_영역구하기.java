import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2583_영역구하기 {
    static int m, n;
    static int[][] graph;
    static boolean[][] visited;
    static int cnt, size;
    static ArrayList<Integer> size_arr;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        int k = Integer.parseInt(st.nextToken());

        graph = new int[m][n];
        visited = new boolean[m][n];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int y = m - y2; y < m - y1; y++) {
                for (int x = x1; x < x2; x++) {
                    graph[y][x] = 1;
                }
            }
        }

        size_arr = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] == 0 && !visited[i][j]) {
                    size = 1;
                    dfs(i, j);
                    size_arr.add(size);
                    cnt++;
                }
            }
        }

        size_arr.sort(null);

        StringBuilder sb = new StringBuilder();
        sb.append(cnt + "\n");
        for (int size : size_arr) {
            sb.append(size + " ");
        }

        System.out.println(sb);
    }

    static void dfs(int x, int y) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                if (!visited[nx][ny] && graph[nx][ny] == 0) {
                    size++;
                    dfs(nx, ny);
                }
            }
        }
    }
}
