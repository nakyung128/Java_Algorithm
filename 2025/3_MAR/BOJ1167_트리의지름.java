import java.io.*;
import java.util.*;

public class BOJ1167_트리의지름 {
    static class Node {
        int idx, distance;

        Node(int idx, int distance) {
            this.idx = idx;
            this.distance = distance;
        }
    }

    static ArrayList<ArrayList<Node>> tree;
    static boolean[] visited;
    static int far, dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        tree = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            while (true) {
                int idx = Integer.parseInt(st.nextToken());
                if (idx == -1)
                    break;
                int dis = Integer.parseInt(st.nextToken());
                tree.get(node).add(new Node(idx, dis));
            }
        }

        // 첫 번째 DFS (임의의 노드 1에서 가장 먼 노드 찾기)
        visited = new boolean[V + 1];
        find(1, 0);

        // 두 번째 DFS (해당 노드에서 가장 먼 거리 찾기)
        visited = new boolean[V + 1]; // 방문 배열 초기화
        find(far, 0);
        System.out.println(dist);
    }

    static void find(int idx, int sum) {
        visited[idx] = true;
        if (sum > dist) {
            dist = sum;
            far = idx;
        }

        for (Node node : tree.get(idx)) {
            if (!visited[node.idx]) {
                find(node.idx, sum + node.distance);
            }
        }
    }
}
