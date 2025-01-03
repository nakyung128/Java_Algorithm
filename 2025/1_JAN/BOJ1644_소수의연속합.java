import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ1644_소수의연속합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        boolean[] isPrime = new boolean[N + 1];
        ArrayList<Integer> prime = new ArrayList<>();

        // 소수는 false
        isPrime[0] = isPrime[1] = true;

        // 1. 소수 리스트 만들기 (에라토스테네스의 체)
        for (int i = 2; i * i <= N; i++) {
            // 만약에 i가 소수라면
            if (!isPrime[i]) {
                for (int j = i * i; j <= N; j += i) {
                    isPrime[j] = true;
                }
            }
        }

        for (int i = 2; i <= N; i++) {
            if (!isPrime[i]) {
                prime.add(i);
            }
        }

        // 2. 연속합으로 정수 구할 수 있는지
        int start = 0;
        int end = 0;
        int sum = 0;
        int cnt = 0;

        while (start <= end && end < prime.size()) {
            if (sum < N) {
                sum += prime.get(end++);
            } else {
                if (sum == N)
                    cnt++;
                sum -= prime.get(start++);
            }
        }

        // 위 코드가 end가 끝에 도달하면 종료되기 때문에
        // prime 리스트의 마지막 숫자가 N과 같다면 나 자신 + 1 해 줌.
        if (prime.size() > 0 && prime.get(prime.size() - 1) == N)
            cnt++;

        System.out.println(cnt);
    }
}
