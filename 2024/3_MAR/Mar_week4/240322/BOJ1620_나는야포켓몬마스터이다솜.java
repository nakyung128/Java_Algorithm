import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ1620_나는야포켓몬마스터이다솜 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken()); // 도감에 수록돼 있는 포켓몬의 개수
    int m = Integer.parseInt(st.nextToken()); // 맞혀야 하는 문제의 개수

    HashMap<Integer, String> poketmon = new HashMap<>();
    HashMap<String, Integer> poketmon2 = new HashMap<>();

    for (int i = 0; i < n; i++) {
      String name = br.readLine();
      poketmon.put(i + 1, name);
      poketmon2.put(name, i + 1);
    }

    for (int i = 0; i < m; i++) {
      String quiz = br.readLine();
      if (Character.isDigit(quiz.charAt(0))) {
        System.out.println(poketmon.get(Integer.parseInt(quiz)));
      } else {
        System.out.println(poketmon2.get(quiz));
      }
    }
  }
}
