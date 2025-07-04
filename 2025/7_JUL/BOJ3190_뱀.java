import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ3190_뱀 {
    static class Dir {
        int sec;
        char direction;

        Dir(int sec, char direction) {
            this.sec = sec;
            this.direction = direction;
        }
    }

    static int n;
    static int[][] board;
    static boolean[][] visited;
    static ArrayList<Dir> change;
    static ArrayDeque<int[]> snake;
    static int dx = 0;
    static int dy = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        board = new int[n + 2][n + 2];
        visited = new boolean[n + 2][n + 2];
        snake = new ArrayDeque<>();

        // 벽 생성
        for (int i = 0; i < n + 2; i++) {
            for (int j = 0; j < n + 2; j++) {
                if (i == 0 || i == n + 1) {
                    board[i][j] = 3;
                } else if (j == 0 || j == n + 1) {
                    board[i][j] = 3;
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        change = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board[x][y] = 2; // 사과
        }

        st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            int sec = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);
            change.add(new Dir(sec, dir));
        }
        snake.add(new int[] { 1, 1 });
        start(1, 1, 0);
    }

    static void start(int x, int y, int sec) {
        visited[x][y] = true;

        // 원래 방향대로 먼저 계산하고 회전
        if (change.size() > 0 && change.get(0).sec == sec) {
            char dir = change.get(0).direction;
            if (dir == 'D') { // 오른쪽으로 90도 회전
                if (dx == 0 && dy == 1) {
                    dx = 1;
                    dy = 0;
                } else if (dx == 1 && dy == 0) {
                    dx = 0;
                    dy = -1;
                } else if (dx == 0 && dy == -1) {
                    dx = -1;
                    dy = 0;
                } else if (dx == -1 && dy == 0) {
                    dx = 0;
                    dy = 1;
                }
            } else { // 왼쪽으로 90도 회전
                if (dx == 0 && dy == 1) {
                    dx = -1;
                    dy = 0;
                } else if (dx == -1 && dy == 0) {
                    dx = 0;
                    dy = -1;
                } else if (dx == 0 && dy == -1) {
                    dx = 1;
                    dy = 0;
                } else if (dx == 1 && dy == 0) {
                    dx = 0;
                    dy = 1;
                }
            }
            change.remove(0);
        }
        int nx = x + dx;
        int ny = y + dy;
        if (nx >= 0 && nx < n + 2 && ny >= 0 && ny < n + 2) {
            if (!visited[nx][ny]) {
                // 벽에 닿으면 끝
                if (board[nx][ny] == 3) {
                    System.out.println(sec + 1);
                    return;
                } else if (board[nx][ny] == 2) { // 사과면 먹기
                    board[nx][ny] = 0;
                    snake.addLast(new int[] { nx, ny }); // 머리
                    start(nx, ny, sec + 1);
                } else if (board[nx][ny] == 0) { // 사과 없으면
                    // 꼬리가 위치한 칸을 비워준다. (대신 길이는 변하지 않는다)
                    int[] tail = snake.pollFirst();
                    visited[tail[0]][tail[1]] = false;
                    snake.addLast(new int[] { nx, ny }); // 머리
                    start(nx, ny, sec + 1);
                }
            } else {
                // 자기 자신에 닿으면 끝
                System.out.println(sec + 1);
                return;
            }
        }
    }
}
