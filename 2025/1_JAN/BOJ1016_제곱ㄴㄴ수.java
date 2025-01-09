import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1016_제곱ㄴㄴ수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long min = Long.parseLong(st.nextToken());
        long max = Long.parseLong(st.nextToken());
        boolean[] yes = new boolean[(int) (max - min + 1)];

        for (long i = 2; i * i < max + 1; i++) {
            long start = (min / (i * i)) * i * i;
            if (start < min) {
                start += i * i;
            }
            for (long j = start; j < max + 1; j += i * i) {
                yes[(int) (j - min)] = true;
            }
        }

        int answer = 0;
        for (boolean y : yes) {
            if (!y) {
                answer++;
            }
        }
        System.out.println(answer);
    }
}
