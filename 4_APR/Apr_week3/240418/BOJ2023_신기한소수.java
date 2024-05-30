import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ2023_신기한소수 {
    static int n;
    static ArrayList<Integer> arr, answer;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        arr = new ArrayList<>();
        answer = new ArrayList<>();

        dfs(0, 0);
        answer.sort(null);

        for (int num : answer) {
            sb.append(num + "\n");
        }
        System.out.println(sb);
    }

    static void dfs(int depth, int num) {
        if (depth == n && isPrime(num)) {
            answer.add(num);
            return;
        }

        for (int i = 1; i < 10; i++) {
            if (depth == 0 && i == 1)
                continue;
            arr.add(i);
            if (isPrime(arrToInt())) {
                dfs(depth + 1, arrToInt());
            }
            arr.remove(arr.size() - 1);
        }
    }

    static boolean isPrime(int num) {
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    static int arrToInt() {
        int jari = arr.size() - 1;
        int number = 0;

        for (int num : arr) {
            number += Math.pow(10, jari) * num;
            jari--;
        }
        return number;
    }
}
