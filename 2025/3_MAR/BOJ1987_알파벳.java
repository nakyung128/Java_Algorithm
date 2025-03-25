import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1987_알파벳 {
    static int r, c;
    static boolean[] visited;
    static char[][] board;
    static int answer;
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { -1, 1, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        board = new char[r][c];
        visited = new boolean[26];

        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        visited[board[0][0] - 'A'] = true;
        dfs(0, 0, 1);
        System.out.println(answer);
    }

    static void dfs(int x, int y, int hab) {
        answer = Math.max(answer, hab);
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
                if (!visited[board[nx][ny] - 'A']) {
                    visited[board[nx][ny] - 'A'] = true;
                    dfs(nx, ny, hab + 1);
                    visited[board[nx][ny] - 'A'] = false;
                }
            }
        }
    }
}
