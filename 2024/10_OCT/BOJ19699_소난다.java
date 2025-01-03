import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ19699_소난다 {
    static int n, m;
    static int[] cows;
    static ArrayList<Integer> comb;
    static HashSet<Integer> hs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        cows = new int[n];
        comb = new ArrayList<>();
        hs = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cows[i] = Integer.parseInt(st.nextToken());
        }

        select(0, 0, 0);
        if (hs.size() == 0) {
            System.out.println(-1);
        } else {
            ArrayList<Integer> sort_arr = new ArrayList<>(hs);
            sort_arr.sort(null);
            for (int num : sort_arr) {
                System.out.print(num + " ");
            }
        }
    }

    static void select(int idx, int hab, int depth) {
        if (depth == m) {
            if (isPrime(hab)) {
                hs.add(hab);
            }
            return;
        }
        for (int i = idx; i < n; i++) {
            comb.add(cows[i]);
            select(i + 1, hab + cows[i], depth + 1);
            comb.remove(comb.size() - 1);
        }
    }

    static boolean isPrime(int hab) {
        for (int i = 2; i < hab; i++) {
            if (hab % i == 0) {
                return false;
            }
        }
        return true;
    }
}
