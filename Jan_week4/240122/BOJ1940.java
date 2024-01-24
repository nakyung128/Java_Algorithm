import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1940 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] ingredient = new int[n];
		for (int i = 0; i < n; i++) {
			ingredient[i] = Integer.parseInt(st.nextToken());
		}
		
		// 투포인터 이용 
		Arrays.sort(ingredient); // 오름차순 정렬 
		
		int answer = 0;
		int i = 0;
		int j = n-1;
		
		while (i < j) {
			if (ingredient[i] + ingredient[j] > m) j--; 
			else if (ingredient[i] + ingredient[j] < m) i++;
			else { 
				i++; 
				j--; 
				answer++;
			}
			
		}
		
		System.out.println(answer);
	}
}

// 기존 이중 반복문 풀이 
//public class Main {
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		
//		int n = Integer.parseInt(st.nextToken());
//		
//		st = new StringTokenizer(br.readLine());
//		int m = Integer.parseInt(st.nextToken());
//		
//		st = new StringTokenizer(br.readLine());
//		int[] ingredient = new int[n];
//		for (int i = 0; i < n; i++) {
//			ingredient[i] = Integer.parseInt(st.nextToken());
//		}
//		
//		int answer = 0;
//		
//		for (int i = 0; i < n-1; i++) {
//			int hap = ingredient[i];
//			for (int j = i+1; j < n; j++) {
//				if ((hap + ingredient[j]) == m) {
//					answer++;
//				} else {
//					hap = ingredient[i];
//				}
//			}
//		}
//		
//		System.out.println(answer);
//	}
//}

