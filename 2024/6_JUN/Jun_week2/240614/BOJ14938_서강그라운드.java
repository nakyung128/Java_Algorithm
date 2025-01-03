import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ14938_서강그라운드 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 지역 개수
        int m = Integer.parseInt(st.nextToken()); // 수색 범위
        int r = Integer.parseInt(st.nextToken()); // 길의 개수

        // 각 구역에 있는 아이템의 수
        int[] items = new int[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            int t = Integer.parseInt(st.nextToken());
            items[i] = t;
        }

        int[][] map = new int[n + 1][n + 1];
        for (int[] ma : map) {
            // 수색 범위 최대가 15이므로 16으로 갱신
            Arrays.fill(ma, 16);
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            // 양방향
            map[a][b] = l;
            map[b][a] = l;
        }

        // 플로이드 워셜 알고리즘 (다른 정점과 거리 구하기)
        for (int k = 1; k < n + 1; k++) {
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    // 자기 자신은 거리 0으로
                    if (i == j) {
                        map[i][j] = 0;
                    } else {
                        map[i][j] = Math.min(map[i][k] + map[k][j], map[i][j]);
                    }
                }
            }
        }

        int answer = 0;

        // 예은이의 수색범위 내인 것들 더해 주고 가장 큰 값으로 갱신
        for (int i = 1; i < n + 1; i++) {
            int hap = 0;
            for (int j = 1; j < n + 1; j++) {
                if (map[i][j] <= m) {
                    hap += items[j - 1];
                }
            }
            answer = Math.max(answer, hap);
        }

        System.out.println(answer);
    }
}
