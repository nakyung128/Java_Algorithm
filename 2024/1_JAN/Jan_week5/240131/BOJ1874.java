import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ1874 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuffer sb = new StringBuffer();
		
		int n = Integer.parseInt(st.nextToken());
		int[] nums = new int[n];
		
		Stack<Integer> stack = new Stack<Integer>();
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int num = 1;
		
		for (int i = 0; i < n; i++) {
			if (nums[i] >= num) {
				while (nums[i] >= num) {
					stack.push(num++);
					sb.append("+\n");
				}
				stack.pop();
				sb.append("-\n");
			} else {
				if (stack.pop() > nums[i]) {
					System.out.println("NO");
					System.exit(0);
				} else {
					sb.append("-\n");
				}
			}
		}
		
		System.out.println(sb.toString());
	}
}
