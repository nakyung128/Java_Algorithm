import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2636 {
    static Queue<int[]> queue;
    static int r;
    static int c;
    static int[][] cheese;
    static ArrayList<Integer> count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        cheese = new int[r][c];

        queue = new LinkedList<>();

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                if (i == 0 || i == r - 1 || j == 0 || j == c - 1) {
                    st.nextToken();
                    cheese[i][j] = -1;
                    queue.offer(new int[] { i, j });
                } else {
                    cheese[i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }

        int cnt = 0;
        count = new ArrayList<>();

        // 한 시간만에 녹을 수도 있으므로 처음 치즈의 개수도 넣어 주기 
        countCheese();

        while (true) {
            // 치즈가 모두 녹았으면 출력 후 break
            if (allZero()) {
                System.out.println(cnt);
                System.out.println(count.get(count.size() - 2));
                break;
            } 
            side(); // 가장자리 -1로 바꿔 주기
            melt(); // 치즈 녹이기 
            cnt++; // 한 시간 추가 
        }
    }

    // 가장자리 -1로 바꿔 주는 bfs 
    static void side() {
        queue = new LinkedList<>();

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (cheese[i][j] == -1) {
                    queue.offer(new int[] { i, j });
                }
            }
        }

        int[] dx = new int[] { -1, 1, 0, 0 };
        int[] dy = new int[] { 0, 0, -1, 1 };

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int myX = now[0];
            int myY = now[1];

            for (int i = 0; i < 4; i++) {
                int nx = myX + dx[i];
                int ny = myY + dy[i];

                if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
                    if (cheese[nx][ny] == 0) {
                        cheese[nx][ny] = -1;
                        queue.offer(new int[] { nx, ny });
                    }                    
                }
            }
        }
    }

    // 치즈 녹이기 
    static void melt() {
        int[] dx = new int[] { -1, 1, 0, 0 };
        int[] dy = new int[] { 0, 0, -1, 1 };

        for (int i = 1; i < r - 1; i++) {
            for (int j = 1; j < c - 1; j++) {
                if (cheese[i][j] == 1) {
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if (cheese[nx][ny] == -1) {
                            cheese[i][j] = 0;
                            break;
                        }
                    }
                }
            }
        }
        countCheese();
    }

    // 남아 있는 치즈 개수 세기
    static void countCheese() {
        int cnt = 0;
        for (int i = 1; i < r - 1; i++) {
            for (int j = 1; j < c - 1; j++) {
                if (cheese[i][j] == 1) {
                    cnt++;
                }
            }
        }
        count.add(cnt);
    }

    // 치즈가 모두 녹았는지 판단하는 함수 
    static boolean allZero() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (cheese[i][j] == 1) return false;
            }
        }
        return true;
    }
}
