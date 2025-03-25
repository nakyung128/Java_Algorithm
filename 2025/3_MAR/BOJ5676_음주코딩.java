import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ5676_음주코딩 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        String input = "";
        while ((input = br.readLine()) != null) {
            st = new StringTokenizer(input);
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int pow = 0;
            while (true) {
                if (Math.pow(2, pow) >= N) {
                    break;
                }
                pow++;
            }

            int start = (int) Math.pow(2, pow);
            int[] tree = new int[start * 2];
            st = new StringTokenizer(br.readLine());

            // 트리 완성하기
            for (int i = 0; i < N; i++) {
                int x = Integer.parseInt(st.nextToken());
                if (x > 0) {
                    x = 1;
                } else if (x < 0) {
                    x = -1;
                }
                tree[start + i] = x;
            }
            for (int i = start - 1; i > 0; i--) {
                tree[i] = tree[i * 2] * tree[i * 2 + 1];
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                String type = st.nextToken();

                if (type.equals("C")) {
                    int idx = Integer.parseInt(st.nextToken());
                    int V = Integer.parseInt(st.nextToken());
                    idx = idx + start - 1;
                    if (V > 0) {
                        V = 1;
                    } else if (V < 0) {
                        V = -1;
                    }
                    tree[idx] = V;
                    while (idx > 0) {
                        idx /= 2;
                        tree[idx] = tree[idx * 2] * tree[idx * 2 + 1];
                    }
                } else {
                    int answer = 1;
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());

                    a = a + start - 1;
                    b = b + start - 1;

                    while (a <= b) {
                        if (a % 2 == 1) {
                            answer *= tree[a];
                        }
                        if (b % 2 == 0) {
                            answer *= tree[b];
                        }
                        a = (a + 1) / 2;
                        b = (b - 1) / 2;
                    }
                    if (answer > 0) {
                        sb.append("+");
                    } else if (answer < 0) {
                        sb.append("-");
                    } else {
                        sb.append("0");
                    }
                }
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}
