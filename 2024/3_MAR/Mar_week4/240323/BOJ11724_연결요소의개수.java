import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11724_연결요소의개수 {
    static int n, m;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b);
        }

        int answer = 0;

        for (int i = 1; i <= n; i++) {
            if (parent[i] == i)
                answer++;
        }
        System.out.println(answer);
    }

    static void union(int x, int y) {
        x = find(x); // x의 부모 노드 찾기
        y = find(y); // y의 부모 노드 찾기

        // 이미 같은 그래프면
        if (x <= y) {
            parent[y] = x;
        } else {
            parent[x] = y;
        }
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }
}
