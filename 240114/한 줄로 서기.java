import java.util.Scanner;

class Oneline {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();
        int answer[] = new int[n];
        	
        for (int i = 0; i < n; i++) {
            int num = s.nextInt();
            
            for (int j = 0; j < n; j++) {
            	// 나보다 큰 사람 없는 경우 
            	if (num == 0) {
            		// 그 자리에 아무도 없으면 서기 
                	if (answer[j] == 0) {
                		answer[j] =i+1;
                		break;
                	}
                	// 누가 있으면 다른 빈칸 중 가장 가까운 곳에 선다
                	else {
                		continue;
                	}
            	} 
            	// 나보다 큰 사람이 있음, 자리가 비어 있을 때
				// 나보다 큰 사람이 앞에 있기 때문에 뒤로 가야 한다
            	else if (answer[j] == 0) {
            		num--;
            	}
            }
            
        }
        
        for (int i = 0; i < n; i++) {
        	System.out.print(answer[i] + " ");
        }

		s.close();
    }
}