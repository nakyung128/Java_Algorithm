import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA2383_점심식사시간 {
    static int n;
    static int[][] graph;
    static boolean[][] visited;
    static ArrayList<ArrayList<Integer>> ppl;
    static ArrayList<ArrayList<Integer>> stairs; // 계단 위치
    static ArrayList<ArrayList<ArrayList<Integer>>> stair_ppl;
    static ArrayList<Integer> dis_arr;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());

            graph = new int[n][n];
            visited = new boolean[n][n];
            ppl = new ArrayList<>();
            stairs = new ArrayList<>();

            int ppl_cnt = 0; // 사람이 몇 명 있는지

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                    if (graph[i][j] == 1) {
                        ppl_cnt++;
                        ArrayList<Integer> arr = new ArrayList<>();
                        arr.add(i);
                        arr.add(j);
                        ppl.add(arr);
                    } else if (graph[i][j] != 1 && graph[i][j] != 0) {
                        ArrayList<Integer> arr = new ArrayList<>();
                        arr.add(i);
                        arr.add(j);
                        stairs.add(arr);
                    }
                }
            }

            stair_ppl = new ArrayList<>();

            for (int i = 0; i < 2; i++) {
                stair_ppl.add(new ArrayList<>());
            }

            answer = Integer.MAX_VALUE;

            // 0부터 ppl_cnt까지 nCr (조합 구하기)
            for (int i = 0; i <= ppl_cnt; i++) {
                select(0, i, 0);
            }
            System.out.printf("#%d %d\n", test_case, answer);
        }
    }

    static void select(int start, int r, int depth) {
        if (depth == r) {
            // 두 번째 계단
            if (stair_ppl.get(0).size() > 0) {
                for (ArrayList<Integer> p : ppl) {
                    if (!stair_ppl.get(0).contains(p)) {
                        stair_ppl.get(1).add(p);
                    }
                }
            } else {
                for (ArrayList<Integer> p : ppl) {
                    stair_ppl.get(1).add(p);
                }
            }

            // 계산
            // 최종 정답에는 작은 것 넣어 주기
            answer = Math.min(answer, down());

            // 계산 끝난 후 stair_ppl.get(1) 조합 배열 초기화해 주기
            stair_ppl.get(1).clear();
            return;
        }

        for (int i = start; i < ppl.size(); i++) {
            stair_ppl.get(0).add(ppl.get(i));
            select(i + 1, r, depth + 1);
            stair_ppl.get(0).remove(stair_ppl.get(0).size() - 1);
        }
    }

    static int down() {
        int max_value = 0;

        for (int i = 0; i < 2; i++) {
            // 아무도 없으면 안 함
            if (stair_ppl.get(i).size() == 0)
                continue;

            // 현재 계단
            int minute = 0;
            ArrayList<Integer> stair = stairs.get(i);
            int sx = stair.get(0);
            int sy = stair.get(1);

            // 계단에 갈 사람들
            ArrayList<ArrayList<Integer>> ppl = stair_ppl.get(i);

            dis_arr = new ArrayList<>();

            // 거리 배열
            for (ArrayList<Integer> p : ppl) {
                int dis = Math.abs(p.get(0) - sx) + Math.abs(p.get(1) - sy);
                dis_arr.add(dis);
            }

            // 오름차순 정렬 (가장 맨 뒤: 가장 멀리 있는 애)
            dis_arr.sort(null);

            // 세 명만 있으면 대기시간 발생하지 않음
            if (dis_arr.size() < 4) {
                // 걸리는 시간, 가장 계단과 멀리 있는 사람 + 계단 크기 + 1
                minute = dis_arr.get(dis_arr.size() - 1) + graph[sx][sy] + 1;
            } else { // 네 명 이상부터는 대기 시간 발생함
                int lastIdx = dis_arr.size() - 1;
                // 나보다 3 앞에 있는 애가 계단을 다 내려간 후의 시간 + 계단 크기 + 1
                // 내가 계단까지 가는 시간 + 계단 크기 + 1
                // 중에 더 큰 것을 고르기 (후자가 더 크다면 내가 앞에 있는 애들이 끝나도 계단에 도착을 못 했다는 뜻이므로)
                minute = Math.max(dis_arr.get(dis_arr.size() - 1) + graph[sx][sy] + 1,
                        dis_arr.get(lastIdx - 3) + graph[sx][sy] + 1 + graph[sx][sy]);
            }

            // 두 계단 중에 더 오래 걸리는 게 사람들이 모두 내려오는 동안 걸리는 시간임
            max_value = Math.max(max_value, minute);
        }
        return max_value;
    }
}
