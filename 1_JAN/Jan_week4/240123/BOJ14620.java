import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14620 {

	static int n = 0;
	static int[][] garden;
	static int[][] visited;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		garden = new int[n][n];
		visited = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				garden[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		plant(0, 0);
		System.out.println(answer);

	}

	public static void plant(int sum, int cnt) {
		// 자기 자신부터 상하좌우 
		int[] dx = { 0, -1, 1, 0, 0 };
		int[] dy = { 0, 0, 0, -1, 1 };
		
		if (cnt == 3) {
			// 더 작은 값 넣기 
			answer = Math.min(sum, answer);
			return;
		}

		for (int i = 1; i < n - 1; i++) {
			for (int j = 1; j < n - 1; j++) {
				boolean can = true; // 심을 수 있는가? 
				
				for (int k = 0; k < 5; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];

					if (visited[nx][ny] == 1) {
						can = false;
						break;
					} 
				}
				
				// 심을 수 있다면 
				if (can) {
					int result = 0;
					for (int k = 0; k < 5; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];

						result += garden[nx][ny]; // 비용 더해 주기 
						visited[nx][ny] = 1; // 방문 처리 
					}
					
					plant(sum+result, cnt+1); 
					
					// visited 0으로 다시 초기화 
					for (int k = 0; k < 5; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];

						visited[nx][ny] = 0;
					}
				}

			}
		}
	}
}