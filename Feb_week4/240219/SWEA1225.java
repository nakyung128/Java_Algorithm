import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SWEA1225 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int test_case = sc.nextInt();

            sb = new StringBuilder();
            
            Queue<Integer> queue = new LinkedList<>();
    
            for (int j = 0; j < 8; j++) {
                queue.offer(sc.nextInt());
            }
    
            while (true) {
                boolean isZero = false;
                for (int j = 1; j < 6; j++) {
                    int newNum = queue.poll() - j;
                    if (newNum <= 0) {
                        queue.offer(0);
                        isZero = true;
                        break;
                    } else {
                        queue.offer(newNum);
                    }
                }
                if (isZero) break;
            }

            for (int num : queue) {
                sb.append(String.valueOf(num) + " ");
            }

            System.out.printf("#%d %s\n", test_case, sb);
        }

        sc.close();
    }
}
