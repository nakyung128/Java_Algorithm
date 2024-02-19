import java.util.Scanner;

public class SWEA1220 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for (int test_case = 1; test_case < 11; test_case++) {
			int[][] graph = new int[100][100];
			
			int N = sc.nextInt();
			int count = 0;
			
			for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100; j++) {
					graph[i][j] = sc.nextInt();;
				}
			}
			
			for (int col = 0; col < 100; col++) {
				boolean isOne = false;
				boolean isTwo = false;
				
				for (int row = 0; row < 100; row++) {
					if (isTwo == false && graph[row][col] == 1) {
						isOne = true;
					}
					
					if (isOne && graph[row][col] == 2) {
						isTwo = true;
					}
					
					if (isOne && isTwo) {
						count += 1;
						isOne = false;
						isTwo = false;
					}
				}
			}
			
			System.out.printf("#%d %d\n", N, count);	
		}
		sc.close();
	}
}