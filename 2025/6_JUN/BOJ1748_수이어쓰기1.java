import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1748_수이어쓰기1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String num = br.readLine();
        int size = num.length();

        int answer = 0;
        for (int i = size - 1; i > 0; i--) {
            answer += 9 * i * Math.pow(10, i - 1);
        }
        answer += size * (Integer.parseInt(num) - Math.pow(10, size - 1) + 1);
        System.out.println(answer);
    }
}
