import java.io.*;
import java.util.StringTokenizer;

public class BOJ1929_소수구하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n + 1];
        boolean[] prime = new boolean[n + 1];

        prime[0] = prime[1] = true;

        for (int i = 2; i <= n; i++) {
            arr[i] = i;
        }

        for (int i = 2; i * i <= n; i++) {
            if (!prime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    prime[j] = true;
                }
            }
        }

        for (int i = m; i <= n; i++) {
            if (!prime[i]) {
                System.out.println(i);
            }
        }
    }
}
