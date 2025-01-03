import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ4485_녹색옷입은애가젤다지 {
    static class Point implements Comparable<Point> {
        int x;
        int y;
        int cost;

        Point(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Point o) {
            return this.cost - o.cost;
        }
    }

    static int n;
    static int[][] map, cost;
    static boolean[][] visited;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static PriorityQueue<Point> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int problem = 1;

        while (true) {
            n = Integer.parseInt(br.readLine());
            if (n == 0) {
                break;
            }

            cost = new int[n][n];
            visited = new boolean[n][n];
            map = new int[n][n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            resetCost();
            pq = new PriorityQueue<>();

            find();

            // 도착점까지 드는 비용 출력
            System.out.printf("Problem %d: %d\n", problem, cost[n - 1][n - 1]);
            problem++;
        }
    }

    // cost 배열 초기화
    static void resetCost() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cost[i][j] = Integer.MAX_VALUE;
            }
        }
        cost[0][0] = map[0][0];
    }

    // 다익스트라 함수
    static void find() {
        pq.offer(new Point(0, 0, 0));

        while (!pq.isEmpty()) {
            Point now = pq.poll();
            visited[now.x][now.y] = true;

            // 상하좌우 탐색
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny]) {
                    if (cost[nx][ny] > cost[now.x][now.y] + map[nx][ny]) {
                        cost[nx][ny] = cost[now.x][now.y] + map[nx][ny];
                        pq.offer(new Point(nx, ny, cost[nx][ny]));
                    }
                }
            }
        }
    }
}
