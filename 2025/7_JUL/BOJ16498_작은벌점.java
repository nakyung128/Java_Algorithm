import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ16498_작은벌점 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] arrA = new int[a];
        int[] arrB = new int[b];
        int[] arrC = new int[c];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < a; i++) {
            arrA[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < b; i++) {
            arrB[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < c; i++) {
            arrC[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arrA);
        Arrays.sort(arrB);
        Arrays.sort(arrC);

        long answer = Long.MAX_VALUE;

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < a && j < b && k < c) {
            int x = arrA[i];
            int y = arrB[j];
            int z = arrC[k];

            int max = Math.max(x, Math.max(y, z));
            int min = Math.min(x, Math.min(y, z));
            answer = Math.min(answer, max - min);

            // 최소값을 증가시켜야 차이를 줄일 수 있다.
            if (min == x) {
                i++;
            } else if (min == y) {
                j++;
            } else {
                k++;
            }
        }
        System.out.println(answer);
    }
}
