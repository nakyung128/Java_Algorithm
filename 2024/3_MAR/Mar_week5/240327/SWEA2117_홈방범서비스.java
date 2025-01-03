import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA2117_홈방범서비스 {
    static int n, m, answer;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= t; test_case++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken()); // 하나의 집이 지불할 수 있는 비용

            map = new int[n][n];
            int house = 0;

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1) {
                        house++;
                    }
                }
            }

            int answer = 0;

            // 모든 집한테 다 수익을 받은 것보다 운영 비용이 커지기 전까지만 반복
            for (int k = 2; (k * k + (k - 1) * (k - 1)) < house * m; k++) {
                int cost = cost(k); // 집들을 통해 얻는 수익
                int sale = cost - (k * k + (k - 1) * (k - 1)); // 보안회사 이익

                // 보안회사 이익이 0보다 크면 answer 갱신해 주기
                if (sale >= 0) {
                    answer = Math.max(answer, cost / m);
                }
            }

            // 만약 k = 2부터 아무것도 안 되면 한 집이라도...
            if (answer == 0) {
                answer = m - 1;
            }

            System.out.printf("#%d %d\n", test_case, answer);
        }
    }

    static int cost(int k) {
        int max_cost = 0;
        int cost = 0;

        // 집마다 마름모 형태로 확인하기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cost = 0;

                // 만약 현재 위치 집이면 cost에 추가
                if (map[i][j] == 1) {
                    cost += m;
                }

                // 내 위치에서 위 아래로 k - 1 만큼
                for (int x = 1; x < k; x++) {
                    if (i + x < n && map[i + x][j] == 1) {
                        cost += m;
                    }
                    if (i - x >= 0 && map[i - x][j] == 1) {
                        cost += m;
                    }
                }

                // 왼쪽으로 k - 1 만큼, 오른쪽으로 k - 1만큼 이동하면서 위아래 확인
                // 마름모 범위 확인
                for (int y = 1; y < k; y++) {
                    if (j - y >= 0 && map[i][j - y] == 1) {
                        cost += m;
                    }
                    if (j + y < n && map[i][j + y] == 1) {
                        cost += m;
                    }

                    for (int x = 1; x < k - y; x++) {
                        if (j - y >= 0 && i - x >= 0 && map[i - x][j - y] == 1) {
                            cost += m;
                        }
                        if (j - y >= 0 && i + x < n && map[i + x][j - y] == 1) {
                            cost += m;
                        }
                        if (j + y < n && i - x >= 0 && map[i - x][j + y] == 1) {
                            cost += m;
                        }
                        if (j + y < n && i + x < n && map[i + x][j + y] == 1) {
                            cost += m;
                        }
                    }
                }
                max_cost = Math.max(max_cost, cost); // 최대 이득 갱신
            }
        }

        return max_cost;
    }
}
