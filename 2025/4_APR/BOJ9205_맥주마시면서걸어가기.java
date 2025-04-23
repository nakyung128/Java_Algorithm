import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ9205_맥주마시면서걸어가기 {
    static int[] house, rock;
    static ArrayList<int[]> convs;
    static Queue<int[]> q;
    static boolean[] visited;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        sb = new StringBuilder();
        for (int tc = 0; tc < t; tc++) {
            st = new StringTokenizer(br.readLine());
            int conv = Integer.parseInt(st.nextToken()); // 편의점 개수
            convs = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            house = new int[2];
            house[0] = Integer.parseInt(st.nextToken());
            house[1] = Integer.parseInt(st.nextToken());

            for (int i = 0; i < conv; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                convs.add(new int[] { x, y });
            }

            st = new StringTokenizer(br.readLine());
            rock = new int[2];
            rock[0] = Integer.parseInt(st.nextToken());
            rock[1] = Integer.parseInt(st.nextToken());

            int distance = Math.abs(house[0] - rock[0]) + Math.abs(house[1] - rock[1]);
            if (distance <= 1000) {
                sb.append("happy\n");
            } else {
                q = new LinkedList<>();
                q.add(house);
                visited = new boolean[convs.size()];
                bfs();
            }
        }
        System.out.print(sb.toString());
    }

    static void bfs() {
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];
            if (Math.abs(x - rock[0]) + Math.abs(y - rock[1]) <= 1000) {
                sb.append("happy\n");
                return;
            }
            for (int i = 0; i < convs.size(); i++) {
                if (!visited[i]) {
                    int nx = convs.get(i)[0];
                    int ny = convs.get(i)[1];
                    int dist = Math.abs(x - nx) + Math.abs(y - ny);

                    if (dist <= 1000) {
                        visited[i] = true;
                        q.add(new int[] { nx, ny });
                    }
                }
            }
        }
        sb.append("sad\n");
    }
}
