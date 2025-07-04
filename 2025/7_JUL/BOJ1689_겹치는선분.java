import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1689_겹치는선분 {
    static class Line implements Comparable<Line> {
        int idx;
        int type;

        Line(int idx, int type) {
            this.idx = idx;
            this.type = type;
        }

        @Override
        public int compareTo(Line o) {
            if (this.idx == o.idx) {
                return o.type - this.type;
            }
            return this.idx - o.idx;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        PriorityQueue<Line> lines = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lines.add(new Line(start, 0)); // 시작
            lines.add(new Line(end, 1)); // 끝
        }

        int count = 0;
        int answer = 1;

        while (!lines.isEmpty()) {
            Line line = lines.poll();
            if (line.type == 0) {
                count++;
                answer = Math.max(answer, count);
            } else {
                count--;
            }
        }
        System.out.println(answer);
    }
}
