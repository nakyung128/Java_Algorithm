import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA1232 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int test_case = 1; test_case <= 10; test_case++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            int[] nums = new int[n+1]; // 계산 결과
            Stack<String[]> childs = new Stack<>();

            // 스택에 연산자, 숫자 넣어주기
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                int node_num = Integer.parseInt(st.nextToken()); // 노드 번호
                String node = st.nextToken(); // 연산자 또는 숫자

                // 만약 연산자라면
                if (node.equals("-") || node.equals("+") || node.equals("/") || node.equals("*")) {
                    String child1 = st.nextToken();
                    String child2 = st.nextToken();

                    childs.add(new String[] { String.valueOf(node_num), node, child1, child2 });
                } else {
                    nums[node_num] = Integer.parseInt(node);
                }
            }

            // 계산하기
            while (!childs.isEmpty()) {
                String[] child = childs.pop();

                int node_num = Integer.parseInt(child[0]);
                String buho = child[1];
                int n1 = Integer.parseInt(child[2]);
                int n2 = Integer.parseInt(child[3]);

                switch (buho) {
                    case "+":
                        nums[node_num] = nums[n1] + nums[n2];
                        break;
                    case "-":
                        nums[node_num] = nums[n1] - nums[n2];
                        break;
                    case "/":
                        nums[node_num] = nums[n1] / nums[n2];
                        break;
                    case "*":
                        nums[node_num] = nums[n1] * nums[n2];
                        break;
                }
            }

            System.out.printf("#%d %d\n", test_case, nums[1]);
        }
    }
}
