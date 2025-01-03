import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ18429_근손실 {
    static int[] kits;
    static boolean[] visited;
    static int n, k, answer;
    static ArrayList<Integer> cases;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        kits = new int[n];
        visited = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            kits[i] = Integer.parseInt(st.nextToken());
        }

        cases = new ArrayList<>();
        select(0);

        System.out.println(answer);
    }

    static void select(int depth) {
        if (depth == n) {
            int power = 500;
            boolean flag = false;
            for (int i = 0; i < n; i++) {
                power -= k;
                power += cases.get(i);

                if (power < 500) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                answer++;
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                cases.add(kits[i]);
                select(depth + 1);
                visited[i] = false;
                cases.remove(cases.size() - 1);
            }
        }
    }
}
