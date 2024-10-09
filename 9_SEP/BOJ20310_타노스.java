import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ20310_타노스 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        char[] arr = s.toCharArray();

        int oneCnt = 0;
        int zeroCnt = 0;

        for (char c : arr) {
            if (c == '1')
                oneCnt++;
            else
                zeroCnt++;
        }

        int cnt = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '1') {
                cnt++;
                arr[i] = 'x';
            }
            if (cnt == oneCnt / 2)
                break;
        }

        cnt = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == '0') {
                cnt++;
                arr[i] = 'x';
            }
            if (cnt == zeroCnt / 2)
                break;
        }

        StringBuilder sb = new StringBuilder();
        for (char c : arr) {
            if (c != 'x') {
                sb.append(String.valueOf(c));
            }
        }

        System.out.println(sb.toString());
    }
}
