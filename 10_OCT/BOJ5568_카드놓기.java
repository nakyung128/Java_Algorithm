import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class BOJ5568_카드놓기 {
    static int n, k;
    static int[] nums;
    static ArrayList<Integer> arr;
    static HashSet<String> hs;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        nums = new int[n];
        visited = new boolean[n];
        arr = new ArrayList<>();
        hs = new HashSet<>();

        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        select(0);
        System.out.println(hs.size());
    }

    static void select(int depth) {
        if (depth == k) {
            StringBuilder sb = new StringBuilder();
            for (int n : arr) {
                sb.append(String.valueOf(n));
            }
            hs.add(sb.toString());
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr.add(nums[i]);
                select(depth + 1);
                visited[i] = false;
                arr.remove(arr.size() - 1);
            }
        }
    }
}
