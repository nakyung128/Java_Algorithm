import java.util.Scanner;

public class SWEA1210 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        for (int test_case = 1; test_case <= 10; test_case++) {
            int num = sc.nextInt();
            int[][] graph = new int[100][100];
            
            int nowX = 0;
            int nowY = 0;
            
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    graph[i][j] = sc.nextInt();
                }
            }
            
            int result = 0;
            for (int i = 0; i < 100; i++) {
                if (graph[0][i] == 1) {
                    int[][] visited = new int[100][100];
                    nowX = 0;
                    nowY = i;
                    result = i;
                                
                    // 사다리 타기
                    while (nowX < 99) {
                        // 오른쪽으로
                        if (nowY < 99 && graph[nowX][nowY+1] == 1 && visited[nowX][nowY+1] == 0) {
                            nowY += 1;
                            visited[nowX][nowY] = 1;
                        } else if (nowY > 0 && graph[nowX][nowY-1] == 1 && visited[nowX][nowY-1] == 0) { // 왼쪽으로
                            nowY -= 1;
                            visited[nowX][nowY] = 1;
                        } else { // 아래로
                            nowX += 1;
                            visited[nowX][nowY]=1;
                        }
                    }
                    
                    if (graph[99][nowY] == 2) {
                        System.out.printf("#%d %d\n", num, result);
                        break;
                    }
                }
            }
        }
        
        sc.close();
    }
}