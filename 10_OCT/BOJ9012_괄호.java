import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ9012_괄호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; i++) {
            String str = br.readLine();
            char[] arr = new char[str.length()];
            arr = str.toCharArray();

            Stack<Character> stack = new Stack<>();

            boolean flag = false;
            for (char c : arr) {
                if (c == '(') {
                    stack.add(c);
                } else {
                    if (stack.isEmpty()) {
                        flag = true;
                        break;
                    }
                    stack.pop();
                }
            }
            if (flag || !stack.isEmpty()) {
                sb.append("NO\n");
            } else {
                sb.append("YES\n");
            }
        }
        System.out.println(sb.toString());
    }
}
