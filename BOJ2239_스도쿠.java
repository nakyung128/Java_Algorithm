import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class BOJ2239_스도쿠 {
    static char[][] sudoku;
    static HashMap<Character, Integer> map;
    static ArrayList<Integer> numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sudoku = new char[9][9];

        for (int i = 0; i < 9; i++) {
            String line = br.readLine();
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudoku[i][j] == '0') {
                    map = new HashMap<Character, Integer>();
                    horizon(i, j);
                    vertical(i, j);
                    nine(i, j);

                    System.out.println("=== (" + i + ", " + j + ") ===");
                    for (Entry<Character, Integer> entrySet : map.entrySet()) {
                        System.out.println(entrySet.getKey() + " : " + entrySet.getValue());
                    }
                }
            }
        }

    }

    // 가로 확인하는 함수
    static void horizon(int x, int y) {
        numbers = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            int num = sudoku[x][i] - '0';

            if (num != 0)
                numbers.add(num);
        }

        for (int n = 1; n < 10; n++) {
            if (!numbers.contains(n)) {
                if (!map.containsKey((char) (n + '0'))) {
                    map.put((char) (n + '0'), 1);
                } else {
                    map.replace((char) (n + '0'), map.get((char) (n + '0')) + 1);
                }
            }
        }
    }

    // 세로 확인하는 함수
    static void vertical(int x, int y) {
        numbers = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            int num = sudoku[i][y] - '0';

            if (num != 0)
                numbers.add(num);
        }

        for (int n = 1; n < 10; n++) {
            if (!numbers.contains(n)) {
                System.out.println("세로에 없음: " + n);
                if (!map.containsKey((char) (n + '0'))) {
                    map.put((char) (n + '0'), 1);
                } else {
                    map.replace((char) (n + '0'), map.get((char) (n + '0')) + 1);
                }
            }
        }
    }

    // 9칸 확인하는 함수
    static void nine(int x, int y) {
        numbers = new ArrayList<>();
        if (x < 3) {
            if (y < 3) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        int num = sudoku[i][j] - '0';
                        if (num != 0) {
                            numbers.add(num);
                        }
                    }
                }

                for (int n = 1; n < 10; n++) {
                    if (!numbers.contains(n)) {
                        if (!map.containsKey((char) (n + '0'))) {
                            map.put((char) (n + '0'), 1);
                        } else {
                            map.replace((char) (n + '0'), map.get((char) (n + '0')) + 1);
                        }
                    }
                }
            } else if (y < 6) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 3; j < 6; j++) {
                        int num = sudoku[i][j] - '0';
                        if (num != 0) {
                            numbers.add(num);
                        }
                    }
                }

                for (int n = 1; n < 10; n++) {
                    if (!numbers.contains(n)) {
                        if (!map.containsKey((char) (n + '0'))) {
                            map.put((char) (n + '0'), 1);
                        } else {
                            map.replace((char) (n + '0'), map.get((char) (n + '0')) + 1);
                        }
                    }
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    for (int j = 6; j < 9; j++) {
                        int num = sudoku[i][j] - '0';
                        if (num != 0) {
                            numbers.add(num);
                        }
                    }
                }

                for (int n = 1; n < 10; n++) {
                    if (!numbers.contains(n)) {
                        if (!map.containsKey((char) (n + '0'))) {
                            map.put((char) (n + '0'), 1);
                        } else {
                            map.replace((char) (n + '0'), map.get((char) (n + '0')) + 1);
                        }
                    }
                }
            }
        } else if (x < 6) {
            if (y < 3) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        int num = sudoku[i][j] - '0';
                        if (num != 0) {
                            numbers.add(num);
                        }
                    }
                }

                for (int n = 1; n < 10; n++) {
                    if (!numbers.contains(n)) {
                        if (!map.containsKey((char) (n + '0'))) {
                            map.put((char) (n + '0'), 1);
                        } else {
                            map.replace((char) (n + '0'), map.get((char) (n + '0')) + 1);
                        }
                    }
                }
            } else if (y < 6) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 3; j < 6; j++) {
                        int num = sudoku[i][j] - '0';
                        if (num != 0) {
                            numbers.add(num);
                        }
                    }
                }

                for (int n = 1; n < 10; n++) {
                    if (!numbers.contains(n)) {
                        if (!map.containsKey((char) (n + '0'))) {
                            map.put((char) (n + '0'), 1);
                        } else {
                            map.replace((char) (n + '0'), map.get((char) (n + '0')) + 1);
                        }
                    }
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    for (int j = 6; j < 9; j++) {
                        int num = sudoku[i][j] - '0';
                        if (num != 0) {
                            numbers.add(num);
                        }
                    }
                }

                for (int n = 1; n < 10; n++) {
                    if (!numbers.contains(n)) {
                        if (!map.containsKey((char) (n + '0'))) {
                            map.put((char) (n + '0'), 1);
                        } else {
                            map.replace((char) (n + '0'), map.get((char) (n + '0')) + 1);
                        }
                    }
                }
            }
        } else {
            if (y < 3) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        int num = sudoku[i][j] - '0';
                        if (num != 0) {
                            numbers.add(num);
                        }
                    }
                }

                for (int n = 1; n < 10; n++) {
                    if (!numbers.contains(n)) {
                        if (!map.containsKey((char) (n + '0'))) {
                            map.put((char) (n + '0'), 1);
                        } else {
                            map.replace((char) (n + '0'), map.get((char) (n + '0')) + 1);
                        }
                    }
                }
            } else if (y < 6) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 3; j < 6; j++) {
                        int num = sudoku[i][j] - '0';
                        if (num != 0) {
                            numbers.add(num);
                        }
                    }
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    for (int j = 6; j < 9; j++) {
                        int num = sudoku[i][j] - '0';
                        if (num != 0) {
                            numbers.add(num);
                        }
                    }
                }

                for (int n = 1; n < 10; n++) {
                    if (!numbers.contains(n)) {
                        if (!map.containsKey((char) (n + '0'))) {
                            map.put((char) (n + '0'), 1);
                        } else {
                            map.replace((char) (n + '0'), map.get((char) (n + '0')) + 1);
                        }
                    }
                }
            }
        }
    }
}
