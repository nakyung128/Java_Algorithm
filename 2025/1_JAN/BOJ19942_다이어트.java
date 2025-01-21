import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ19942_다이어트 {
    static int n, mp, mf, ms, mv;
    static int[][] table;
    static int price;
    static ArrayList<Integer> select;
    static ArrayList<String> answer;
    static int depth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        table = new int[n][5];

        st = new StringTokenizer(br.readLine());
        mp = Integer.parseInt(st.nextToken()); // 단백질
        mf = Integer.parseInt(st.nextToken()); // 지방
        ms = Integer.parseInt(st.nextToken()); // 탄수화물
        mv = Integer.parseInt(st.nextToken()); // 비타민

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                table[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        price = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            select = new ArrayList<>();
            depth = i;
            back(0);
        }

        if (price == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(price);
            answer.sort(null);
            System.out.println(answer.get(0));
        }
    }

    static void back(int idx) {
        if (select.size() == depth) {
            int sum = 0;
            int p_sum = 0;
            int f_sum = 0;
            int s_sum = 0;
            int v_sum = 0;

            for (int i : select) {
                p_sum += table[i][0];
                f_sum += table[i][1];
                s_sum += table[i][2];
                v_sum += table[i][3];
                sum += table[i][4];
            }

            if (p_sum >= mp && f_sum >= mf && s_sum >= ms && v_sum >= mv) {
                if (sum < price) {
                    answer = new ArrayList<>();
                    price = sum;
                    String str = "";
                    for (int s : select) {
                        str += (s + 1) + " ";
                    }
                    answer.add(str);
                } else if (sum == price) {
                    String str = "";
                    for (int s : select) {
                        str += (s + 1) + " ";
                    }
                    answer.add(str);
                }
            }
            return;
        }

        for (int i = idx; i < n; i++) {
            select.add(i);
            back(i + 1);
            select.remove(select.size() - 1);
        }
    }
}
