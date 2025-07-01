import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2877_4와7 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        int len = 1;
        int count = 2;

        while (k > count) {
            k -= count;
            len++;
            count = (int) Math.pow(2, len);
        }

        String bin = Integer.toBinaryString(k - 1);
        while (bin.length() < len)
            bin = "0" + bin;

        StringBuilder sb = new StringBuilder();
        for (char c : bin.toCharArray()) {
            sb.append(c == '0' ? '4' : '7');
        }

        System.out.println(sb);
    }
}
