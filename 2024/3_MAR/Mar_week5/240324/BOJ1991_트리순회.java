import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1991_트리순회 {

    static class Node {
        char idx;
        Node left;
        Node right;

        Node(char idx) {
            this.idx = idx;
            this.left = null;
            this.right = null;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        Node[] tree = new Node[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            char a = st.nextToken().charAt(0);
            char b = st.nextToken().charAt(0);
            char c = st.nextToken().charAt(0);

            if (tree[a - 'A'] == null) {
                tree[a - 'A'] = new Node(a);
            }
            if (b != '.') {
                tree[b - 'A'] = new Node(b);
                tree[a - 'A'].left = tree[b - 'A'];
            }
            if (c != '.') {
                tree[c - 'A'] = new Node(c);
                tree[a - 'A'].right = tree[c - 'A'];
            }
        }

        // 전위 순회
        preOrder(tree[0]);
        System.out.println();

        // 중위
        inOrder(tree[0]);
        System.out.println();

        // 후위
        postOrder(tree[0]);
        System.out.println();
    }

    // 전위
    static void preOrder(Node root) {
        if (root == null)
            return;
        // 루트
        System.out.print(root.idx);
        // 왼쪽
        preOrder(root.left);
        // 오른쪽
        preOrder(root.right);
    }

    // 중위
    static void inOrder(Node root) {
        if (root == null)
            return;
        // 왼쪽
        inOrder(root.left);
        // 루트
        System.out.print(root.idx);
        // 오른쪽
        inOrder(root.right);
    }

    // 후위
    static void postOrder(Node root) {
        if (root == null)
            return;
        // 왼쪽
        postOrder(root.left);
        // 오른쪽
        postOrder(root.right);
        // 루트
        System.out.print(root.idx);
    }
}
