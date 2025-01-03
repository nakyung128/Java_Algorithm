import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA2115 {
    static int[][] honey;
    static boolean[][] visited;
    static ArrayList<ArrayList<Integer>> selected;
    static ArrayList<Integer> honies;
    static int n, m, c;
    static int max_result;
    static int price;
    static boolean[] honey_visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= t; test_case++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            honey = new int[n][n];
            visited = new boolean[n][n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    honey[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            selected = new ArrayList<>();
            honies = new ArrayList<>();

            price = 0;
            select(0, 0);

            System.out.printf("#%d %d\n", test_case, price);

        }
    }

    static void select(int x, int y) {
        if (selected.size() == 2) {
            // 벌꿀 채취
            take();
            return;
        }

        for (int i = x; i < n; i++) {
            for (int j = y; j < n - m + 1; j++) {
                honies = new ArrayList<>();
                if (!visited[i][j]) {
                    for (int k = 0; k < m; k++) {
                        honies.add(honey[i][j + k]);
                        visited[i][j+k] = true;
                    }
                    selected.add(honies);
                    if (j >= n - m - 1) {
                        select(i + 1, 0);
                        for (int k = 0; k < m; k++) {
                            visited[i][j + k] = false;
                        }
                        selected.remove(selected.size() - 1);
                    } else {
                        // select(i, 0);
                        select(i, j);
                        for (int k = 0; k < m; k++) {
                            visited[i][j + k] = false;
                        }
                        selected.remove(selected.size() - 1);
                    }
                }
            }
        }
    }

    // 꿀 채취하는 함수 (최대 양 구하기)
    static void take() {
        int answer = 0;
        int selectHap = 0;
        for (ArrayList<Integer> select : selected) {
            selectHap = 0;
            int honeyHap = 0;

            for (int honey : select) {
                honeyHap += honey;
            }

            // 다 합한 것이 채취할 수 있는 꿀의 양보다 큰 경우
            // 골라야 함 
            if (honeyHap > c) {
                // System.out.println(select);
                honies = new ArrayList<>();
                max_result = 0;
                honey_visited = new boolean[select.size()];
                pick(select, 0, 0);
                selectHap = max_result;
            } else {
                for (int honey : select) {
                    selectHap += Math.pow(honey, 2);
                }
            }
            answer += selectHap;
        }
        price = Math.max(price, answer);
    }

    // 꿀 고르기
    static void pick(ArrayList<Integer> select, int idx, int hap) {
        if (hap + select.get(idx) > c) {
            int result = 0;
            for (int i = 0; i < honies.size(); i++) {
                result += Math.pow(honies.get(i), 2);
            }
            max_result = Math.max(max_result, result);
            return;
        }

        for (int i = idx; i < m; i++) {
            if (hap + select.get(i) <= c) {
                if (!honey_visited[i]) {
                    honies.add(select.get(i));
                    honey_visited[i] = true;
                    if (i + 1 < m) {
                        pick(select, i + 1, hap + select.get(i));
                        honies.remove(honies.size()-1); 
                        honey_visited[i] = false;
                    } else {
                        pick(select, 0, hap + select.get(i));
                        honies.remove(honies.size()-1); 
                        honey_visited[i] = false;
                    }
                }
            }
        }
    }
}
