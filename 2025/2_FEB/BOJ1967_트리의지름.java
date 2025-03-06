import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1967_트리의지름 {
    static class Node {
        int idx, dis;

        Node(int idx, int dis) {
            this.idx = idx;
            this.dis = dis;
        }
    }

    static ArrayList<ArrayList<Node>> tree;
    static boolean[] visited;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        tree = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            tree.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());

            tree.get(parent).add(new Node(child, dis));
            tree.get(child).add(new Node(parent, dis));
        }

        for (int i = 1; i < tree.size(); i++) {
            visited = new boolean[n + 1];
            visited[i] = true;
            dfs(i, 0);
        }

        System.out.println(answer);
    }

    static void dfs(int idx, int hab) {
        for (Node child : tree.get(idx)) {
            if (!visited[child.idx]) {
                visited[child.idx] = true;
                answer = Math.max(answer, hab + child.dis);
                dfs(child.idx, hab + child.dis);
            }
        }
    }
}
