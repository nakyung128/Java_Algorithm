import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1253 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int[] numbers = new int[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(numbers); // 배열 정렬
		int answer = 0;
		
		// 투 포인터
		for (int k = 0; k < n; k++) {
			int i = 0;
			int j = numbers.length-1;
			int target = numbers[k];
			
			while (i < j) {
				if (numbers[i] + numbers[j] == target) {
					if (i != k && j != k) {
						answer++;
						break;
					} else {
						if (i == k) i++;
						else if (j == k) j--;
					}
				} else if (numbers[i] + numbers[j] > target) {
					j--;
				} else if (numbers[i] + numbers[j] < target) {
					i++;
				}
			}
		}
		
		System.out.println(answer);
	}
}
