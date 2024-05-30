import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * union-find 이용해서 풀기
 */
public class SWEA7465_창용마을무리의개수 {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= t; test_case++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            parent = new int[n];
            for (int i = 1; i <= n; i++) {
                parent[i - 1] = i;
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                union(a, b);
            }

            int answer = 0;
            for (int i = 0; i < n; i++) {
                if (parent[i] == i + 1)
                    answer++;
            }

            System.out.printf("#%d %d\n", test_case, answer);
        }
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x > y) {
            parent[x - 1] = y;
        } else {
            parent[y - 1] = x;
        }
    }

    static int find(int x) {
        if (parent[x - 1] == x) {
            return x;
        }
        return parent[x - 1] = find(parent[x - 1]);
    }
}