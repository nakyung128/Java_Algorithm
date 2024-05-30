import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2660_회장뽑기 {

    static class Node {
        int idx;
        int lev;

        Node(int idx, int lev) {
            this.idx = idx;
            this.lev = lev;
        }
    }

    static int n;
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;
    static Queue<Node> q;
    static int min_cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 회원의 수
        graph = new ArrayList<>();
        visited = new boolean[n + 1];

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == -1 && b == -1)
                break;

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        min_cnt = Integer.MAX_VALUE;

        ArrayList<Integer> count_arr = new ArrayList<>();

        for (int i = 1; i < n + 1; i++) {
            q = new LinkedList<>();
            visited = new boolean[n + 1];
            visited[i] = true;
            q.offer(new Node(i, 0));

            int cnt = count();
            count_arr.add(cnt);
            min_cnt = Math.min(min_cnt, cnt);
        }

        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i < count_arr.size(); i++) {
            if (count_arr.get(i) == min_cnt) {
                answer.add(i + 1);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(min_cnt + " " + answer.size() + "\n");
        for (int a : answer) {
            sb.append(a + " ");
        }
        System.out.println(sb);
    }

    static int count() {
        int cnt = 0;

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int p : graph.get(now.idx)) {
                if (!visited[p]) {
                    visited[p] = true;
                    q.offer(new Node(p, now.lev + 1));
                }
            }
            cnt = now.lev;
        }
        return cnt;
    }
}
