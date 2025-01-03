import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class SWEA1224 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Stack<Character> stack;
		ArrayList<String> result;
		Stack<String> nums;

		for (int test_case = 1; test_case <= 10; test_case++) {
			int n = sc.nextInt();
			String s = sc.next();

			stack = new Stack<Character>();
			result = new ArrayList<String>();
			nums = new Stack<String>();

			// 후위표기식 만들기
			for (int i = 0; i < n; i++) {
				char c = s.charAt(i);
				if (c == '(' || c == '*') {
					stack.push(c);
				} else if (c == '+') {
					while (stack.peek() == '*') {
						result.add(stack.pop().toString());
					}
					stack.push(c);
				} else if (c == ')') {
					while (true) {
						if (stack.peek() == '(') {
							stack.pop();
							break;
						}
						result.add(stack.pop().toString());
					}
				} else {
					result.add(String.valueOf(c));
				}
			}

			// 후위표기식 계산
			for (int i = 0; i < result.size(); i++) {
				if (result.get(i).equals("*")) {
					int n1 = Integer.parseInt(nums.pop());
					int n2 = Integer.parseInt(nums.pop());
					nums.push(String.valueOf(n1 * n2));
				} else if (result.get(i).equals("+")) {
					int n1 = Integer.parseInt(nums.pop());
					int n2 = Integer.parseInt(nums.pop());
					nums.push(String.valueOf(n1 + n2));
				} else {
					nums.push(result.get(i));
				}
			}

			System.out.printf("#%d %s\n", test_case, nums.pop());
		}

		sc.close();
	}
}