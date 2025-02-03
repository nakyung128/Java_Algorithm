import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class BOJ1068_트리 {
    static ArrayList<ArrayList<Integer>> tree;
    static boolean[] visited;
    static int target, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        tree = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }

        visited = new boolean[n];
        int root = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int p = Integer.parseInt(st.nextToken());
            if (p != -1) {
                tree.get(i).add(p);
                tree.get(p).add(i);
            } else {
                root = i;
            }
        }

        target = Integer.parseInt(br.readLine());
        if (root == target) {
            System.out.println(0);
        } else {
            dfs(root);
            System.out.println(answer);
        }
    }

    static void dfs(int x) {
        visited[x] = true;
        int child = 0;
        for (int n : tree.get(x)) {
            if (!visited[n] && n != target) {
                child++;
                dfs(n);
            }
        }
        if (child == 0) {
            answer++;
        }
    }
}
