import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ22352_항체인식 {
    static int n, m;
    static boolean[][] visited;
    static int[][] before, after;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        before = new int[n][m];
        after = new int[n][m];
        visited = new boolean[n][m];

        // 백신 놓기 전
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                before[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 백신 놓은 후
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                after[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dfs 돌면서 확인
        int sameCnt = 0;
        int diffCnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j]) {
                    continue;
                }

                flag = true; // 플래그 초기화
                visited[i][j] = true; // 시작 지점 방문 처리

                dfs(i, j);

                if (!flag) {
                    // 그룹이 동일하지 않다면 바로 "NO" 반환.
                    System.out.println("NO");
                    return;
                }

                if (before[i][j] == after[i][j]) {
                    sameCnt++; // 이전, 이후 데이터 같은 경우
                } else {
                    diffCnt++; // 이전, 이후 데이터 달라진 경우
                }

            }
        }

        /**
         * "YES"인 경우
         * 1. 데이터 달라진 그룹이 딱 한 개인 경우. (해당 그룹의 한 조직에 백신 놓았다고 생각할 수 있음)
         * 2. 데이터 달라진 그룹은 0개, 동일한 그룹은 1개 이상. (해당 그룹들 중 하나의 조직에 백신 놓았다고 생각할 수 있음)
         * 
         * "NO"인 경우
         * 1. 데이터 그룹이 동일하지 않은 경우.
         * 2. 데이터가 달라진 그룹이 여러개인 경우. '하나의' 조직에 백신을 넣은 게 아니기 때문.
         */

        if (diffCnt == 1 || (diffCnt == 0 && sameCnt >= 1)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    static void dfs(int x, int y) {
        // 상하좌우 이동
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 그래프 범위 내이고
            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                // 데이터가 동일하며, 방문하지 않은 경우
                if (before[x][y] == before[nx][ny] && !visited[nx][ny]) {
                    visited[nx][ny] = true; // 방문 처리 (중복 방지)
                    // 이전엔 그룹에 포함되지만 이후에는 그룹이 아니게 됐다면 CPCU-1202 백신이 아니기애 false 리턴.
                    if (after[x][y] != after[nx][ny]) {
                        flag = false;
                        return;
                    }
                    dfs(nx, ny);
                }
            }
        }
    }
}
