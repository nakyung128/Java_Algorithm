import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ15961_회전초밥 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 접시의 수
        int d = Integer.parseInt(st.nextToken()); // 가짓 수
        int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 가짓수
        int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }
        int[] count = new int[d + 1]; // 초밥 종류별 카운트
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        int kind = 0;

        // 초깃값
        for (int i = 0; i < k; i++) {
            int s = nums[i];
            dq.offerLast(s);
            if (count[s] == 0) {
                kind++;
            }
            count[s]++;
        }
        int answer = kind + (count[c] == 0 ? 1 : 0);

        // 슬라이딩 윈도우
        for (int i = 1; i < n; i++) {
            int remove = dq.pollFirst();
            count[remove]--;
            if (count[remove] == 0) {
                kind--;
            }
            int next = nums[(i + k - 1) % n];
            dq.offerLast(next);
            if (count[next] == 0) {
                kind++;
            }
            count[next]++;

            int total = kind + (count[c] == 0 ? 1 : 0);
            answer = Math.max(answer, total);
        }
        System.out.println(answer);
    }
}
