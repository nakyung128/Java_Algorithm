import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2565_전깃줄 {
    static class Line implements Comparable<Line> {
        int first, second;

        public Line(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(Line o) {
            return this.first - o.first;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        ArrayList<Line> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            arr.add(new Line(first, second));
        }
        arr.sort(null);

        int[] dp = new int[n];
        int longest = 1;
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int max = 0;
            for (int j = i; j >= 0; j--) {
                if (arr.get(j).second < arr.get(i).second) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
            longest = Math.max(longest, dp[i]);
        }

        System.out.println(n - longest);
    }
}
