import java.util.Scanner;

public class SWEA1209 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for (int num = 0; num < 10; num++) {
			int test_case = sc.nextInt();
			int result = 0;
			int[][] graph = new int[100][100];
			
			for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100; j++) {
					graph[i][j] = sc.nextInt();
				}
			}
			
			// 각 행의 합
			for (int row = 0; row < 100; row++) {
				int hap = 0;
				for (int col = 0; col < 100; col++) {
					hap += graph[row][col];
				}
				result = Math.max(result, hap);
			}
			
			// 각 열의 합
			for (int row = 0; row < 100; row++) {
				int hap = 0;
				for (int col = 0; col < 100; col++) {
					hap += graph[col][row];
				}
				result = Math.max(result, hap);
			}
			
			// 대각선 (왼 => 오)
			int cross = 0;
			for (int i = 0; i < 100; i++) {
				cross += graph[i][i];
			}
			result = Math.max(result, cross);
			
			// 대각선 (오 => 왼)
			cross = 0;
			for (int i = 0; i < 100; i++) {
				cross += graph[i][99-i];
			}
			result = Math.max(result, cross);
			
 			System.out.printf("#%d %d\n", test_case, result);
		}
        sc.close();
	}
}