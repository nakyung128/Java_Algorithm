import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1477_휴게소세우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 휴게소 개수
        int m = Integer.parseInt(st.nextToken()); // 새로 지을 휴게소의 개수
        int l = Integer.parseInt(st.nextToken()); // 고속도로의 길이

        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(0);
        arr.add(l);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }
        arr.sort(null); // 휴게소 오름차순 정렬

        int start = 1;
        int end = l - 1;
        int answer = end;

        while (start <= end) {
            int mid = (start + end) / 2;
            int cnt = 0;

            for (int i = 0; i < arr.size() - 1; i++) {
                int dist = arr.get(i + 1) - arr.get(i) - 1; // 이미 휴게소 있는 위치는 세우지 못하니까 -1
                cnt += dist / mid; // 휴게소 그 사이에 몇 개 설치되는지
            }

            if (cnt > m) {
                // 공백 길이 더 늘려야 함
                start = mid + 1;
            } else {
                // 공백 길이 줄여야 함
                end = mid - 1;
                answer = mid;
            }
        }
        System.out.println(answer);
    }
}
