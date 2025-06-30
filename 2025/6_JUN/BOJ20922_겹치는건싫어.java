import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ20922_겹치는건싫어 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        int answer = 0;
        HashMap<Integer, Integer> hs = new HashMap<>();
        while (left <= right) {
            if (hs.getOrDefault(arr[right], 0) < k) {
                answer = Math.max(answer, right - left + 1);
                hs.put(arr[right], hs.getOrDefault(arr[right], 0) + 1);
                right++;
                if (right == n) {
                    break;
                }
            } else {
                if (hs.get(arr[left]) > 1) {
                    hs.replace(arr[left], hs.get(arr[left]) - 1);
                } else {
                    hs.remove(arr[left]);
                }
                left++;
            }
        }
        System.out.println(answer);
    }
}
