import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ20917_사회적거리두기 {
    static int n, s;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        for (int tc = 0; tc < t; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // 설치된 콘센트 수
            s = Integer.parseInt(st.nextToken()); // 참가하는 팀의 수
            arr = new int[n]; // 설치된 콘센트의 위치
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr); // 오름차순 정렬

            int left = 1;
            int right = arr[n - 1] - arr[0];
            int answer = 0;

            while (left <= right) {
                int mid = (left + right) / 2;

                if (isValid(mid)) {
                    answer = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            System.out.println(answer);
        }
    }

    static boolean isValid(int max) {
        int cnt = 1;
        int hab = 0;
        for (int i = 0; i < n - 1; i++) {
            hab += arr[i + 1] - arr[i];
            if (hab >= max) {
                cnt++;
                hab = 0;
            }
        }
        return cnt >= s;
    }
}
