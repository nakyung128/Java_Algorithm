import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1217_오민식의고민 {
    static int n, s, e, m;
    static long[] distance, money;
    static Edge[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 도시의 개수
        s = Integer.parseInt(st.nextToken()); // 시작 도시
        e = Integer.parseInt(st.nextToken()); // 도착 도시
        m = Integer.parseInt(st.nextToken()); // 교통 수단의 개수

        money = new long[n];
        distance = new long[n];
        edges = new Edge[m];

        Arrays.fill(distance, Long.MIN_VALUE); // 최단 거리 배열 초기화

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(start, end, price);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            money[i] = Long.parseLong(st.nextToken());
        }

        distance[s] = money[s];

        // 변형된 벨만-포드
        // 양수사이클이 전파되도록 큰 수로 반복
        for (int i = 0; i <= n + 100; i++) {
            for (int j = 0; j < m; j++) {
                int start = edges[j].start;
                int end = edges[j].end;
                int price = edges[j].price;

                // 출발 노드가 방문하지 않은 노드면 스킵
                if (distance[start] == Long.MIN_VALUE) {
                    continue;
                } else if (distance[start] == Long.MAX_VALUE) {
                    // 출발 노드가 양수 사이클에 연결된 노드라면 종료 노드도 연결 노드로 업데이트
                    distance[end] = Long.MAX_VALUE;
                } else if (distance[end] < distance[start] + money[end] - price) {
                    // 더 많이 돈 벌 수 있는 경로가 발견되면
                    distance[end] = distance[start] + money[end] - price;
                    // n - 1 반복 이후 업데이트 되는 노드는 양수 사이클 노드로 변경
                    if (i >= n - 1) {
                        distance[end] = Long.MAX_VALUE;
                    }
                }
            }
        }

        if (distance[e] == Long.MIN_VALUE) {
            System.out.println("gg");
        } else if (distance[e] == Long.MAX_VALUE) {
            System.out.println("Gee");
        } else {
            System.out.println(distance[e]);
        }
    }
}

class Edge {
    int start, end, price;

    Edge(int start, int end, int price) {
        this.start = start;
        this.end = end;
        this.price = price;
    }
}