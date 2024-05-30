import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14890_경사로 {
    static int[][] map;
    static int n, l, idx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken()); // 활주로의 길이

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;

        for (int i = 0; i < n; i++) {
            int[] row = map[i];
            idx = 0;
            if (can(row))
                answer++;
        }

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

        System.out.println(answer);
    }

    static boolean can(int[] arr) {
        int cnt = 1;
        int h = arr[0];

        for (int i = 1; i < n; i++) {
            if (h == arr[i]) {
                cnt++;
            } else if (arr[i] - h == 1) {
                if (cnt < l) { // 지금까지 개수보다 x가 크면 안 됨
                    return false;
                } else {
                    cnt = 1;
                    h = arr[i];
                }
            } else if (h - arr[i] == 1) {
                if (l + i > n) { // 길이 넘어가면 안 됨 (삐져나오면?)
                    return false;
                }
                for (int j = 1; j < l; j++) {
                    if (arr[i] != arr[i + j]) {
                        return false;
                    }
                }
                i += l - 1;
                h = arr[i];
                cnt = 0;
            } else {
                return false;
            }
        }
        return true;
    }
}