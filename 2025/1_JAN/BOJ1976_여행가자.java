import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1976_여행가자 {
    static int[] parent, city;
    static int[][] link;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        link = new int[n + 1][n + 1];
        city = new int[m];

        for (int i = 1; i < n + 1; i++) {
            parent[i] = i;
        }

        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < n + 1; j++) {
                link[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (link[i][j] == 1) {
                    union(i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            city[i] = find(Integer.parseInt(st.nextToken()));
        }

        System.out.println(enable(m));
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            parent[b] = a;
        }
    }

    static int find(int a) {
        if (parent[a] == a) {
            return a;
        } else {
            return parent[a] = find(parent[a]);
        }
    }

    static String enable(int m) {
        for (int i = 0; i < m - 1; i++) {
            if (city[i] != city[i + 1]) {
                return "NO";
            }
        }
        return "YES";
    }
}
