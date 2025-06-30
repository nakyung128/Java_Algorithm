import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ30804_과일탕후루 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        HashSet<Integer> hs = new HashSet<>();
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            hs.add(arr[i]);
        }

        if (hs.size() <= 2) {
            System.out.println(n);
        } else {
            int left = 0;
            int right = 0;
            int answer = 1;
            HashMap<Integer, Integer> hm = new HashMap<>();
            hm.put(arr[left], 1);
            while (left <= right) {
                if (hm.size() <= 2 && right < n) {
                    answer = Math.max(answer, right - left + 1);
                    right++;
                    if (right == n) {
                        break;
                    }
                    hm.put(arr[right], hm.getOrDefault(arr[right], 0) + 1);
                } else {
                    if (hm.get(arr[left]) > 1) {
                        hm.replace(arr[left], hm.get(arr[left]) - 1);
                    } else {
                        hm.remove(arr[left]);
                    }
                    left++;
                }
            }
            System.out.println(answer);
        }
    }
}
