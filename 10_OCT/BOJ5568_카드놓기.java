import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ5568_카드놓기 {
    static int n, k;
    static int[] nums;
    static ArrayList<Integer> per;
    static ArrayList<String> answers;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        nums = new int[n];
        visited = new boolean[n];
        per = new ArrayList<>();
        answers = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        select(0);
        System.out.println(answers.size());
    }

    static void select(int depth) {
        if (depth == k) {
            String num = "";
            for (int p : per) {
                num += String.valueOf(p);
            }
            boolean flag = true;
            for (String s : answers) {
                if (s.equals(num)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                answers.add(num);
            }
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                per.add(nums[i]);
                select(depth + 1);
                visited[i] = false;
                per.remove(per.size() - 1);
            }
        }
    }
}
