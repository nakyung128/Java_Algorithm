import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2573_빙산 {
    static int n;
    static int m;
    static int[][] bingsan;
    static boolean[][] visited;
    static int answer;
    static ArrayList<int[]> melt_bings;
    static ArrayList<Integer> melt_size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        bingsan = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                bingsan[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 덩어리가 두 개 이상 되기 전까지 돌기
        while (isEnd() < 2) {
            // 다 녹았는데 덩어리가 두 개 이상이 아니라면 0 출력
            if (isAllMelt()) {
                answer = 0;
                break;
            }

            // 0이 아닌 곳 녹이기
            melt_bings = new ArrayList<>();
            melt_size = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (bingsan[i][j] != 0) {
                        // 0이 아닌 곳 녹는 빙산 배열에 추가
                        melt_bings.add(new int[] { i, j });
                        melt(i, j); // 녹이기
                    }
                }
            }

            // 녹는 빙산 반복문 돌면서 녹는 크기만큼 빼주기
            // 한 번에 여러 군데가 녹는 것이기 때문에 이러한 방법을 사용함
            for (int i = 0; i < melt_bings.size(); i++) {
                int x = melt_bings.get(i)[0];
                int y = melt_bings.get(i)[1];
                int size = melt_size.get(i);
                if (bingsan[x][y] - size <= 0) {
                    bingsan[x][y] = 0;
                } else {
                    bingsan[x][y] -= size;
                }
            }
            answer++; // + 1년
            visited = new boolean[n][m]; // visited 배열 초기화
        }
        System.out.println(answer);
    }

    // 상하좌우에 있는 0 개수만큼 녹는다
    static void melt(int x, int y) {
        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };

        int cnt = 0; // 주변에 있는 0의 개수

        // 상하좌우 탐색
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                // 주변이 0이면 녹는 크기 + 1
                if (bingsan[nx][ny] == 0) {
                    cnt++;
                }
            }
        }
        melt_size.add(cnt); // 녹는 크기 (빼는 크기) 배열에 넣기
    }

    // 덩어리 세기 위한 dfs
    // 이 함수 한 번 당 덩어리 하나
    static void count(int x, int y) {
        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                // 방문하지 않았고 빙산의 높이가 0이 아니라면
                if (!visited[nx][ny] && bingsan[nx][ny] != 0) {
                    visited[nx][ny] = true; // 방문
                    count(nx, ny); // 재귀
                }
            }
        }
    }

    // 덩어리 세는 함수
    static int isEnd() {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 0이 아니고 방문하지 않았다면
                if (bingsan[i][j] != 0 && !visited[i][j]) {
                    visited[i][j] = true; // 방문
                    count(i, j); // 이 부분부터 dfs 돌기
                    cnt++; // 덩어리 + 1
                }
            }
        }
        return cnt;
    }

    // 다 녹았는지 판별하는 함수
    static boolean isAllMelt() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (bingsan[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
