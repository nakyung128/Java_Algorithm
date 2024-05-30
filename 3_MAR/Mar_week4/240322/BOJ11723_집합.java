import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11723_집합 {
  static int[] numbers;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    StringBuilder sb = new StringBuilder();

    int m = Integer.parseInt(st.nextToken()); // 수행하는 연산의 수

    numbers = new int[20];

    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      String cal = st.nextToken();
      int num = 0;
      if (st.hasMoreTokens()) {
        num = Integer.parseInt(st.nextToken());
      }

      switch (cal) {
        case "add":
          add(num);
          break;
        case "remove":
          remove(num);
          break;
        case "check":
          sb.append(check(num) + "\n");
          break;
        case "toggle":
          toggle(num);
          break;
        case "all":
          all();
          break;
        case "empty":
          empty();
          break;
      }
    }
    System.out.println(sb);
  }

  static void add(int num) {
    if (numbers[num - 1] == 0) {
      numbers[num - 1] = 1;
    }
  }

  static void remove(int num) {
    if (numbers[num - 1] == 1) {
      numbers[num - 1] = 0;
    }
  }

  static int check(int num) {
    if (numbers[num - 1] == 1) {
      return 1;
    }
    return 0;
  }

  static void toggle(int num) {
    if (numbers[num - 1] == 1) {
      numbers[num - 1] = 0;
    } else {
      numbers[num - 1] = 1;
    }
  }

  static void all() {
    Arrays.fill(numbers, 1);
  }

  static void empty() {
    numbers = new int[20];
  }
}
