import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1245_균형점 {
  static double[] magnet;
  static int[] mass;
  static int n;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int t = Integer.parseInt(st.nextToken());

    for (int test_case = 1; test_case <= t; test_case++) {
      st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken()); // 자성체의 개수

      magnet = new double[n]; // 자성체의 x 좌표
      mass = new int[n]; // 질량

      st = new StringTokenizer(br.readLine());

      // x 좌표 입력받기
      for (int i = 0; i < n; i++) {
        magnet[i] = Double.parseDouble(st.nextToken());
      }

      // 질량 입력받기
      for (int i = 0; i < n; i++) {
        mass[i] = Integer.parseInt(st.nextToken());
      }
      StringBuilder sb = new StringBuilder();

      for (int i = 0; i < n - 1; i++) {
        sb.append(String.format("%.10f", search(i, magnet[i], magnet[i + 1])) + " ");
      }

      System.out.printf("#%d %s\n", test_case, sb);
    }
  }

  static double search(int idx, double left, double right) {
    double mid = 0.0; // 물체의 첫 위치 (가운데로)
    double dis = 0.0;

    // 이분탐색
    while (right - left >= 1e-12) {
      mid = (left + right) / 2.0;

      // 인력 구하기 (왼쪽 인력 - 오른쪽 인력)
      dis = cal(idx, mid);

      if (dis > 0) { // 오른쪽으로 더 가야 함
        left = mid;
      } else if (dis < 0) { // 왼쪽으로 더 가야 함
        right = mid;
      } else { // 맞음
        break;
      }
    }
    return mid;
  }

  // 왼쪽, 오른쪽 인력 계산
  static double cal(int idx, double mid) {
    double left = 0.0;
    double right = 0.0;

    // 왼쪽 자성체 인력의 합 계산
    for (int i = 0; i <= idx; i++) {
      left += (mass[i] / (Math.pow(magnet[i] - mid, 2)));
    }

    // 오른쪽 자성체 인력의 합 계산
    for (int i = idx + 1; i < n; i++) {
      right += (mass[i] / (Math.pow(magnet[i] - mid, 2)));
    }

    return left - right;
  }
}
