import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ21278_호석이두마리치킨 {
    static ArrayList<Integer> comb;
    static int[][] dist;
    static int[] answer_arr;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        dist = new int[n + 1][n + 1];

        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = 101;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            dist[a][b] = 1;
            dist[b][a] = 1;
        }

        for (int k = 1; k < n + 1; k++) {
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        comb = new ArrayList<>();
        answer_arr = new int[2];
        answer = Integer.MAX_VALUE;
        choice(n, 1, 0);
        System.out.println(answer_arr[0] + " " + answer_arr[1] + " " + answer);
    }

    static void choice(int n, int idx, int depth) {
        if (depth == 2) {
            // 거리 계산
            cal(n, comb.get(0), comb.get(1));
            return;
        }
        for (int i = idx; i < n + 1; i++) {
            comb.add(i);
            choice(n, i + 1, depth + 1);
            comb.remove(comb.size() - 1);
        }
    }

    static void cal(int n, int a, int b) {
        int hab = 0;
        for (int i = 1; i < n + 1; i++) {
            int min_dist = Math.min(dist[i][a], dist[i][b]) * 2;
            hab += min_dist;
        }
        if (hab < answer) {
            answer = hab;
            answer_arr[0] = a;
            answer_arr[1] = b;
        }
    }
}
