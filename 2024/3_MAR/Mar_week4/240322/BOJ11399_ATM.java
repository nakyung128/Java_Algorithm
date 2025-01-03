import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11399_ATM {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());

    int[] ppl = new int[n];
    int[] hab = new int[n];
    st = new StringTokenizer(br.readLine());

    for (int i = 0; i < n; i++) {
      ppl[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(ppl);

    hab[0] = ppl[0];
    for (int i = 1; i < n; i++) {
      hab[i] = ppl[i] + hab[i - 1];
    }

    int answer = 0;
    for (int h : hab) {
      answer += h;
    }

    System.out.println(answer);
  }
}
