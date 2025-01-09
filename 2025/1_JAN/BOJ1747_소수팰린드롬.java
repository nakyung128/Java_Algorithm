import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1747_소수팰린드롬 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] prime = new int[1100000];

        prime[0] = prime[1] = -1;

        for (int i = 2; i < 1100000; i++) {
            prime[i] = i;
        }

        for (int i = 2; i * i < 1100000; i++) {
            if (prime[i] != -1) {
                for (int j = i * i; j < 1100000; j += i) {
                    prime[j] = -1;
                }
            }
        }

        for (int i = n; i < 1100000; i++) {
            if (isPelindrome(prime[i])) {
                System.out.println(i);
                break;
            }
        }
    }

    static boolean isPelindrome(int num) {
        char[] arr = String.valueOf(num).toCharArray();

        int s = 0;
        int e = arr.length - 1;

        while (s < e) {
            if (arr[s] == arr[e]) {
                s++;
                e--;
            } else {
                return false;
            }
        }
        return true;
    }
}
