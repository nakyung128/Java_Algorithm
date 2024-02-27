import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2468 {
    static int n;
    static int[][] city;
    static int[][] temp_city;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        city = new int[n][n];
        temp_city = new int[n][n];

        int max_value = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
                max_value = Math.max(max_value, city[i][j]);
            }
        }

        result = 0;

        for (int rain = 0; rain < max_value; rain++) {
            reset(rain);
            int answer = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (temp_city[i][j] != -1) {
                        temp_city[i][j] = -1;
                        dfs(i, j);
                        answer++;
                    }
                }
            }
            result = Math.max(result, answer);
        }
        System.out.println(result);
    }

    static void reset(int rain) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (city[i][j] <= rain) {
                    temp_city[i][j] = -1;
                } else
                    temp_city[i][j] = city[i][j];
            }
        }
    }

    static void dfs(int x, int y) {
        int[] dx = new int[] { -1, 1, 0, 0 };
        int[] dy = new int[] { 0, 0, -1, 1 };

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                if (temp_city[nx][ny] != -1) {
                    temp_city[nx][ny] = -1;
                    dfs(nx, ny);
                }
            }
        }
    }
}
