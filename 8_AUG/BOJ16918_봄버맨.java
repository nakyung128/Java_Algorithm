import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16918_봄버맨 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        char[][] board = new char[r][c];
        int[][] time = new int[r][c];

        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'O') {
                    time[i][j] = 3; // 폭탄 터질 시간
                }
            }
        }

        int[] dx = new int[] { -1, 1, 0, 0 };
        int[] dy = new int[] { 0, 0, -1, 1 };

        for (int t = 1; t <= n; t++) {
            if (t % 2 == 0) {
                for (int i = 0; i < r; i++) {
                    for (int j = 0; j < c; j++) {
                        if (board[i][j] == '.') {
                            board[i][j] = 'O';
                            time[i][j] = t + 3;
                        }
                    }
                }
            } else if (t % 2 == 1) {
                // 폭탄 터뜨리기
                for (int i = 0; i < r; i++) {
                    for (int j = 0; j < c; j++) {
                        if (time[i][j] == t) {
                            board[i][j] = '.';
                            for (int k = 0; k < 4; k++) {
                                int nx = i + dx[k];
                                int ny = j + dy[k];

                                if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
                                    if (board[nx][ny] == 'O' && time[nx][ny] != t) {
                                        board[nx][ny] = '.';
                                        time[nx][ny] = 0;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < r; i++) {
            System.out.println(board[i]);
        }
    }
}
