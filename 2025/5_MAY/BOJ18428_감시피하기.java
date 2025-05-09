import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ18428_감시피하기 {
    static int n;
    static String[][] map;
    static ArrayList<int[]> teacher;
    static ArrayList<int[]> blank;
    static ArrayList<int[]> select;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        map = new String[n][n];
        blank = new ArrayList<>();
        teacher = new ArrayList<>();
        HashSet<Integer> row = new HashSet<>();
        HashSet<Integer> col = new HashSet<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = st.nextToken();
                if (map[i][j].equals("T")) {
                    row.add(i);
                    col.add(j);
                    teacher.add(new int[] { i, j });
                }
            }
        }

        // 행이나 열에 선생님이 있는 경우에만 빈칸 배열에 추가
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j].equals("X")) {
                    if (row.contains(i) || col.contains(j)) {
                        blank.add(new int[] { i, j });
                    }
                }
            }
        }

        select = new ArrayList<>();
        comb(0, 0);
        System.out.println("NO");
    }

    static void comb(int idx, int depth) {
        if (depth == 3) {
            if (isValid()) {
                System.out.println("YES");
                System.exit(0);
            }
            // 되돌리기
            for (int[] s : select) {
                map[s[0]][s[1]] = "X";
            }
            return;
        }
        for (int i = idx; i < blank.size(); i++) {
            select.add(blank.get(i));
            comb(i + 1, depth + 1);
            select.remove(select.size() - 1);
        }
    }

    static boolean isValid() {
        for (int[] s : select) {
            map[s[0]][s[1]] = "O";
        }

        for (int[] t : teacher) {
            int x = t[0];
            int y = t[1];

            // 상
            while (x > 0) {
                x--;
                if (map[x][y].equals("O")) {
                    break;
                }
                if (map[x][y].equals("S")) {
                    return false;
                }
            }
            // 하
            x = t[0];
            while (x < n - 1) {
                x++;
                if (map[x][y].equals("O")) {
                    break;
                }
                if (map[x][y].equals("S")) {
                    return false;
                }
            }
            // 좌
            x = t[0];
            while (y > 0) {
                y--;
                if (map[x][y].equals("O")) {
                    break;
                }
                if (map[x][y].equals("S")) {
                    return false;
                }
            }
            // 우
            y = t[1];
            while (y < n - 1) {
                y++;
                if (map[x][y].equals("O")) {
                    break;
                }
                if (map[x][y].equals("S")) {
                    return false;
                }
            }
        }
        return true;
    }
}
