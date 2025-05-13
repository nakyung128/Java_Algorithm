import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17141_연구소2 {
    static int n, m, answer;
    static int[][] map;
    static boolean[][] visited;
    static ArrayList<int[]> virus;
    static ArrayList<int[]> combArr;
    static Queue<Node> q;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static class Node {
        int x, y, time;

        Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        virus = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    virus.add(new int[] { i, j });
                }
            }
        }
        answer = Integer.MAX_VALUE;
        combArr = new ArrayList<>();
        comb(0, 0); // 바이러스 놓기

        // 다 퍼지는 게 불가능한 경우는 -1 반환
        if (answer != Integer.MAX_VALUE) {
            System.out.println(answer);
        } else {
            System.out.println(-1);
        }
    }

    // 바이러스 놓을 수 있는 위치 조합 구하기
    static void comb(int idx, int depth) {
        if (depth == m) {
            // 다 퍼지는 데에 얼마나 걸리는지 계산하기
            answer = Math.min(answer, time());
            return;
        }
        for (int i = idx; i < virus.size(); i++) {
            combArr.add(virus.get(i));
            comb(i + 1, depth + 1);
            combArr.remove(combArr.size() - 1);
        }
    }

    static int time() {
        int time = 0;
        visited = new boolean[n][n];
        q = new LinkedList<>();
        for (int[] v : combArr) {
            q.offer(new Node(v[0], v[1], 0));
            visited[v[0]][v[1]] = true;
        }

        while (!q.isEmpty()) {
            Node now = q.poll();
            time = now.time;

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (!visited[nx][ny] && map[nx][ny] != 1) {
                        visited[nx][ny] = true;
                        q.offer(new Node(nx, ny, now.time + 1));
                    }
                }
            }
        }

        // 바이러스 다 퍼졌으면 시간을, 아니라면 MAX_VALUE 반환
        if (isValid()) {
            return time;
        } else {
            return Integer.MAX_VALUE;
        }
    }

    // 바이러스가 다 퍼졌는지 확인
    static boolean isValid() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 1 && !visited[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
