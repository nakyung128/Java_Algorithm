import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ18809_가든 {
    static int n, m, g, r;
    static int[][] garden;
    static int[][] graph;
    static boolean[] com_visited;
    static int[][] time;
    static Queue<int[]> queue;
    static ArrayList<int[]> can;
    static ArrayList<int[]> greens;
    static ArrayList<int[]> reds;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        garden = new int[n][m];
        graph = new int[n][m];
        queue = new LinkedList<>();
        can = new ArrayList<>();
        time = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                garden[i][j] = Integer.parseInt(st.nextToken());

                // 배양액을 뿌릴 수 있는 땅이면 can 배열에 추가
                if (garden[i][j] == 2) {
                    can.add(new int[] { i, j });
                }
            }
        }

        greens = new ArrayList<>();
        reds = new ArrayList<>();
        answer = 0;
        com_visited = new boolean[can.size()];
        selectG(0, 0);
        System.out.println(answer);
    }

    // 씨앗 뿌릴 곳 고르기
    static void selectG(int start, int depth) {
        if (depth == g) {
            selectR(0, 0);
        }

        for (int i = start; i < can.size(); i++) {
            if (!com_visited[i]) {
                com_visited[i] = true;
                greens.add(can.get(i));
                selectG(i + 1, depth + 1);
                greens.remove(greens.size() - 1);
                com_visited[i] = false;
            }
        }
    }

    static void selectR(int start, int depth) {
        if (depth == r) {
            // reset -> graph 및 cnt 초기화
            reset();
            // bfs
            answer = Math.max(answer, bfs());
            return;
        }

        for (int i = start; i < can.size(); i++) {
            if (!com_visited[i]) {
                com_visited[i] = true;
                reds.add(can.get(i));
                selectR(i + 1, depth + 1);
                reds.remove(reds.size() - 1);
                com_visited[i] = false;
            }
        }
    }

    static int bfs() {

        int cnt = 0;

        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };

        // queue에 씨앗 다 넣어 주기
        for (int[] g : greens) {
            queue.offer(g);
            graph[g[0]][g[1]] = 3; // green
            time[g[0]][g[1]] = 0;
        }

        for (int[] r : reds) {
            queue.offer(r);
            graph[r[0]][r[1]] = 4; // red
            time[r[0]][r[1]] = 0;
        }

        // bfs 돌기요
        while (!queue.isEmpty()) {
            int[] me = queue.poll();

            if (graph[me[0]][me[1]] == 999)
                continue;

            // 3이면 초록색, 4면 빨간색
            int color = graph[me[0]][me[1]];

            // 4방향 탐색하기
            for (int i = 0; i < 4; i++) {
                int nx = me[0] + dx[i];
                int ny = me[1] + dy[i];

                // 범위 내에 있고
                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    // 동시에 방문하면 꽃이 핀다 (꽃 => 100)
                    // 여기는 queue에 안 넣음
                    if ((graph[nx][ny] == 3 || graph[nx][ny] == 4) && graph[nx][ny] != color
                            && time[nx][ny] == time[me[0]][me[1]] + 1) {
                        graph[nx][ny] = 999;
                        cnt++;
                    } else if (graph[nx][ny] == 0) {
                        // 아직 방문안 했으면 퍼짐
                        graph[nx][ny] = color; // 색으로 갱신
                        time[nx][ny] = time[me[0]][me[1]] + 1; // 시간 + 1 해 주기
                        queue.offer(new int[] { nx, ny });
                    }
                }
            }
        }

        return cnt;
    }

    static void reset() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (garden[i][j] == 0)
                    graph[i][j] = -1;
                else
                    graph[i][j] = 0;
            }
        }

        // time 배열 초기화
        for (int i = 0; i < n; i++) {
            Arrays.fill(time[i], -1);
        }

    }
}