import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class MaxMultiple {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int s = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		long answer = 1;
		
		int mok = s / k;
		int nam = s % k;
		
		for (int i = 0; i < nam; i++) {
			answer *= (mok + 1);
		}
		
		for (int i = 0; i < k - nam; i++) {
			answer *= mok;
		}
		
		System.out.println(answer);
		
	}

}
