import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Truck {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // 다리를 건너는 트럭의 수 
		int w = Integer.parseInt(st.nextToken()); // 다리의 길이 
		int l = Integer.parseInt(st.nextToken()); // 다리의 최대 하중 
		
		Queue<Integer> truck = new LinkedList<Integer>();
		Queue<Integer> bridge = new LinkedList<Integer>();
		
		int times = 0; // 걸리는 시간 
		int weight = 0; // 다리 위의 무게 
		
		st = new StringTokenizer(br.readLine());
		
		// 트럭 Queue에 넣기 
		for (int i = 0; i < n; i++) {
			truck.offer(Integer.parseInt(st.nextToken()));
		}
		
		// 다리 0으로 초기화 
		for (int i = 0; i < w; i++) {
			bridge.offer(0);
		}
		
		while (!bridge.isEmpty()) {
			times++;
			weight -= bridge.poll(); // 지나감 
			
			// 지나가지 못한 트럭이 아직 있으면 
			if (!truck.isEmpty()) {
				// 그 다음 트럭이 더해져도 최대 하중보다 같거나 작으면 지나가기 
				if (truck.peek() + weight <= l) {
					weight += truck.peek();
					bridge.offer(truck.poll());
				} else {
					// 최대 하중보다 크면 0 추가 
					bridge.offer(0);
				}
			}
		}
		
		System.out.println(times);
		
	}
}