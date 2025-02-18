import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2942_구간합구하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 수의 개수
        int m = Integer.parseInt(st.nextToken()); // 변경이 일어나는 횟수
        int k = Integer.parseInt(st.nextToken()); // 구간합 구하는 횟수

        int pow = 0;
        while (true) {
            if (Math.pow(2, pow) >= n) {
                break;
            } else {
                pow++;
            }
        }

        // 트리 만들기
        int start = (int) Math.pow(2, pow);
        long[] tree = new long[start * 2];

        for (int i = 0; i < n; i++) {
            tree[start + i] = Long.parseLong(br.readLine());
        }

        // 트리 앞부분 채우기
        for (int i = start - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == 1) {
                // b번째 수를 c로 바꾼다
                long c = Long.parseLong(st.nextToken());
                int idx = b + start - 1;
                long cha = c - tree[idx];
                tree[idx] = c;
                while (idx > 0) {
                    idx /= 2;
                    if (idx == 0) {
                        break;
                    }
                    tree[idx] = tree[idx] + cha;
                }
            } else {
                // b부터 c까지의 합을 구한다
                int c = Integer.parseInt(st.nextToken());
                long hab = 0;
                int start_idx = b + start - 1;
                int end_idx = c + start - 1;

                while (start_idx <= end_idx) {
                    if (start_idx % 2 == 1) {
                        hab += tree[start_idx];
                    }
                    if (end_idx % 2 == 0) {
                        hab += tree[end_idx];
                    }
                    start_idx = (start_idx + 1) / 2;
                    end_idx = (end_idx - 1) / 2;
                }
                sb.append(hab + "\n");
            }
        }
        System.out.print(sb.toString());
    }
}
