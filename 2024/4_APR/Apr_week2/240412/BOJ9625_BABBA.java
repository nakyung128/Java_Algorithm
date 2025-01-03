import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9625_BABBA {
    static class Node {
        int a;
        int b;

        Node(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());

        Node[] dp = new Node[k + 1];
        dp[0] = new Node(1, 0);
        dp[1] = new Node(0, 1);

        for (int i = 2; i < k + 1; i++) {
            dp[i] = new Node(dp[i - 1].b, dp[i - 1].a + dp[i - 1].b);
        }

        System.out.println(dp[k].a + " " + dp[k].b);
    }
}
