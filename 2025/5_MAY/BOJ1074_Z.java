import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1074_Z {
    static int r, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken()); // 행
        c = Integer.parseInt(st.nextToken()); // 열
        int size = (int) Math.pow(2, n);

        System.out.println(z(size, 0, 0));
    }

    static int z(int size, int x, int y) {
        if (size == 1) {
            return 0;
        }

        int half = size / 2;
        int pow = half * half;

        // 사분면
        if (r < x + half && c < y + half) { // 1사분면
            return z(half, x, y);
        } else if (r < x + half && c >= y + half) { // 2사분면
            return pow + z(half, x, y + half);
        } else if (r >= x + half && c < y + half) { // 3사분면
            return 2 * pow + z(half, x + half, y);
        } else { // 4사분면
            return 3 * pow + z(half, x + half, y + half);
        }
    }
}
