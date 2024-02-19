import java.util.Scanner;
import java.util.Stack;

public class SWEA1218 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int test_case = 1; test_case <= 10; test_case++) {
			int n = sc.nextInt();
			String gwal = sc.next();
			Stack<Character> stack = new Stack<Character>();

			boolean can = false;

			for (int i = 0; i < gwal.length(); i++) {
				if (gwal.charAt(i) == ')') {
					if (stack.peek() == '(') {
						stack.pop();
						can = true;
					} else {
						can = false;
						break;
					}
				} else if (gwal.charAt(i) == '>') {
					if (stack.peek() == '<') {
						stack.pop();
						can = true;
					} else {
						can = false;
						break;
					}
				} else if (gwal.charAt(i) == ']') {
					if (stack.peek() == '[') {
						stack.pop();
						can = true;
					} else {
						can = false;
						break;
					}
				} else if (gwal.charAt(i) == '}') {
					if (stack.peek() == '{') {
						stack.pop();
						can = true;
					} else {
						can = false;
						break;
					}
				} else {
					stack.push(gwal.charAt(i));
				}
			}
			
			if (!stack.isEmpty()) {
				can = false;
			}

			if (can == false) {
				System.out.printf("#%d %d\n", n, 0);
			} else {
				System.out.printf("#%d %d\n", n, 1);
			}
		}
        sc.close();
    }
}
