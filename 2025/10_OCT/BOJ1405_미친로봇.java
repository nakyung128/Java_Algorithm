import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1405_미친로봇 {
    static int N;
    static double e, w, s, n;
    static double[] dirProb;
    static boolean[][] visited;
    static double answer;

    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        dirProb = new double[4];
        e = Double.parseDouble(st.nextToken()) / 100.0;
        w = Double.parseDouble(st.nextToken()) / 100.0;
        s = Double.parseDouble(st.nextToken()) / 100.0;
        n = Double.parseDouble(st.nextToken()) / 100.0;

        dirProb[0] = e;
        dirProb[1] = w;
        dirProb[2] = s;
        dirProb[3] = n;

        visited = new boolean[2 * N + 1][2 * N + 1];
        visited[N][N] = true;
        dfs(N, N, 0, 1.0);
        System.out.println(answer);
    }

    static void dfs(int x, int y, int depth, double prob) {
        if (depth == N) {
            answer += prob;
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < 2 * N + 1 && ny >= 0 && ny < 2 * N + 1) {
                if (visited[nx][ny])
                    continue;
                visited[nx][ny] = true;
                dfs(nx, ny, depth + 1, prob * dirProb[i]);
                visited[nx][ny] = false;
            }
        }
    }
}
