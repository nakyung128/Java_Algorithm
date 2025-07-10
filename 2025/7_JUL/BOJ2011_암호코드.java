import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2011_암호코드 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String code = br.readLine();
        int MOD = 1000000;

        if (code.charAt(0) == '0') {
            System.out.println(0);
            System.exit(0);
        } else {
            int[] dp = new int[code.length() + 1];
            dp[0] = 1; // 아무것도 해석 안 했을 때 한 가지
            dp[1] = 1;

            for (int i = 2; i < code.length() + 1; i++) {
                char now = code.charAt(i - 1);
                char prev = code.charAt(i - 2);

                // 현재 글자가 1 ~ 9인 경우 혼자도 해석 가능함 (0 아니면)
                if (now != '0') {
                    dp[i] += dp[i - 1];
                    dp[i] %= MOD;
                }

                // 앞 글자랑 현재 글자 합쳐서 10 ~ 26인 경우 두 글자 해석 가능
                int two = (prev - '0') * 10 + (now - '0');
                if (two >= 10 && two <= 26) {
                    dp[i] += dp[i - 2];
                    dp[i] %= MOD;
                }
            }
            System.out.println(dp[code.length()]);
        }
    }
}
