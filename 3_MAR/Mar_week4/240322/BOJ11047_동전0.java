import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11047_동전0 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());

    int[] coin = new int[n];

    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      coin[i] = Integer.parseInt(st.nextToken());
    }

    int count = 0;

    while (k != 0) {
      for (int i = n - 1; i >= 0; i--) {
        if (k >= coin[i]) {
          int div = k / coin[i];
          k -= coin[i] * div;
          count += div;
        }
      }
    }

    System.out.println(count);
  }
}
