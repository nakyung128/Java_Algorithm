import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1238 {
    static Queue<Integer> queue;
    static int[] visited;
    static ArrayList<ArrayList<Integer>> data;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int test_case = 1; test_case <= 10; test_case++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());

            visited = new int[101];
            data = new ArrayList<>();

            for (int i = 0; i < 101; i++) {
                data.add(new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < size / 2; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                data.get(from).add(to);
            }

            queue = new LinkedList<>();
            queue.offer(start); // 시작점 추가
            visited[start] = 1; // 방문 처리

            int result = bfs();

            System.out.printf("#%d %d\n", test_case, result);
        }
    }

    public static int bfs() {
        int lastMax = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = 0;

            for (int s = 0; s < size; s++) {
                int now = queue.poll();

                for (int n : data.get(now)) {
                    if (visited[n] == 0) {
                        queue.offer(n);
                        visited[n] = 1;
                        max = Math.max(max, n); // 현재 최대 번호 
                    }
                }

                // 최대 번호가 갱신되었을 경우 lastMax 갱신
                if (max > 0) lastMax = max;
                
            }

        }
        return lastMax;
    }
}
