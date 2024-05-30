import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2661_좋은수열 {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        makeNum("");
    }

    static void makeNum(String str) {
        if (str.length() == n) {
            System.out.println(str);
            System.exit(0);
        }

        for (int i = 1; i <= 3; i++) {
            if (isGood(str + i)) {
                makeNum(str + i);
            }
        }
    }

    static boolean isGood(String str) {
        // 좋은 수열인지 확인하기
        for (int i = 1; i < str.length() / 2 + 1; i++) {
            for (int start = 0; start < str.length() - 2 * i + 1; start++) {
                String first = "";
                String second = "";

                for (int idx = start; idx < start + i; idx++) {
                    first += str.charAt(idx);
                }

                for (int idx = start + i; idx < start + i + i; idx++) {
                    second += str.charAt(idx);
                }

                if (first.equals(second)) {
                    return false;
                }
            }
        }
        return true;
    }
}