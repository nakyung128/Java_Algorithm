import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ15654_N과M_5 {
  static int[] nums;
  static boolean[] visited;
  static ArrayList<Integer> perm;
  static int n, m;
  static StringBuilder sb;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    nums = new int[n];
    visited = new boolean[n];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      nums[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(nums); // 사전순으로 출력하기 위해 오름차순 정렬

    sb = new StringBuilder();
    perm = new ArrayList<>();
    select(0);

    System.out.println(sb);
  }

  static void select(int depth) {
    if (depth == m) {
      for (int p : perm) {
        sb.append(p + " ");
      }
      sb.append("\n");
      return;
    }

    for (int i = 0; i < n; i++) {
      if (!visited[i]) {
        visited[i] = true;
        perm.add(nums[i]);
        select(depth + 1);
        visited[i] = false;
        perm.remove(perm.size() - 1);
      }
    }
  }
}
