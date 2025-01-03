import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
	int idx;
	int cost;

	Node(int idx, int cost) {
		this.idx = idx;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node o) {
		return this.cost - o.cost;
	}
}

public class BOJ1197_최소스패닝트리 {
	static ArrayList<ArrayList<Node>> graph;
	static PriorityQueue<Node> pq;
	static boolean[] visited;
	static int v, answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		v = Integer.parseInt(st.nextToken()); // 정점의 개수
		int e = Integer.parseInt(st.nextToken()); // 간선의 개수

		graph = new ArrayList<>();

		for (int i = 0; i < v + 1; i++) {
			graph.add(new ArrayList<>());
		}

		// 양방향 그래프 만들기
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph.get(a).add(new Node(b, c));
			graph.get(b).add(new Node(a, c));
		}

		// 초기화
		visited = new boolean[v + 1];
		pq = new PriorityQueue<>();
		answer = 0;
		
		pq.offer(new Node(1, 0)); // 우선순위 큐에 맨 처음 정점 넣어 주기
		
		prim();
		
		System.out.println(answer);
	}
	
	// 프림 알고리즘
	static void prim() {
		int cnt = 0;
		
		while(!pq.isEmpty()) {
			Node me = pq.poll(); // 우선순위 큐에서 뽑음
			
			// 방문하지 않은 경우
			if (!visited[me.idx]) {
				visited[me.idx] = true; // 방문
				answer += me.cost; // 최소 비용에 더해 주기
				
				if (++cnt == v) break; // cnt가 총 정점의 개수가 되면 끝내기
				
				// 탐색하면서 방문하지 않은 것 넣어 주기
				for (Node n : graph.get(me.idx)) {
					if (!visited[n.idx]) {
						pq.offer(n);
					}
				}
			}
		}
	}
}
