import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ25918_북극곰은괄호를찢어 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        char[] s = br.readLine().toCharArray();
        Stack<Character> stack = new Stack<>();
        int answer = 0;

        for (char c : s) {
            if (stack.isEmpty()) {
                stack.push(c);
            } else {
                stack.push(c);
                if (stack.get(stack.size() - 1) != stack.get(stack.size() - 2)) {
                    stack.pop();
                    stack.pop();
                }
            }
            if (!stack.isEmpty()) {
                answer = Math.max(answer, stack.size());
            }
        }

        if (stack.isEmpty()) {
            System.out.println(answer);
        } else {
            System.out.println(-1);
        }
    }
}
