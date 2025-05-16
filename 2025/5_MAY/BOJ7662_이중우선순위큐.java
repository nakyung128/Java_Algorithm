import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ7662_이중우선순위큐 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < t; tc++) {
            TreeMap<Integer, Integer> map = new TreeMap<>();
            int k = Integer.parseInt(br.readLine());
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                String what = st.nextToken();
                int num = Integer.parseInt(st.nextToken());

                if (what.equals("I")) { // 집어넣기
                    map.put(num, map.getOrDefault(num, 0) + 1);
                } else {
                    if (map.size() == 0) {
                        continue;
                    }
                    int rm = 0;
                    if (num == 1) { // 최댓값 삭제
                        rm = map.lastKey();
                    } else { // 최솟값 삭제
                        rm = map.firstKey();
                    }
                    if (map.put(rm, map.get(rm) - 1) == 1) {
                        map.remove(rm);
                    }
                }
            }
            if (map.size() == 0) {
                sb.append("EMPTY\n");
            } else {
                sb.append(map.lastKey() + " " + map.firstKey() + "\n");
            }
        }
        System.out.print(sb.toString());
    }
}
