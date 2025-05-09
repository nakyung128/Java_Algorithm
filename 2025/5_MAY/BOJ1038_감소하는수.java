import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ1038_감소하는수 {
    static ArrayList<Long> answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        if (n > 1022) {
            System.out.println(-1);
        } else {
            answer = new ArrayList<>();
            // 1자리부터 10자리까지
            for (int i = 0; i < 10; i++) {
                dfs(i, i);
            }

            answer.sort(null); // 오름차순 정렬
            System.out.println(answer.get(n));
        }
    }

    static void dfs(long num, int last) {
        answer.add(num);
        // 다음 자리에 올 수 있는 숫자는 last보다 작은 숫자
        for (int i = 0; i < last; i++) {
            dfs(num * 10 + i, i);
        }
    }
}
