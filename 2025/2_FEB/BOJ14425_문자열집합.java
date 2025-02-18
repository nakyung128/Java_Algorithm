import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14425_문자열집합 {
    static class Node {
        Node[] next = new Node[26];
        boolean isEnd;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Node root = new Node();

        // 트라이 자료구조 구축
        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            Node now = root; // 현재 노드를 루트로 설정
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                if (now.next[c - 'a'] == null) {
                    now.next[c - 'a'] = new Node();
                }
                now = now.next[c - 'a'];
                if (j == word.length() - 1) {
                    now.isEnd = true;
                }
            }
        }

        // 트라이 자료구조 검색
        int answer = 0;
        for (int i = 0; i < m; i++) {
            String word = br.readLine();
            Node now = root;
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                if (now.next[c - 'a'] == null) {
                    break;
                }
                now = now.next[c - 'a'];
                if (j == word.length() - 1 && now.isEnd) {
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }
}