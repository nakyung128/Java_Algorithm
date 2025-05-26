import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ25603_짱해커이동식 {
    static int n, k;
    static int[] cost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        cost = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(cal());
    }

    static int cal() {
        boolean[] check = new boolean[n];
        int max_cost = 0;
        int s = 0;

        while (s <= n - k) {
            boolean flag = false;
            int min = Integer.MAX_VALUE;
            int min_idx = 0;
            for (int i = s; i < s + k; i++) {
                if (check[i]) {
                    flag = true;
                    break;
                } else {
                    if (cost[i] < min) {
                        min = cost[i];
                        min_idx = i;
                    }
                }
            }
            if (!flag) {
                check[min_idx] = true;
                max_cost = Math.max(max_cost, min);
            }
            s++;
        }

        return max_cost;
    }
}
