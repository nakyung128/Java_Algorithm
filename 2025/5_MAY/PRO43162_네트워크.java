import java.util.*;

class Solution {
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;

    public int solution(int n, int[][] computers) {
        graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && computers[i][j] == 1) {
                    graph.get(i + 1).add(j + 1);
                }
            }
        }

        int answer = 0;
        visited = new boolean[n + 1];
        for (int i = 1; i < n + 1; i++) {
            if (!visited[i]) {
                dfs(i);
                answer++;
            }
        }
        return answer;
    }

    static void dfs(int idx) {
        for (int next : graph.get(idx)) {
            if (!visited[next]) {
                visited[next] = true;
                dfs(next);
            }
        }
    }
}