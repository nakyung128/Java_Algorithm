import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14503_로봇청소기 {
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static int n, m;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;

        while (true) {
            // 현재 칸이 청소되지 않은 경우 현재 칸을 청소
            if (!visited[r][c]) {
                visited[r][c] = true;
                answer++;
            }
            // 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
            if (check(r, c)) {
                // 반시계 방향으로 90도 회전
                while (true) {
                    // 바라보는 방향 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진
                    if (d == 0) {
                        d = 3;
                        if (map[r][c - 1] == 0 && !visited[r][c - 1]) {
                            c--;
                            break;
                        }
                    } else if (d == 3) {
                        d = 2;
                        if (map[r + 1][c] == 0 && !visited[r + 1][c]) {
                            r++;
                            break;
                        }
                    } else if (d == 2) {
                        d = 1;
                        if (map[r][c + 1] == 0 && !visited[r][c + 1]) {
                            c++;
                            break;
                        }
                    } else {
                        d = 0;
                        if (map[r - 1][c] == 0 && !visited[r - 1][c]) {
                            r--;
                            break;
                        }
                    }
                }
            } else { // 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
                // 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진
                // 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춤
                if (d == 0) {
                    if (r + 1 < n) {
                        if (map[r + 1][c] == 0) {
                            r++;
                        } else {
                            break;
                        }
                    }
                } else if (d == 1) {
                    if (c - 1 >= 0) {
                        if (map[r][c - 1] == 0) {
                            c--;
                        } else {
                            break;
                        }
                    }
                } else if (d == 2) {
                    if (r - 1 >= 0) {
                        if (map[r - 1][c] == 0) {
                            r--;
                        } else {
                            break;
                        }
                    }
                } else {
                    if (c + 1 < m) {
                        if (map[r][c + 1] == 0) {
                            c++;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        System.out.println(answer);
    }

    static boolean check(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                if (map[nx][ny] == 0 && !visited[nx][ny]) {
                    return true;
                }
            }
        }
        return false;
    }
}
