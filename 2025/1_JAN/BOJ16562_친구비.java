import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16562_친구비 {
    static int[] root, money;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        money = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            money[i] = Integer.parseInt(st.nextToken());
        }

        root = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            root[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        int hab = 0;
        for (int i = 1; i < n + 1; i++) {
            if (root[i] == i) {
                hab += money[i];
            }
        }
        if (hab > k) {
            System.out.println("Oh no");
        } else {
            System.out.println(hab);
        }
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            if (money[a] > money[b]) {
                root[a] = b;
            } else {
                root[b] = a;
            }
        }
    }

    static int find(int a) {
        if (root[a] == a) {
            return a;
        } else {
            return root[a] = find(root[a]);
        }
    }
}
