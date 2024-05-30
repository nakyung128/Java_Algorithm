import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ15652_N과M_4 {
  static ArrayList<Integer> perm;
  static boolean[] visited;
  static int[] nums;
  static int m;
  static StringBuilder sb;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    nums = new int[n];
    visited = new boolean[n];

    for (int i = 1; i <= n; i++) {
      nums[i - 1] = i;
    }

    perm = new ArrayList<>();
    sb = new StringBuilder();
    select(0, 0);
    System.out.println(sb);
  }

  static void select(int start, int depth) {
    if (depth == m) {
      for (int p : perm) {
        sb.append(p + " ");
      }
      sb.append("\n");
      return;
    }

    for (int i = start; i < nums.length; i++) {
      perm.add(nums[i]);
      select(i, depth + 1);
      perm.remove(perm.size() - 1);

    }
  }
}
