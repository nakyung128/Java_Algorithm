import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ3980_선발명단 {
    static int[][] power;
    static boolean[] visited;
    static ArrayList<Integer> perm;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < t; tc++) {
            power = new int[11][11];
            visited = new boolean[11];

            for (int i = 0; i < 11; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 11; j++) {
                    power[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            answer = 0;
            perm = new ArrayList<>();
            permu(0);
            sb.append(answer + "\n");
        }
        System.out.print(sb.toString());
    }

    static void permu(int depth) {
        if (depth == 11) {
            int hab = 0;
            for (int i = 0; i < perm.size(); i++) {
                hab += power[i][perm.get(i)];
            }
            answer = Math.max(answer, hab);
            return;
        }
        for (int i = 0; i < 11; i++) {
            if (!visited[i] && power[perm.size()][i] != 0) {
                visited[i] = true;
                perm.add(i);
                permu(depth + 1);
                visited[i] = false;
                perm.remove(perm.size() - 1);
            }
        }
    }
}
