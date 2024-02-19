import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class SWEA1234 {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		for (int test_case = 1; test_case <= 10; test_case++) {
			int n = sc.nextInt();
			String  pass = sc.next();
			String pw = "";
			
			Stack<Character> stack = new Stack<>();
			
			for (int i = 0; i < n; i++) {
				if (stack.size() != 0) {
					if (stack.peek( ) == pass.charAt(i)) {
						stack.pop();
					} else {
						stack.push(pass.charAt(i));
					}
				} else {
					stack.push(pass.charAt(i));
				}
			}
			
			for (int i = 0; i < stack.size(); i++) {
				pw += stack.get(i);
			}
			
			System.out.printf("#%d %s\n", test_case, pw);
		}
        sc.close();
	}
}
