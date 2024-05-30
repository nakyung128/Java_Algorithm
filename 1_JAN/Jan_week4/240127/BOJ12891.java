import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12891 {
	static int[] checkArr;
	static int[] myArr;
	static int check;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int s = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());
		int answer = 0;

		char[] dna = new char[s];
		dna = br.readLine().toCharArray();
		
		checkArr = new int[4]; // 비밀번호 체크 배열 
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			checkArr[i] = Integer.parseInt(st.nextToken());
			if (checkArr[i] == 0) {
				check++;
			}
		}
		
		myArr = new int[4];// 현재 상태 체크 배열 
		
		// 맨 처음 문자열 확인 
		for (int i = 0; i < p; i++) {
			add(dna[i]);
		}
		
		if (check == 4) {
			answer++;
		}
		
		// 슬라이딩 
		for (int i = p; i < s; i++) {
			add(dna[i]);
			remove(dna[i-p]);
			
			if (check == 4) {
				answer++;
			}
		}
		
		System.out.println(answer);
	}
	
	// 문자 더하기 함수 
	public static void add(char c) {
		switch (c) {
		case 'A': 
			myArr[0]++;
			if (myArr[0] == checkArr[0]) check++;
			break;		
		case 'C':
			myArr[1]++;
			if (myArr[1] == checkArr[1]) check++;
			break;
		case 'G':
			myArr[2]++;
			if (myArr[2] == checkArr[2]) check++;
			break;
		case 'T':
			myArr[3]++;
			if (myArr[3] == checkArr[3]) check++;
			break;
		}
	}
	
	// 문자 빼기 함수 
	public static void remove(char c) {
		switch (c) {
		case 'A': 
			if (myArr[0] == checkArr[0]) check--;
			myArr[0]--;
			break;		
		case 'C':
			if (myArr[1] == checkArr[1]) check--;
			myArr[1]--;
			break;
		case 'G':
			if (myArr[2] == checkArr[2]) check--;
			myArr[2]--;
			break;
		case 'T':
			if (myArr[3] == checkArr[3]) check--;
			myArr[3]--;
			break;
		}
	}
}
