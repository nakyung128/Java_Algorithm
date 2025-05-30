import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2056_작업 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] chasu = new int[n + 1];
        int[] times = new int[n + 1];
        int[] dp = new int[n + 1];
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            arr.add(new ArrayList<>());
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            times[i] = Integer.parseInt(st.nextToken());
            int cha = Integer.parseInt(st.nextToken());
            chasu[i] = cha;
            if (cha == 0) {
                q.offer(i);
                dp[i] = times[i];
            }
            for (int c = 0; c < cha; c++) {
                int num = Integer.parseInt(st.nextToken());
                arr.get(num).add(i);
            }
        }

        while (!q.isEmpty()) {
            int now = q.poll();
            for (int next : arr.get(now)) {
                dp[next] = Math.max(dp[next], dp[now] + times[next]);
                if (--chasu[next] == 0) {
                    q.offer(next);
                }
            }
        }

        int answer = 0;
        for (int i = 1; i < n + 1; i++) {
            answer = Math.max(answer, dp[i]);
        }
        System.out.println(answer);
    }
}
