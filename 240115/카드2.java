import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Card2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		
		Queue<Integer> cards = new LinkedList<Integer>();;
		
		for (int i = 1; i <= n; i++) {
			cards.offer(i);
		}
		
		while (true) {
			if (cards.size() == 1) {
				System.out.println(cards.peek());
				break;
			}
			
			cards.poll(); // 제일 위 것을 버린다 
			cards.offer(cards.peek()); // 다음 것을 제일 아래로 옮긴다
			cards.poll();
		}	
		
	}
}
