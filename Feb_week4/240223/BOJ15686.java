import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ15686 {
    static int n;
    static int m;
    static int[][] city; // 도시 지도
    static boolean[] visited; // 방문 여부
    static ArrayList<int[]> chicken; // 치킨집 위치
    static ArrayList<int[]> house; // 집 위치
    static ArrayList<int[]> selected; // 선택된 치킨집 위치
    static int min_result; // 결과값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        city = new int[n][n];

        chicken = new ArrayList<>();
        selected = new ArrayList<>();
        house = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
                if (city[i][j] == 2) { // 치킨집 위치 넣기
                    chicken.add(new int[] {i, j});
                } else if (city[i][j] == 1) { // 집 위치 넣기
                    house.add(new int[] {i, j});
                }
            }
        }

        visited = new boolean[chicken.size()];
        min_result = Integer.MAX_VALUE;

        select(0, 0); // 치킨 집 선택 후 치킨 거리 구하기

        System.out.println(min_result);
    }

    static void select(int cnt, int start) {
        if (cnt == m) {
            distance();
            return;
        }

        // 앞에 것은 다시 안 본다
        // 순서가 상관없는 조합을 구하는 것이기 때문
        // start 인덱스를 기준으로 start보다 작으면 뽑을 후보에서 제외
        for (int i = start; i < chicken.size(); i++) {
            if (!visited[i]) {
                selected.add(chicken.get(i)); // 선택된 치킨집 위치 넣기
                visited[i] = true;
                select(cnt+1, i+1);
                selected.remove(selected.size()-1);
                visited[i] = false;
            }
        }
    }

    // 최소 거리 구하기
    static void distance() {
        int result = 0;
        for (int[] h : house) {
            int min_value = Integer.MAX_VALUE;

            int hx = h[0];
            int hy = h[1];

            for (int[] c : selected) {
                int dis = 0; // 거리

                int cx = c[0];
                int cy = c[1];

                // 가장 거리가 짧은 치킨 집이 치킨 거리
                dis = Math.abs(hx - cx) + Math.abs(hy - cy);
                min_value = Math.min(min_value, dis); // min_value: 한 집의 치킨 거리 구하기
            }
            result += min_value; // result: 도시의 치킨 거리
        }
        // min_result: 도시의 치킨 거리 중 가장 작은 값
        min_result = Math.min(result, min_result);
    }
}
