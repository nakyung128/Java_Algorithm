import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1260_DFS와BFS {
  static int n, m, v;
  static ArrayList<ArrayList<Integer>> nodes;
  static boolean[] visited;
  static ArrayList<Integer> dfs_answer;
  static ArrayList<Integer> bfs_answer;
  static Queue<Integer> q;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    v = Integer.parseInt(st.nextToken());

    nodes = new ArrayList<>();

    for (int i = 0; i < n + 1; i++) {
      nodes.add(new ArrayList<>());
    }

    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      nodes.get(a).add(b);
      nodes.get(b).add(a);
    }

    visited = new boolean[n + 1];

    dfs_answer = new ArrayList<>();
    bfs_answer = new ArrayList<>();

    for (ArrayList<Integer> list : nodes) {
      list.sort(null);
    }

    // dfs 돌리기
    dfs_answer.add(v);
    visited[v] = true;
    dfs(v);

    // bfs 돌리기
    q = new LinkedList<>();
    visited = new boolean[n + 1];
    bfs_answer.add(v);
    visited[v] = true;
    q.offer(v);
    bfs();

    // 정답 출력
    for (int d : dfs_answer) {
      System.out.print(d + " ");
    }
    System.out.println();
    for (int b : bfs_answer) {
      System.out.print(b + " ");
    }
  }

  static void dfs(int start) {
    for (int n : nodes.get(start)) {
      if (!visited[n]) {
        visited[n] = true;
        dfs_answer.add(n);
        dfs(n);
      }
    }
  }

  static void bfs() {
    while (!q.isEmpty()) {
      int me = q.poll();

      for (int n : nodes.get(me)) {
        if (!visited[n]) {
          visited[n] = true;
          bfs_answer.add(n);
          q.offer(n);
        }
      }
    }
  }
}