import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ2002_추월 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int answer = 0;
        int[] exit = new int[n];

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(br.readLine(), i);
        }

        for (int i = 0; i < n; i++) {
            exit[i] = map.get(br.readLine());
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (exit[i] > exit[j]) {
                    answer++;
                    break;
                }
            }
        }
        System.out.println(answer);
    }
}
