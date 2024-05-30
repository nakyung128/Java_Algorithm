import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2170_선긋기 {
    static class Line implements Comparable<Line> {
        int x;
        int y;

        Line(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Line o) {
            if (this.x != o.x) {
                return this.x - o.x;
            } else {
                return this.y - o.y;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        ArrayList<Line> lines = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            lines.add(new Line(x, y));
        }

        // 정렬 기준 -> x점이 다르다면 x 오름차순, y점이 같으면 y 오름차순
        lines.sort(null);

        ArrayList<Line> answer = new ArrayList<>();
        answer.add(lines.get(0)); // 일단 맨 처음 선을 answer에 넣음

        int cnt = 0; // 다음 떨어진 선
        for (int i = 1; i < lines.size(); i++) {
            // 새로 그을 선의 시작점이 그어져 있는 선의 끝점보다 작거나 같고,
            // 새로 그을 선의 끝점이 그어져 있는 선의 끝점보다 큰 경우
            if (lines.get(i).x <= answer.get(cnt).y && lines.get(i).y > answer.get(cnt).y) {
                answer.get(cnt).y = lines.get(i).y; // 그어져 있는 선의 끝점을 갱신 (이어 그림)
            } else if (lines.get(i).x > answer.get(cnt).y) { // 만약 새로 그을 선의 시작점이 그어져 있는 끝점보다 크면
                answer.add(lines.get(i)); // 새로운 선 긋기
                cnt++; // 그어져 있는 선을 새로 그은 선으로
            }
        }

        // 거리 계산
        int distance = 0;
        for (Line line : answer) {
            distance += Math.abs(line.y - line.x);
        }

        System.out.println(distance);
    }
}
