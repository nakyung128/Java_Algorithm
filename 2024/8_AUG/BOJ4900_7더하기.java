import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class BOJ4900_7더하기 {
    static HashMap<String, String> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new HashMap<>();
        map.put("063", "0");
        map.put("010", "1");
        map.put("093", "2");
        map.put("079", "3");
        map.put("106", "4");
        map.put("103", "5");
        map.put("119", "6");
        map.put("011", "7");
        map.put("127", "8");
        map.put("107", "9");

        while (true) {
            String line = br.readLine();
            if (line.equals("BYE")) {
                break;
            }
            int plusIdx = line.indexOf("+");
            String first = line.substring(0, plusIdx);
            String second = line.substring(plusIdx + 1, line.length() - 1);

            int first_num = Integer.parseInt(stringToInt(first));
            int second_num = Integer.parseInt(stringToInt(second));
            int hab = first_num + second_num;

            System.out.println(line + intToString(hab));
        }
    }

    static String intToString(int hab) {
        String answer = "";
        String str_hab = String.valueOf(hab);
        for (int i = 0; i < str_hab.length(); i++) {
            String str = String.valueOf(str_hab.charAt(i));
            for (String key : map.keySet()) {
                if (map.get(key).equals(str)) {
                    answer += key;
                }
            }
        }
        return answer;
    }

    static String stringToInt(String str) {
        String num = "";
        for (int i = 0; i < str.length(); i += 3) {
            String key = str.substring(i, i + 3);
            num += map.get(key);
        }

        return num;
    }
}
