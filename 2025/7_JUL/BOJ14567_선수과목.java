import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.LinkedList;

public class BOJ14567_선수과목 {
    static ArrayList<ArrayList<Integer>> graph;
    static int[] depth, answer;
    static Queue<Integer> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        depth = new int[n + 1];
        answer = new int[n + 1];
        q = new LinkedList<>();

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int prev = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());
            graph.get(prev).add(next);
            depth[next] += 1;
        }

        for (int i = 1; i < n + 1; i++) {
            if (depth[i] == 0) {
                q.offer(i);
                answer[i] = 1;
            }
        }

        bfs();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n + 1; i++) {
            sb.append(answer[i] + " ");
        }
        System.out.println(sb.toString());
    }

    static void bfs() {
        while (!q.isEmpty()) {
            int now = q.poll();
            for (int next : graph.get(now)) {
                if (--depth[next] == 0) {
                    q.offer(next);
                    answer[next] = answer[now] + 1;
                }
            }
        }
    }
}
