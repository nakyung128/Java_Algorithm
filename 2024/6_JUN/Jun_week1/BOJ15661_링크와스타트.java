import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ15661_링크와스타트 {
    static int n;
    static int[][] skill;
    static ArrayList<Integer> link, start;
    static int answer;

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

        link = new ArrayList<>();
        answer = Integer.MAX_VALUE;

        // 팀 나누기 (조합)
        for (int i = 1; i < n; i++) {
            devide(1, 0, i);
        }

        System.out.println(answer);
    }

    static void devide(int start, int depth, int cnt) {
        if (link.size() == cnt) {
            // 스타트에 남은 팀 추가
            makeStart(link);
            // 능력치 계산
            answer = Math.min(answer, cal());
            return;
        }

        for (int i = start; i <= n; i++) {
            link.add(i);
            devide(i + 1, depth + 1, cnt);
            link.remove(link.size() - 1);
        }
    }

    static void makeStart(ArrayList<Integer> link) {
        start = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (!link.contains(i)) {
                start.add(i);
            }
        }
    }

    static int cal() {
        int linkSkill = 0;
        int startSkill = 0;

        for (int i = 0; i < link.size() - 1; i++) {
            int num1 = link.get(i) - 1;
            for (int j = i + 1; j < link.size(); j++) {
                int num2 = link.get(j) - 1;
                linkSkill += skill[num1][num2];
                linkSkill += skill[num2][num1];
            }
        }

        for (int i = 0; i < start.size() - 1; i++) {
            int num1 = start.get(i) - 1;
            for (int j = i + 1; j < start.size(); j++) {
                int num2 = start.get(j) - 1;
                startSkill += skill[num1][num2];
                startSkill += skill[num2][num1];
            }
        }

        return Math.abs(linkSkill - startSkill);
    }
}
