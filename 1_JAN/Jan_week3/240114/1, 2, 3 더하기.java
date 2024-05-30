import java.util.Scanner;

class Hap {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();

        int dp[] = new int[10];

        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 4;

        for (int i = 3; i < 10; i++) {
            dp[i] = dp[i-3] + dp[i-2] + dp[i-1];
        }

        for (int i = 0; i < t; i++) {
            int num = s.nextInt();
            System.out.println(dp[num-1]);
        }

        s.close();
    }
}