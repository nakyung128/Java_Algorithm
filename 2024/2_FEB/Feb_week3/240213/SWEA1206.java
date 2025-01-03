import java.util.Scanner;
import java.util.ArrayList;

public class SWEA1206 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for (int test_case = 1; test_case <= 10; test_case++) {
			int result = 0;
			ArrayList<Integer> buildings = new ArrayList<Integer>();
			
			int n = sc.nextInt();
			
			for (int i = 0; i < n; i++) {
				buildings.add(sc.nextInt());
			}

			for (int i = 2; i < n-2; i++) {
				if (buildings.get(i) - buildings.get(i-1) > 0 && buildings.get(i) - buildings.get(i+1) > 0 && buildings.get(i) - buildings.get(i-2) > 0 && buildings.get(i) - buildings.get(i+2) > 0) {
					int m1 = Math.min(buildings.get(i) - buildings.get(i-1), buildings.get(i) - buildings.get(i-2));
					int m2 = Math.min(buildings.get(i) - buildings.get(i+1), buildings.get(i) - buildings.get(i+2));
					int minus = Math.min(m1, m2);
					result += minus;
				} else continue;
			}
			
			System.out.printf("#%d %d\n", test_case, result);
		}
		sc.close();
	}
}
