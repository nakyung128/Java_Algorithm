import java.util.Scanner;
import java.util.Stack;

public class SWEA1219 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int t = 0; t < 10; t++) {
            int test_case = sc.nextInt();
            int n = sc.nextInt(); // 길의 총 개수

            int[][] node = new int[100][1];
            
            boolean[] visited = new boolean[100];

            // 입력
            for (int i = 0; i < n; i++) {
                int start = sc.nextInt();
                int end = sc.nextInt();

                if (node[start][0] != 0) {
                    node[start] = new int[] { node[start][0], end };
                } else {
                    node[start] = new int[] { end };
                }
            }
            
            Stack<Integer> stack = new Stack<Integer>();
            
            stack.push(0);
            visited[0] = true;
            
            boolean can = false;
            
            while (!stack.isEmpty()) {
            	int idx = stack.pop();
            	if (idx == 99) {
            		can = true;
            		break;
            	}
            	for (int nd : node[idx]) {
            		// 방문 안 했을 때 스택에 넣고 방문처리
            		if (!visited[nd]) {
            			stack.push(nd);
            			visited[nd] = true;
            		}
            	}
            }
            
            if (can) {
            	System.out.printf("#%d %d\n", test_case, 1);
            } else {
            	System.out.printf("#%d %d\n", test_case, 0);
            }
        }
        sc.close();
    }
}