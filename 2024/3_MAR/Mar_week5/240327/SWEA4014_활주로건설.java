import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA4014_활주로건설 {
    static int[][] map;
    static int n, x, idx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= t; test_case++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken()); // 활주로의 길이

            map = new int[n][n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int answer = 0;

            // 행 확인
            for (int i = 0; i < n; i++) {
                int[] row = map[i];
                idx = 0;
                if (can(row))
                    answer++;
            }

            // 열 확인
            for (int i = 0; i < n; i++) {
                int[] col = new int[n];
                for (int j = 0; j < n; j++) {
                    col[j] = map[j][i];
                }
                idx = 0;
                if (can(col)) {
                    answer++;
                }
            }

            System.out.printf("#%d %d\n", test_case, answer);
        }
    }

    static boolean can(int[] arr) {
        int cnt = 1;
        int h = arr[0];

        for (int i = 1; i < n; i++) {
            if (h == arr[i]) {
                cnt++;
            } else if (arr[i] - h == 1) {
                if (cnt < x) { // 지금까지 개수보다 x가 크면 안 됨
                    return false;
                } else {
                    cnt = 1;
                    h = arr[i];
                }
            } else if (h - arr[i] == 1) {
                if (x + i > n) { // 길이 넘어가면 안 됨 (삐져나오면?)
                    return false;
                }
                for (int j = 1; j < x; j++) {
                    if (arr[i] != arr[i + j]) {
                        return false;
                    }
                }
                i += x - 1; // 활주로 끝지점으로 이동
                h = arr[i]; // 높이 갱신해 주기
                cnt = 0;
            } else { // 높이 차이가 1 이상이면 안 됨
                return false;
            }
        }
        return true;
    }
}