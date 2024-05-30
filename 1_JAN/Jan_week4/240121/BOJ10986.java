import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10986 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		long[] origin = new long[n];
		
		st = new StringTokenizer(br.readLine());
		
		origin[0] = Long.parseLong(st.nextToken());
		
		for (int i = 1; i < n; i++) {
			origin[i] = Long.parseLong(st.nextToken());
		}
		
		// (a+b)%m = (a%m)+(b%m)
		// 따라서, %m을 한 누적 합 배열 생성하기 
		
		// 기존 누적합 배열 생성 
		long[] hap = new long[n];
		hap[0] = origin[0];
		
		for (int i = 1; i < n; i++) {
			hap[i] = hap[i-1] + origin[i];
		}
		
		// 나누어 떨어지는 구간의 개수 
		long answer = 0;
		
		for (int i = 0; i < n; i++) {
			hap[i] = hap[i] % m;
		}
		
		// 같은 원소의 개수를 담을 배열
		long[] idx = new long[m];
		
		for (int i = 0; i < n; i++) {
			int nam = (int) hap[i];
			if (nam == 0) answer++;
			idx[nam]++;
		}
		
		// 같은 원소끼리 경우의 수 구하기
		// idx[i] * idx[i]-1 과정에서 오버플로우가 일어날 수 있어 long type을 사용한 것 
		for (int i = 0; i < m; i++) {
			if (idx[i] > 1) {
				answer += (idx[i] * (idx[i]-1) / 2);
			}
		}
		
		System.out.println(answer);
	}
}
