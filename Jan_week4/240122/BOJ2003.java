import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2003 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] hap = new int[n];
		
		st = new StringTokenizer(br.readLine());
		
		hap[0] = Integer.parseInt(st.nextToken());
		
		// 누적합 배열 생성
		for (int i = 1; i < n; i++) {
			hap[i] = hap[i-1] + Integer.parseInt(st.nextToken());
		}
		
		// 일단 합이 m인 것 세기
		int answer = 0;
		for (int num : hap) {
			if (num == m) answer++;
		}
		for (int i = 0; i < n-1; i++) {
			for (int j = i+1; j < n; j++) {
				if (hap[j]- hap[i] == m) answer++;
			}
		}
		
		System.out.println(answer);
	}
}
