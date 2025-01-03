import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA4008 {
  static ArrayList<Integer> op;
  static ArrayList<Integer> nums;
  static int max_result;
  static int min_result;
  static int n;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int T = Integer.parseInt(st.nextToken());

    for (int test_case = 1; test_case <= T; test_case++) {
      st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());

      op = new ArrayList<>();

      st = new StringTokenizer(br.readLine());
      int plus = Integer.parseInt(st.nextToken());
      op.add(plus);
      int minus = Integer.parseInt(st.nextToken());
      op.add(minus);
      int mul = Integer.parseInt(st.nextToken());
      op.add(mul);
      int div = Integer.parseInt(st.nextToken());
      op.add(div);

      st = new StringTokenizer(br.readLine());
      nums = new ArrayList<>();

      // 수식에 쓰이는 숫자
      for (int i = 0; i < n; i++) {
        nums.add(Integer.parseInt(st.nextToken()));
      }

      max_result = Integer.MIN_VALUE;
      min_result = Integer.MAX_VALUE;

      back(nums.get(0), 0);

      System.out.printf("#%d %d\n", test_case, max_result - min_result);
    }
  }

  static void back(int answer, int depth) {
    // n-1번 연산했으면
    if (depth == n - 1) {
      max_result = Math.max(max_result, answer);
      min_result = Math.min(min_result, answer);
      return;
    }

    for (int i = 0; i < op.size(); i++) {
      if (op.get(i) > 0) {
        int tempResult = 0;
        // 계산
        switch (i) {
          case 0:
            tempResult = answer + nums.get(depth + 1);
            break;
          case 1:
            tempResult = answer - nums.get(depth + 1);
            break;
          case 2:
            tempResult = answer * nums.get(depth + 1);
            break;
          case 3:
            tempResult = answer / nums.get(depth + 1);
            break;
        }
        op.set(i, op.get(i) - 1);
        back(tempResult, depth + 1);
        op.set(i, op.get(i) + 1);
      }
    }
  }
}
