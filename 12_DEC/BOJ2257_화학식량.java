import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ2257_화학식량 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<String> stack = new Stack<>();
        char[] formula = br.readLine().toCharArray();
        int answer = 0;

        for (char s : formula) {
            if (s == '(') {
                stack.push("(");
            } else if (s == ')') {
                int hab = 0;
                while (true) {
                    String str = stack.pop();
                    if (str.equals("(")) {
                        break;
                    }
                    hab += Integer.parseInt(str);
                }
                stack.push(String.valueOf(hab));
            } else if (s >= '1' && s <= '9') {
                int num = Integer.parseInt(stack.pop());
                num *= s - '0';
                stack.push(String.valueOf(num));
            } else {
                switch (s) {
                    case 'H':
                        stack.push("1");
                        break;
                    case 'C':
                        stack.push("12");
                        break;
                    case 'O':
                        stack.push("16");
                        break;
                }
            }
        }

        for (String str : stack) {
            answer += Integer.parseInt(str);
        }

        System.out.println(answer);
    }
}
