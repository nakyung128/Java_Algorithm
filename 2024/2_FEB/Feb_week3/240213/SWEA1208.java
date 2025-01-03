import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA1208 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int test_case = 1; test_case <= 10; test_case++) {
			int dump = 0;
			
			if (test_case > 1) {
				st = new StringTokenizer(br.readLine());
				dump = Integer.parseInt(st.nextToken());
			} else {
				dump = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());

			ArrayList<Integer> heights = new ArrayList<Integer>();

			for (int i = 0; i < 100; i++) {
				heights.add(Integer.parseInt(st.nextToken()));
			}

			System.out.printf("#%s %d\n", test_case, flatten(dump, heights));
		}
	}

	public static int flatten(int dump, ArrayList<Integer> heights) {
		int minus = 0;
		
		heights.sort(null); // 처음에 정렬
		
		for (int i = 0; i < dump; i++) {
			heights.add(0, heights.get(0) + 1);
			heights.remove(1);
			heights.add(99, heights.get(99) - 1);
			heights.remove(100);
			
			// 상자 옮긴 뒤 다시 배열 정렬
			heights.sort(null);
			
			minus = Math.abs(heights.get(0) - heights.get(99));
			
			if (minus <= 1) {
				break;
			} 
		}
		
		return minus;
	}
}
