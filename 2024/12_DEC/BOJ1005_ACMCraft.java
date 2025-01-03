import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1005_ACMCraft {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        for (int tc = 0; tc < t; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int[] time = new int[n + 1]; // 짓는 데 걸리는 시간
            int[] links = new int[n + 1]; // 진입 차수
            int[] result = new int[n + 1]; // 해당 건물이 완료되는 데 걸리는 시간

            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
            Queue<Integer> q = new LinkedList<>();

            for (int i = 0; i < n + 1; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 1; i < n + 1; i++) {
                time[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                graph.get(a).add(b);
                links[b]++;
            }

            int goal = Integer.parseInt(br.readLine());

            for (int i = 1; i < n + 1; i++) {
                if (links[i] == 0) {
                    q.add(i);
                    result[i] = time[i];
                }
            }

            while (!q.isEmpty()) {
                int now = q.poll();
                for (int next : graph.get(now)) {
                    result[next] = Math.max(result[next], result[now] + time[next]);
                    if (--links[next] == 0) {
                        q.add(next);
                    }
                }
            }

            System.out.println(result[goal]);
        }
    }
}
