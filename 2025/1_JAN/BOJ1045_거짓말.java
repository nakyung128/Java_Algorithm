import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1045_거짓말 {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 사람의 수
        int m = Integer.parseInt(st.nextToken()); // 파티의 수

        st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());

        int[] ppl = new int[size];
        for (int i = 0; i < size; i++) {
            ppl[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<ArrayList<Integer>> party = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            party.add(new ArrayList<>());
            int party_size = Integer.parseInt(st.nextToken());

            for (int j = 0; j < party_size; j++) {
                party.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

        parent = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            int root = party.get(i).get(0);
            for (int j = 1; j < party.get(i).size(); j++) {
                union(root, party.get(i).get(j));
            }
        }

        int result = 0;
        for (int i = 0; i < m; i++) {
            int root = party.get(i).get(0);
            boolean enable = true;
            for (int j = 0; j < size; j++) {
                if (find(root) == find(ppl[j])) {
                    enable = false;
                    break;
                }
            }
            if (enable) {
                result++;
            }
        }

        System.out.println(result);
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
}
