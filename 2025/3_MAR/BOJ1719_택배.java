import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1719_택배 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 집하장 개수
        int m = Integer.parseInt(st.nextToken()); // 경로 개수

        int[][] dist = new int[n + 1][n + 1]; // 최단 거리 배열
        int[][] answer = new int[n + 1][n + 1]; // 제일 먼저 가야 하는 집하장 배열

        // dist 초기화
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = 10000001;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            dist[a][b] = time;
            dist[b][a] = time;
            answer[a][b] = b;
            answer[b][a] = a;
        }

        // 플로이드 워셜
        for (int k = 1; k < n + 1; k++) {
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j]; // 최단 거리 갱신
                        answer[i][j] = answer[i][k]; // 거치는 집하장 갱신
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (i == j) {
                    sb.append("- ");
                } else if (answer[i][j] == 0) {
                    sb.append(j + " ");
                } else {
                    sb.append(answer[i][j] + " ");
                }
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}
