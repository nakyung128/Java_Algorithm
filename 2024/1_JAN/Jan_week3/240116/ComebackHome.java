import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ComebackHome {
	
	// 상하좌우 
	static int[] dx = { 0, -1, 1, 0 };
	static int[] dy = { 1, 0, 0, -1 };
	
	static int r;
	static int c;
	static int k;
	
	static char[][] graph;
	static int[][] visited;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		graph = new char[r][c];
		visited = new int[r][c];
		
		// 맵의 정보 입력 받기 
		for (int i = 0; i < r; i++) {
			String line = br.readLine();
			for (int j = 0; j < c; j++) {
				graph[i][j] = line.charAt(j);
			}
		}
		
		visited[r-1][0] = 1;
		dfs(r-1, 0, 1);
		System.out.println(answer);
	}
	
	// 집까지 도착하는 거리 계산하는 함
	static void dfs(int x, int y, int cnt) {
		// 도착 
		if (x == 0 && y == c-1) {
			if (cnt == k) {
				answer++;
			}
			return;
		}
		
		// 시작 지점: 왼쪽 아래점, 도착 지점: 오른쪽 
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
				if (graph[nx][ny] == '.' && visited[nx][ny] == 0) {
					visited[nx][ny] = 1; // 방문 처리 
					dfs(nx, ny, cnt+1);
					visited[nx][ny] = 0; // 다른 방법에서는 지나갈 수 있도록 방문 처리 해제하기 
				} 
			}	
		}
	}
}
