import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ15657_N과M_8 {
    static int n, m;
    static int[] nums;
    static ArrayList<Integer> select;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        nums = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        select = new ArrayList<>();
        sb = new StringBuilder();
        choice(0, 0);
        System.out.println(sb);
    }

    static void choice(int start, int depth) {
        if (depth == m) {
            for (int s : select) {
                sb.append(s + " ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < nums.length; i++) {
            select.add(nums[i]);
            choice(i, depth + 1);
            select.remove(select.size() - 1);
        }

    }
}