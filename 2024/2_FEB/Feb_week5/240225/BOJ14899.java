import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ14899 {
    static int n;
    static ArrayList<Integer> member;
    static ArrayList<Integer> start;
    static ArrayList<Integer> link;
    static int[][] skill;
    static boolean[] visited;
    static int min_result;
    static int power;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        skill = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                skill[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        member = new ArrayList<>();
        start = new ArrayList<>();
        visited = new boolean[n];

        for (int i = 1; i <= n; i++) {
            member.add(i);
        }

        min_result = Integer.MAX_VALUE;

        back(0, 0);

        System.out.println(min_result);
    }

    // 팀 가르기
    static void back(int idx, int cnt) {
        if (cnt == n / 2) {
            // 링크 팀에 나머지 넣기
            link = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    link.add(member.get(i));
                }
            }

            // 계산하기
            power = select(start);
            int start_power = power;

            power = select(link);
            int link_power = power;

            min_result = Math.min(min_result, Math.abs(start_power - link_power));

            return;
        }

        for (int i = idx; i < n - 1; i++) {
            if (!visited[i]) {
                visited[i] = true;
                start.add(member.get(i));
                back(i + 1, cnt + 1);
                start.remove(start.size() - 1);
                visited[i] = false;
            }
        }
    }

    // 두 명 고르고 계산
    static int select(ArrayList<Integer> team) {
        int hap = 0;

        for (int i = 0; i < team.size() - 1; i++) {
            for (int j = i + 1; j < team.size(); j++) {
                hap += skill[team.get(i) - 1][team.get(j) - 1];
                hap += skill[team.get(j) - 1][team.get(i) - 1];
            }
        }
        return hap;
    }
}