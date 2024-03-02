import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ3184_양 {
    static int r;
    static int c;
    static char[][] graph;
    static boolean[][] visited;
    static ArrayList<int[]> wolf_arr;
    static ArrayList<int[]> sheep_arr;
    static int sheep;
    static int wolf;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        graph = new char[r][c];
        visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                graph[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (!visited[i][j] && graph[i][j] != '#') {
                    sheep = 0;
                    wolf = 0;

                    visited[i][j] = true;

                    wolf_arr = new ArrayList<>();
                    sheep_arr = new ArrayList<>();

                    if (graph[i][j] == 'v') {
                        wolf_arr.add(new int[] { i, j });
                        wolf++;
                    } else if (graph[i][j] == 'o') {
                        sheep_arr.add(new int[] { i, j });
                        sheep++;
                    }

                    dfs(i, j); // 한 구역에서 양과 늑대의 개수 구하기

                    // 늑대가 더 많은 경우 양이 다 잡아 먹힘
                    if (wolf >= sheep) {
                        for (int[] s : sheep_arr) {
                            graph[s[0]][s[1]] = '.';
                        }
                    } else if (sheep > wolf) { // 양이 더 많은 경우 늑대 쫓아냄
                        for (int[] w : wolf_arr) {
                            graph[w[0]][w[1]] = '.';
                        }
                    }

                }
            }
        }
        int[] answer = count();
        System.out.printf("%d %d", answer[0], answer[1]);
    }

    // 한 구역 내의 늑대, 양의 수 구하기
    static void dfs(int x, int y) {
        int[] dx = new int[] { -1, 1, 0, 0 };
        int[] dy = new int[] { 0, 0, -1, 1 };

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 방문하지 않았고 울타리가 아니라면
            if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
                if (!visited[nx][ny] && graph[nx][ny] != '#') {
                    visited[nx][ny] = true;
                    if (graph[nx][ny] == 'v') {
                        wolf++; // 늑대 + 1
                        wolf_arr.add(new int[] { nx, ny });
                    } else if (graph[nx][ny] == 'o') {
                        sheep++; // 양 + 1
                        sheep_arr.add(new int[] { nx, ny });
                    }
                    dfs(nx, ny);
                }
            }
        }
    }

    static int[] count() {
        int sheep = 0;
        int wolf = 0;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (graph[i][j] == 'o')
                    sheep++;
                else if (graph[i][j] == 'v')
                    wolf++;
            }
        }

        return new int[] { sheep, wolf };
    }
}
