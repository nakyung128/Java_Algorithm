import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ21608_상어초등학교 {
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static int n;
    static int[][] map;
    static int[] order;
    static ArrayList<ArrayList<Integer>> like;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        order = new int[n * n];
        like = new ArrayList<>();

        for (int i = 0; i < n * n + 1; i++) {
            like.add(new ArrayList<>());
        }

        for (int i = 0; i < n * n; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            order[i] = num;
            for (int j = 0; j < 4; j++) {
                like.get(num).add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int o : order) {
            check(o);
        }
        System.out.println(score());
    }

    static void check(int now) {
        int[] select = { n, n };
        int maxLike = 0;
        int blankCnt = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0) {
                    int likeCnt = 0;
                    int blank = 0;
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                            if (map[nx][ny] == 0) {
                                blank++;
                            } else {
                                for (int l : like.get(now)) {
                                    if (map[nx][ny] == l) {
                                        likeCnt++;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (maxLike < likeCnt) {
                        maxLike = likeCnt;
                        blankCnt = blank;
                        select[0] = i;
                        select[1] = j;
                    } else if (maxLike == likeCnt) {
                        if (blankCnt < blank) {
                            blankCnt = blank;
                            select[0] = i;
                            select[1] = j;
                        } else if (blankCnt == blank) {
                            if (select[0] > i) {
                                select[0] = i;
                                select[1] = j;
                            } else if (select[0] == i && select[1] > j) {
                                select[0] = i;
                                select[1] = j;
                            }
                        }
                    }
                }
            }
        }
        map[select[0]][select[1]] = now;
    }

    static int score() {
        int score = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int likeCnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                        for (int l : like.get(map[i][j])) {
                            if (map[nx][ny] == l) {
                                likeCnt++;
                                break;
                            }
                        }
                    }
                }
                if (likeCnt == 0) {
                    continue;
                } else if (likeCnt == 1) {
                    score += 1;
                } else if (likeCnt == 2) {
                    score += 10;
                } else if (likeCnt == 3) {
                    score += 100;
                } else {
                    score += 1000;
                }
            }
        }
        return score;
    }
}
