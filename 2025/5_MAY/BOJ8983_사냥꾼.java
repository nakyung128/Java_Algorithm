import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ8983_사냥꾼 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int answer = 0;

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr); // 이분탐색 위해서 오름차순 정렬

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (y > l) { // 애초에 y가 사정거리보다 큰 경우는 안 됨
                continue;
            }

            int s = 0;
            int e = n - 1;
            while (s <= e) {
                int mid = (s + e) / 2;
                int dist = Math.abs(arr[mid] - x) + y;

                if (dist <= l) {
                    answer++;
                    break;
                } else {
                    // 사대가 동물보다 왼쪽에 있으면
                    if (arr[mid] < x) {
                        s = mid + 1;
                    } else { // 사대가 동물보다 오른쪽에 있으면
                        e = mid - 1;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
