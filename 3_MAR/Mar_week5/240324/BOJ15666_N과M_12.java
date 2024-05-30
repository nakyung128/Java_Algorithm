import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

/**
 * 중복조합
 */
public class BOJ15666_N과M_12 {
    static int n, m;
    static ArrayList<Integer> select, nums;
    static LinkedHashSet<String> result;
    static boolean[] visited;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        nums = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            nums.add(Integer.parseInt(st.nextToken()));
        }

        result = new LinkedHashSet<>();
        visited = new boolean[nums.size()];
        select = new ArrayList<>();
        sb = new StringBuilder();

        nums.sort(null);
        choice(0, 0);
        result.forEach(System.out::println);
    }

    static void choice(int start, int depth) {
        if (depth == m) {
            sb = new StringBuilder();
            for (int s : select) {
                sb.append(s + " ");
            }
            result.add(sb.toString());
            return;
        }

        for (int i = start; i < nums.size(); i++) {
            select.add(nums.get(i));
            choice(i, depth + 1);
            select.remove(select.size() - 1);
        }
    }
}
