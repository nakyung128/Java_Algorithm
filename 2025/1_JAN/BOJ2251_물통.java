import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2251_물통 {
    static int[] send = { 0, 0, 1, 1, 2, 2 };
    static int[] receive = { 1, 2, 0, 2, 0, 1 };
    static int[] size;
    static boolean[][] visited;
    static ArrayList<Integer> answer;
    static Queue<int[]> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        size = new int[3];
        size[0] = Integer.parseInt(st.nextToken());
        size[1] = Integer.parseInt(st.nextToken());
        size[2] = Integer.parseInt(st.nextToken());

        visited = new boolean[201][201];
        visited[0][0] = true;
        answer = new ArrayList<>();
        answer.add(size[2]);

        q = new LinkedList<>();
        q.offer(new int[] { 0, 0 });

        bfs();

        answer.sort(null);
        for (int a : answer) {
            System.out.print(a + " ");
        }
    }

    static void bfs() {
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int a = now[0];
            int b = now[1];
            int c = size[2] - a - b;

            // 물 옮기는 방법 총 여섯 가지
            for (int i = 0; i < 6; i++) {
                int[] next = { a, b, c };
                next[receive[i]] += next[send[i]];
                next[send[i]] = 0;
                // 물이 넘치면 다시 이전 물통에 남는만큼 넣기
                if (next[receive[i]] > size[receive[i]]) {
                    next[send[i]] = next[receive[i]] - size[receive[i]];
                    next[receive[i]] = size[receive[i]];
                }

                // 체크한 적 없는 경우면
                if (!visited[next[0]][next[1]]) {
                    visited[next[0]][next[1]] = true;
                    q.offer(new int[] { next[0], next[1] });

                    if (next[0] == 0) {
                        answer.add(next[2]);
                    }
                }
            }
        }
    }
}
