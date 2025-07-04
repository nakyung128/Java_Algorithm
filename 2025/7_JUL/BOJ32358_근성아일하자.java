import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ32358_근성아일하자 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        TreeSet<Integer> ts = new TreeSet<>();
        long answer = 0;
        int guenseong = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            if (type == 1) {
                int idx = Integer.parseInt(st.nextToken());
                ts.add(idx);
            } else {
                // 쓰레기 치우기
                while (!ts.isEmpty()) {
                    Integer left = ts.floor(guenseong); // 가장 가까운 왼쪽 쓰레기
                    Integer right = ts.ceiling(guenseong); // 가장 가까운 오른쪽 쓰래기

                    int target;
                    if (left == null) {
                        target = right;
                    } else if (right == null) {
                        target = left;
                    } else {
                        int leftDistance = guenseong - left;
                        int rightDistance = right - guenseong;
                        if (leftDistance <= rightDistance) {
                            target = left;
                        } else {
                            target = right;
                        }
                    }
                    answer += Math.abs(guenseong - target);
                    guenseong = target;
                    ts.remove(target);
                }
            }
        }
        System.out.println(answer);
    }
}
