import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SWEA1267 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		for (int test_case = 1; test_case <= 10; test_case++) {
			sb = new StringBuilder();
			
			int v = sc.nextInt(); // 그래프의 정점의 개수 
			int e = sc.nextInt(); // 간선의 개수 
			
			// 진입차수 배열 
			int[] arr = new int[v+1];
			
			// 연결 정보 담기 
			ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
			
			for (int i = 0; i < v+1; i++) {
				graph.add(new ArrayList<Integer>());
			}
			
			for (int i = 0; i < e; i++) {
				int start = sc.nextInt();
				int end = sc.nextInt();
				
				arr[end] += 1;
				graph.get(start).add(end);
			}
			
			Queue<Integer> queue = new LinkedList<>();
			
			// 진입 차수가 0인 노드 큐에 넣기 
			for (int i = 1; i < arr.length; i++) {
				if (arr[i] == 0) queue.offer(i);
			}
			
			while (!queue.isEmpty()) {
				int node = queue.poll();
				
				// 꺼낸 노드 정렬 결과값에 저장 
				sb.append(String.valueOf(node) + " ");
				
				// 꺼낸 노드의 인접한 노드 찾기 
				for (int i = 0; i < graph.get(node).size(); i++) {
					// 인접한 노드의 진입차수 갱신하기 
					arr[graph.get(node).get(i)]--;
					// 진입차수가 0이라면 큐에 넣기 
					if (arr[graph.get(node).get(i)] == 0) {
						queue.offer(graph.get(node).get(i));
					}
				}
			}
			
			System.out.printf("#%d %s\n", test_case, sb);
		}
		sc.close();
	}
}