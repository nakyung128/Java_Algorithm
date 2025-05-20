import java.util.*;

class Solution {
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public int solution(int[][] maps) {
        int row = maps.length;
        int col = maps[0].length;
        int[][] visited = new int[row][col];

        int answer = bfs(row - 1, col - 1, maps, visited);
        if (answer == 0) {
            answer = -1;
        }
        return answer;
    }

    static int bfs(int row, int col, int[][] maps, int[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { 0, 0 });
        visited[0][0] = 1;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx <= row && ny >= 0 && ny <= col) {
                    if (visited[nx][ny] == 0 && maps[nx][ny] == 1) {
                        visited[nx][ny] = visited[x][y] + 1;
                        q.offer(new int[] { nx, ny });
                    }
                }
            }
        }
        return visited[row][col];
    }
}