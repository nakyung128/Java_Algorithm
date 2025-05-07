import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3079_입국심사 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 심사대 개수
        long m = Long.parseLong(st.nextToken()); // 사람 수 (최대 10억이므로 long으로 선언)
        int[] arr = new int[n];

        long start = 0;
        long end = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            end = Math.max(end, arr[i]);
        }
        end *= m; // 제일 최대로 걸리는 시간 (최악의 경우)

        long answer = end;

        // 이분탐색으로 가능한 최소 시간 찾기
        while (start <= end) {
            long mid = (start + end) / 2;
            long able = 0;
            for (int time : arr) {
                able += mid / time;
                // 오버플로우 발생할 수 있으므로 m보다 크면 break
                if (able > m) {
                    break;
                }
            }
            if (able >= m) {
                end = mid - 1; // 시간 줄여요
                answer = mid;
            } else {
                start = mid + 1; // 시간 늘려요
            }
        }
        System.out.println(answer);
    }
}
