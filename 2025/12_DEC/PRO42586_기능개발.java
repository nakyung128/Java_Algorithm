import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> answer = new ArrayList<>(); // 정답 배열
        Queue<Integer> days = new LinkedList<>(); // 각 작업이 며칠 뒤 완성되는지 배열

        // 작업의 개수만큼 반복
        for (int i = 0; i < progresses.length; i++) {
            int left = 100 - progresses[i]; // 남은 진도
            int day = 0; // 걸리는 일수
            // 남은 진도 나누기 속도가 나누어 떨어지지 않는다면 [걸리는 일수 = 진도 / 속도 + 1]
            // 나누어 떨어진다면 [걸리는 일수 = 진도 / 속도]
            if (left % speeds[i] != 0) {
                day = left / speeds[i] + 1;
            } else {
                day = left / speeds[i];
            }
            days.add(day); // 일수 배열에 추가
        }

        int cnt = 1; // 일단 자신 하나 포함
        int d = days.poll();

        // days 큐가 빌 때까지 반복
        // d(선작업) next(다음 작업)보다 오래 걸리면 cnt++ (다음 작업도 나와 함께 배포됨)
        // d보다 next가 오래 걸린다면 cnt만큼 한번에 배포됨, 나머지 변수들 초기화
        // d = next
        while (!days.isEmpty()) {
            int next = days.poll();
            if (d >= next) {
                cnt++;
            } else {
                answer.add(cnt);
                cnt = 1;
                d = next;
            }
        }
        answer.add(cnt); // 비고 나면 마지막에 add(cnt)가 안 되므로 직접 추가

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}