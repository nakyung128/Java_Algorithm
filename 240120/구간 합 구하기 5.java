import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11660 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] graph = new int[n+1][n+1];
		int[][] hap = new int[n+1][n+1];
		
		for (int i = 1; i < n+1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < n+1; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		 
		// 구간 합 배열 값 넣어주기 
		for (int i = 1; i < n+1; i++) {
			for (int j = 1; j < n+1; j++) {
				hap[i][j] = hap[i][j-1] + hap[i-1][j] - hap[i-1][j-1] + graph[i][j];
			}
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int answer = hap[x2][y2] - hap[x1-1][y2] - hap[x2][y1-1] + hap[x1-1][y1-1];
			
			System.out.println(answer);
		}
	}
}
