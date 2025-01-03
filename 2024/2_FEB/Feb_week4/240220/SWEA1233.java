import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1233 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int test_case = 1; test_case <= 10; test_case++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 정점의 개수

            int answer = 1;

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                st.nextToken(); // 정점 번호
                char node = st.nextToken().charAt(0); // 노드

                // 자식이 있을 때
                if (st.hasMoreTokens()) {
                    // 숫자면 유효하지 않음
                    if (Character.isDigit(node)) {
                        answer = 0;
                    }
                } else { // 자식 없을 때
                    // 연산자면 유효하지 않음
                    if (node == '+' || node == '-' || node == '*' || node == '/') {
                        answer = 0;
                    }
                }
            }

            System.out.printf("#%d %d\n", test_case, answer);

        }
    }
}
