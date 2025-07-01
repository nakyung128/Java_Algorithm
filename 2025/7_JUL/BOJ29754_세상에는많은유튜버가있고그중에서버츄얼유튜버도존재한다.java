import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ29754_세상에는많은유튜버가있고그중에서버츄얼유튜버도존재한다 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int week = m / 7;

        // 주별로 관리해야 됨...
        // 모든 주에 주 5회 이상, 60시간 이상 방송해야 '진짜 버츄얼 유튜버'
        ArrayList<HashMap<String, Integer>> time = new ArrayList<>();
        ArrayList<HashMap<String, Integer>> count = new ArrayList<>();
        for (int i = 0; i < week; i++) {
            time.add(new HashMap<>());
            count.add(new HashMap<>());
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int day = Integer.parseInt(st.nextToken()) - 1;
            int w = day / 7;
            String start = st.nextToken();
            String end = st.nextToken();

            int startH = Integer.parseInt(start.substring(0, 2));
            int startM = Integer.parseInt(start.substring(3, 5));
            int endH = Integer.parseInt(end.substring(0, 2));
            int endM = Integer.parseInt(end.substring(3, 5));

            if (startM > endM) {
                endH--;
                endM += 60;
            }
            int strim = (endH - startH) * 60 + endM - startM;
            if (time.get(w).containsKey(name)) {
                int prev = time.get(w).get(name);
                int prevCnt = count.get(w).get(name);
                time.get(w).replace(name, prev + strim);
                count.get(w).replace(name, prevCnt + 1);
            } else {
                time.get(w).put(name, strim);
                count.get(w).put(name, 1);
            }
        }
        ArrayList<TreeSet<String>> answer = new ArrayList<>();
        for (int i = 0; i < week; i++) {
            answer.add(new TreeSet<>());
        }

        for (int i = 0; i < week; i++) {
            ArrayList<String> arr = new ArrayList<>();
            for (String name : time.get(i).keySet()) {
                if (time.get(i).get(name) >= 3600) {
                    arr.add(name);
                }
            }
            for (String name : count.get(i).keySet()) {
                if (count.get(i).get(name) >= 5 && arr.contains(name)) {
                    answer.get(i).add(name);
                }
            }
        }
        if (week == 1) {
            if (answer.get(0).size() == 0) {
                System.out.println(-1);
            } else {
                for (String name : answer.get(0)) {
                    System.out.println(name);
                }
            }
        } else {
            int cnt = 0;
            for (String name : answer.get(0)) {
                boolean flag = false;
                for (int i = 1; i < week; i++) {
                    if (!answer.get(i).contains(name)) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    cnt++;
                    System.out.println(name);
                }
            }
            if (cnt == 0) {
                System.out.println(-1);
            }
        }
    }
}
