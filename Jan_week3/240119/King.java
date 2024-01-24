import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class King {
	static char row;
	static char col;
	static int c;
	
	static int kingC;
	static int kingR;
	static int rockC;
	static int rockR;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String king = st.nextToken();
		String stone = st.nextToken();
		int n = Integer.parseInt(st.nextToken());
		
		
		// king 첫 위치 넣기
		where("king", king.charAt(0), king.charAt(1));
		
		// stone 첫 위치 넣기
		where("stone", stone.charAt(0), stone.charAt(1));
		
		
		//반복 돌리기 
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String go = st.nextToken();
			
			switch (go) {
			case "R":
				if (go(1, 0) == -1) continue;
				break;
			case "L":
				if (go(-1, 0) == -1) continue;
				break;
			case "B":
				if (go(0, -1) == -1) continue;
				break;
			case "T":
				if (go(0, 1) == -1) continue;
				break;
			case "RT":
				if (go(1, 1) == -1) continue;
				break;
			case "LT":
				if (go(-1, 1) == -1) continue;
				break;
			case "RB":
				if (go(1, -1) == -1) continue;
				break;
			case "LB":
				if (go(-1, -1) == -1) continue;
				break;
			}
		}
		
		// 마지막 위치 출력
		System.out.println(num_to_str(kingC, kingR));
		System.out.println(num_to_str(rockC, rockR)); 
	}
	
	// 킹, 돌 위치 옮기는 함수 
	public static int go(int moveC, int moveR) {
		if (isOver(kingC+moveC) || isOver(kingR+moveR)) {
			return -1;
		} else {
			// 킹을 옮겼는데 돌 위치랑 같으면
			if ((kingC+moveC) == rockC && kingR+moveR == rockR) {
				// 돌이 밖으로 넘어가는가?
				if (isOver(rockC+moveC) || isOver(rockR+moveR)) {
					return -1;
				} else {
					// 킹과 돌 둘 다 옮기기
					kingC += moveC;
					kingR += moveR;
					rockC += moveC;
					rockR += moveR;
				}
			} else {
				// 킹만 옮기기
				kingC += moveC;
				kingR += moveR;
			}
		}
		return 1;
	}
	
	// 숫자를 출력할 문자로 바꾸기 
	public static String num_to_str(int c, int r) {
		String str = "";
		
		switch (c) {
		case 1: 
			str = "A"+Integer.toString(r);
			break;
		case 2:
			str = "B"+Integer.toString(r);
			break;
		case 3:
			str = "C"+Integer.toString(r);
			break;
		case 4:
			str = "D"+Integer.toString(r);
			break;
		case 5:
			str = "E"+Integer.toString(r);
			break;
		case 6:
			str = "F"+Integer.toString(r);
			break;
		case 7:
			str = "G"+Integer.toString(r);
			break;
		case 8:
			str = "H"+Integer.toString(r);
			break;
		}
		return str;
	}
	
	// 어디에 있는지 문자 -> 숫자 변
	public static void where(String who, char col, char row) {
		switch (col) {
		case 'A':
			c = 1;
			break;
		case 'B':
			c = 2;
			break;
		case 'C':
			c = 3;
			break;
		case 'D':
			c = 4;
			break;
		case 'E':
			c = 5;
			break;
		case 'F':
			c = 6;
			break;
		case 'G':
			c = 7;
			break;
		case 'H':
			c = 8;
			break;
		}
		
		if (who.equals("king")) {
			kingC = c;
			kingR = Integer.parseInt(String.valueOf(row));
		} else {
			rockC = c;
			rockR = Integer.parseInt(String.valueOf(row));
		}
	}
	
	// 체스판을 넘어가는가? 
	public static boolean isOver(int where) {
		if (where > 8 || where < 1) {
			return true;
		} else return false;
	}
}
