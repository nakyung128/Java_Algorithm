import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11657_타임머신 {
    static class Edge {
        int start, end, time;

        Edge(int start, int end, int time) {
            this.start = start;
            this.end = end;
            this.time = time;
        }
    }

    static long distance[];
    static Edge edges[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        distance = new long[n + 1];
        edges = new Edge[m + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(start, end, time);
        }

        // 벨만-포드 알고리즘
        distance[1] = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Edge now = edges[j];
                // 더 작은 최단거리 있으면 업데이트
                if (distance[now.start] != Integer.MAX_VALUE && distance[now.end] > distance[now.start] + now.time) {
                    distance[now.end] = distance[now.start] + now.time;
                }
            }
        }

        // 음수 사이클 확인
        boolean cycle = false;
        for (int i = 0; i < m; i++) {
            Edge now = edges[i];
            // 더 작은 최단거리 있으면 업데이트
            if (distance[now.start] != Integer.MAX_VALUE && distance[now.end] > distance[now.start] + now.time) {
                cycle = true;
                break;
            }
        }

        // 음수 사이클 없으면
        StringBuilder sb = new StringBuilder();
        if (!cycle) {
            for (int i = 2; i < n + 1; i++) {
                if (distance[i] == Integer.MAX_VALUE) {
                    sb.append("-1\n");
                } else {
                    sb.append(distance[i] + "\n");
                }
            }
        } else {
            sb.append("-1\n");
        }

        System.out.print(sb.toString());
    }
}
