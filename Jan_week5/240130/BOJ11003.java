import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ11003 {
	static class Node {
		int index;
		int value;
		
		public Node(int index, int value) {
			this.index = index;
			this.value = value;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		Deque<Node> deque = new LinkedList<>();
		
		for (int i = 0; i < n; i++) {
			int now = Integer.parseInt(st.nextToken());
			
			// 만약 맨 뒤의 노드가 현재 데이터 값보다 크다면 제거 
			// 새로운 값이 들어올 때마다 현재 수보다 큰 값을 덱에서 제거함으로써 시간 복잡도 줄이기 
			while (!deque.isEmpty() && deque.getLast().value > now) {
				deque.removeLast();
			}
			
			// 현재 값 넣어주기 
			deque.addLast(new Node(i, now));
			
			// 인덱스 범위가 슬라이딩 윈도우를 벗어나면 맨 앞에 것 삭제해 주기 
			if (deque.getFirst().index <= i - l) {
				deque.removeFirst();
			}
			
			bw.write(deque.getFirst().value + " ");
		}
		bw.flush();
		bw.close();
	}
}
