import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ13460_구슬탈출2 {
    static int n, m;
    static char[][] board;
    static LinkedList<Point> q;
    static int[] goal;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static class Point {
        int rx;
        int ry;
        int bx;
        int by;
        int cnt;

        Point(int rx, int ry, int bx, int by, int cnt) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n][m];
        goal = new int[2];
        q = new LinkedList<>();
        Point point = new Point(0, 0, 0, 0, 0);

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'O') {
                    goal[0] = i;
                    goal[1] = j;
                } else if (board[i][j] == 'R') {
                    point.rx = i;
                    point.ry = j;
                } else if (board[i][j] == 'B') {
                    point.bx = i;
                    point.by = j;
                }
            }
        }
        q.offer(point);
        System.out.println(out());
    }

    static int out() {
        while (!q.isEmpty()) {
            Point now = q.poll();

            if (now.cnt == 10) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int rx = now.rx;
                int ry = now.ry;
                int bx = now.bx;
                int by = now.by;

                boolean r_hole = false;
                boolean b_hole = false;

                while (true) {
                    int newX = rx + dx[i];
                    int newY = ry + dy[i];

                    if (board[newX][newY] == 'O') {
                        r_hole = true;
                        break;
                    } else if (board[newX][newY] == '#') {
                        break;
                    }

                    rx = newX;
                    ry = newY;
                }

                while (true) {
                    int newX = bx + dx[i];
                    int newY = by + dy[i];

                    if (board[newX][newY] == 'O') {
                        b_hole = true;
                        break;
                    } else if (board[newX][newY] == '#') {
                        break;
                    }

                    bx = newX;
                    by = newY;
                }

                // 파란 구슬이 구멍에 빠지면 안 됨, 빨간 구슬이 구멍에 빠졌으면 카운트 리턴
                if (b_hole) {
                    continue;
                } else if (r_hole) {
                    return now.cnt + 1;
                }

                // 둘 다 그대로면 그냥 넘어가기
                if (rx == now.rx && ry == now.ry && bx == now.bx && by == now.by) {
                    continue;
                }

                if (rx == bx && ry == by) {
                    if (i == 0) {
                        if (now.rx > now.bx) {
                            rx++;
                        } else {
                            bx++;
                        }
                    } else if (i == 1) {
                        if (now.rx > now.bx) {
                            bx--;
                        } else {
                            rx--;
                        }
                    } else if (i == 2) {
                        if (now.ry > now.by) {
                            ry++;
                        } else {
                            by++;
                        }
                    } else {
                        if (now.ry > now.by) {
                            by--;
                        } else {
                            ry--;
                        }
                    }
                }

                q.offer(new Point(rx, ry, bx, by, now.cnt + 1));
            }
        }

        return -1;
    }
}
