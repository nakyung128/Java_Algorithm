import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11689_GCDNK1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        long answer = n;

        for (long i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                answer = answer - answer / i;
                while (n % i == 0) {
                    n /= i;
                }
            }
        }

        if (n > 1) { // 아직 소인수 구성이 남아 있으면
            answer = answer - answer / n;
        }

        System.out.println(answer);
    }
}
