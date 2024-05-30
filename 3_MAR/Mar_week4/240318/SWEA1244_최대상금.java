import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * SWEA1244_최대상금
 */

public class SWEA1244_최대상금 {
    static ArrayList<Integer> selected; // 순열 고를 때 사용할 리스트
    static ArrayList<int[]> change_list; // 순열 담는 리스트
    static boolean[] visited; // 순열 만들기 위한 visited 배열
    static ArrayList<Integer> index; // (0 ~ n-1) 순열 만들 리스트
    static ArrayList<int[]> final_list; // 최종 변환할 위치들 담는 리스트
    static int answer, count; // answer: 정답, count: 위치 변환 횟수
    static String num; // 기존 숫자

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= t; test_case++) {
            st = new StringTokenizer(br.readLine());

            num = st.nextToken();
            index = new ArrayList<>();

            for (int i = 0; i < num.length(); i++) {
                index.add(i);
            }

            visited = new boolean[index.size()];

            count = Integer.parseInt(st.nextToken());

            selected = new ArrayList<>();
            change_list = new ArrayList<>();
            make_list(0); // 가능한 두 개 고르는 모든 순열 구하기

            answer = 0;
            visited = new boolean[change_list.size()];

            final_list = new ArrayList<>();
            select(0, 0);

            System.out.printf("#%d %d\n", test_case, answer);
        }
    }

    // 가능한 순열 구하기
    static void make_list(int depth) {
        if (depth == 2) {
            change_list.add(new int[] { selected.get(0), selected.get(1) });
            return;
        }

        for (int i = 0; i < index.size(); i++) {
            if (!visited[i]) {
                selected.add(index.get(i));
                visited[i] = true;
                make_list(depth + 1);
                selected.remove(selected.size() - 1);
                visited[i] = false;
            }
        }
    }

    // 순열 중에 count개 고르기 (조합)
    static void select(int start, int depth) {
        if (depth == count) {
            exchange();
            return;
        }

        for (int i = start; i < change_list.size(); i++) {
            final_list.add(change_list.get(i));
            select(i + 1, depth + 1);
            final_list.remove(final_list.size() - 1);
        }

    }

    // 자리 바꾸기
    static void exchange() {
        char[] num_arr = num.toCharArray();

        for (int[] index : final_list) {
            int a = index[0];
            int b = index[1];

            char temp = num_arr[a];
            num_arr[a] = num_arr[b];
            num_arr[b] = temp;
        }
        answer = Math.max(Integer.parseInt(String.valueOf(num_arr)), answer);
    }
}