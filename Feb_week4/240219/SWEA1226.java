import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1226 {
    static int[][] miro;
    static boolean[][] visited;
    static Queue<int[]> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            int test_case = Integer.parseInt(st.nextToken());

            // 미로 배열 생성
            miro = new int[16][16];

            // 방문 배열 생성
            visited = new boolean[16][16];

            queue = new LinkedList<>();

            // 미로 값 넣어주기
            for (int j = 0; j < 16; j++) {
                String line = br.readLine();
                for (int k = 0; k < 16; k++) {
                    miro[j][k] = line.charAt(k) - '0';
                }
            }

            queue.offer(new int[] { 1, 1 }); // 시작점 큐에 넣어주기
            visited[1][1] = true; // 방문 처리

            if (bfs())
                System.out.printf("#%d %d\n", test_case, 1); // 가능
            else
                System.out.printf("#%d %d\n", test_case, 0); // 불가능
        }
    }

    static boolean bfs() {
        // 상하좌우
        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };

        while (!queue.isEmpty()) {
            int[] now = (int[]) queue.poll(); // 현재 위치
            int x = now[0];
            int y = now[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < 16 && ny >= 0 && ny < 16) {
                    if (miro[nx][ny] == 0 && visited[nx][ny] == false) {
                        queue.offer(new int[] { nx, ny });
                        visited[nx][ny] = true;
                    } else if (miro[nx][ny] == 3)
                        return true;
                }
            }
        }
        return false;
    }
}