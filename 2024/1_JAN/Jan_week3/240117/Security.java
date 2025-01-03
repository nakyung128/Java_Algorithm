import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Security {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int stores = Integer.parseInt(st.nextToken());

		ArrayList<int[]> where = new ArrayList<int[]>();
		
		for (int i = 0; i < stores; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			where.add(new int[] {x, y});
		}
		
		st = new StringTokenizer(br.readLine());
		
		// 동근이의 위치 
		int meX = Integer.parseInt(st.nextToken());
		int meY = Integer.parseInt(st.nextToken());
		
		int total = 0;
		
		for (int[] wh : where) {
			int root1 = 0; // 시계 방향 
			int root2 = 0; // 반시계 방향 
			
			int x = wh[0];
			int y = wh[1];
			
			if (x == 1) { // 북 
				if (meX == 1) { // 북
					total += Math.abs(y-meY);
				} else if (meX == 2) { // 남 
					root1 += (row - y);
					root1 += col;
					root1 += (row - meY);
					
					root2 += y;
					root2 += col;
					root2 += meY;

					total += Math.min(root1, root2);
					
				} else if (meX == 3) { // 서 
					total += y;
					total += meY;
					
				} else { // 동 
					total += (row - y);
					total += meY;
				}
				
			} else if (x == 2) { // 남 
				if (meX == 1) {
					root2 += (row - y);
					root2 += col;
					root2 += (row - meY);
					
					root1 += y;
					root1 += col;
					root1 += meY;

					total += Math.min(root1, root2);
				} else if (meX == 2) {
					total += Math.abs(y-meY);
				} else if (meX == 3) {
					total += y;
					total += (col - meY);
				} else {
					total += (row - y);
					total += (col - meY);
				}
			} else if (x == 3) {
				if (meX == 1) {
					total += y;
					total += meY;
				} else if (meX == 2) {
					total += (col - y);
					total += meY;
				} else if (meX == 3) {
					total += Math.abs(y-meY);
				} else {
					root2 += (row - y);
					root2 += row;
					root2 += (row - meY);
					
					root1 += y;
					root1 += row;
					root1 += meY;

					total += Math.min(root1, root2);
				}
				
			} else {
				if (meX == 1) { 
					total += y;
					total += (row - meY);
				} else if (meX == 2) { 
					total += (col - y);
					total += (row - meY);
				} else if (meX == 3) {
					root2 += y;
					root2 += row;
					root2 += meY;
					
					root1 += (col - y);
					root1 += row;
					root1 += (col - meY);
					
					total += Math.min(root1, root2);
				} else {
					total += Math.abs(y-meY);
				}
			}
			
			
		}
		
		System.out.println(total);
		
		
	}
}
