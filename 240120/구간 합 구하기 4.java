import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11659 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		int[] array = new int[n+1];
		int[] hap = new int[n+1];
		
		for (int i = 1; i < n+1; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		// 합 배열 생성 
		for (int i = 1; i < n+1; i++) {
			hap[i] = hap[i-1] + array[i];
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			System.out.println(hap[end] - hap[start-1]);
		}
	}
}
