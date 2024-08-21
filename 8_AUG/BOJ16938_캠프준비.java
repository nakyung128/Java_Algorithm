import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ16938_캠프준비 {
    static int n, l, r, x;
    static ArrayList<Integer> problems, select;
    static int answer, cnt;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 문제 개수
        l = Integer.parseInt(st.nextToken()); // l보다 크거나 같고
        r = Integer.parseInt(st.nextToken()); // r보다 작거나 같아야 함
        x = Integer.parseInt(st.nextToken()); // 난이도 차이 x보다 크거나 같아야 함

        st = new StringTokenizer(br.readLine());
        problems = new ArrayList<>();
        select = new ArrayList<>();
        answer = 0;

        for (int i = 0; i < n; i++) {
            int level = Integer.parseInt(st.nextToken());
            problems.add(level);
        }

        for (int i = 2; i <= n; i++) {
            cnt = i;
            select_problems(0, 0);
        }

        System.out.println(answer);
    }

    static void select_problems(int idx, int depth) {
        if (depth == cnt) {
            ArrayList<Integer> copy = new ArrayList<>();
            copy.addAll(select);
            copy.sort(null);

            int max = copy.get(copy.size() - 1);
            int min = copy.get(0);
            int hab = 0;
            for (int lev : select) {
                hab += lev;
            }
            if (hab >= l && hab <= r) {
                if (max - min >= x) {
                    answer++;
                }
            }
            return;
        }

        for (int i = idx; i < n; i++) {
            select.add(problems.get(i));
            select_problems(i + 1, depth + 1);
            select.remove(select.size() - 1);
        }
    }
}
