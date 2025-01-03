import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA1767_프로세서연결하기 {
  static class Node {
    int x;
    int y;

    Node(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  static int[][] map;
  static boolean[][] visited;
  static int[] dx = { -1, 1, 0, 0 };
  static int[] dy = { 0, 0, -1, 1 };
  static ArrayList<Node> cores;
  static int max_core, n, min_len;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int t = Integer.parseInt(st.nextToken());

    for (int test_case = 1; test_case <= t; test_case++) {
      st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());

      map = new int[n][n];
      visited = new boolean[n][n];
      cores = new ArrayList<>();

      for (int i = 0; i < n; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < n; j++) {
          map[i][j] = Integer.parseInt(st.nextToken());
          // 가장자리는 연결 안 해도 되니까 제외
          if (i != 0 && i != n - 1 && j != 0 && j != n - 1 && map[i][j] == 1) {
            cores.add(new Node(i, j));
          }
        }
      }

      max_core = 0;
      min_len = Integer.MAX_VALUE;

      connect(0, 0, 0);
      System.out.printf("#%d %d\n", test_case, min_len);
    }
  }

  static void connect(int idx, int core_cnt, int len_cnt) {

    // 다 연결했으면
    if (idx == cores.size()) {
      if (core_cnt > max_core) {
        max_core = core_cnt;
        min_len = len_cnt;
      } else if (core_cnt == max_core) {
        min_len = Math.min(min_len, len_cnt);
      }
      return;
    }

    Node now = cores.get(idx);

    for (int i = 0; i < 4; i++) {
      int cnt = 0;

      int nx = now.x;
      int ny = now.y;

      while (true) {
        nx += dx[i];
        ny += dy[i];

        // 가다가 다른 전선이나 코어 만나면 끝 -> 다른 방향으로 탐색하자
        if (map[nx][ny] == 1 || visited[nx][ny]) {
          cnt = 0;
          break;
        }

        // 아님 연결 (전선 길이 + 1)
        cnt++;

        // 끝에 도착했으면
        if (nx == 0 || nx == n - 1 || ny == 0 || ny == n - 1) {
          break;
        }
      }

      // visited 처리해 주기
      int xx = now.x;
      int yy = now.y;
      for (int j = 0; j < cnt; j++) {
        xx += dx[i];
        yy += dy[i];
        visited[xx][yy] = true;
      }

      if (cnt == 0) {
        // 연결 못했으면 그대로
        connect(idx + 1, core_cnt, len_cnt);
      } else {
        // 연결했으면 core_cnt + 1, len_cnt에 길이 더해 주기
        connect(idx + 1, core_cnt + 1, len_cnt + cnt);
      }

      // visited 해제
      xx = now.x;
      yy = now.y;
      for (int j = 0; j < cnt; j++) {
        xx += dx[i];
        yy += dy[i];
        visited[xx][yy] = false;
      }
    }
  }
}
