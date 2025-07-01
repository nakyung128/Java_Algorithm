import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17281_야구 {
    static int[] order;
    static boolean[] visited;
    static int[][] inning;
    static int n, max_score, next;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        inning = new int[n][9];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                inning[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        max_score = 0;
        order = new int[9];
        visited = new boolean[9];
        perm(0);
        System.out.println(max_score);
    }

    static void perm(int depth) {
        if (depth == 9) {
            // 점수 계산
            next = 0;
            max_score = Math.max(max_score, score());
            return;
        }
        if (depth == 3) {
            order[depth] = 0;
            perm(depth + 1);
        } else {
            for (int i = 1; i < 9; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    order[depth] = i;
                    perm(depth + 1);
                    visited[i] = false;
                }
            }
        }
    }

    static int score() {
        int score = 0;
        for (int i = 0; i < n; i++) {
            int outCnt = 0;
            boolean[] base = new boolean[3];

            while (outCnt < 3) {
                int player = order[next];
                int sc = inning[i][player];

                if (sc == 0) {
                    outCnt++;
                } else if (sc == 1) {
                    if (base[2]) {
                        score++;
                    }
                    base[2] = base[1];
                    base[1] = base[0];
                    base[0] = true;
                } else if (sc == 2) {
                    if (base[2]) {
                        score++;
                    }
                    if (base[1]) {
                        score++;
                    }
                    base[2] = base[0];
                    base[1] = true;
                    base[0] = false;
                } else if (sc == 3) {
                    for (int j = 0; j < 3; j++) {
                        if (base[j]) {
                            score++;
                        }
                    }
                    base[0] = false;
                    base[1] = false;
                    base[2] = true;
                } else {
                    for (int j = 0; j < 3; j++) {
                        if (base[j]) {
                            score++;
                        }
                    }
                    score++;
                    Arrays.fill(base, false);
                }
                next = (next + 1) % 9;
            }
        }
        return score;
    }
}
