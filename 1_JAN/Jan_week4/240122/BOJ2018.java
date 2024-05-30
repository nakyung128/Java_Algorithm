import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2018 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int[] num = new int[n];
		int answer = 0;
		
		for (int i = 1; i <= n; i++) {
			num[i-1] = i;
		}
		
		for (int i = 0; i < n; i++) {
			int hap = num[i];
			for (int j = i+1; j < n; j++) {
				hap += num[j];
				if (hap > n) break; // break 안 하면 시간초과 
				else if (hap == n) answer++;
				
			}
		}
		
		System.out.println(answer+1);
	}
}
